package by.tms.medicins.parser;

import by.tms.medicins.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class DomMedicinsParser extends AbstractParser<Drug> {

    private static final Logger logger = LogManager.getLogger();

    public DomMedicinsParser(String fileName) {
        super(fileName);
    }

    private List<Drug> createDrugs(NodeList elements, Supplier<Drug> constructor, BiConsumer<Element,Drug> specificFieldSetter) {
        List<Drug> medicinsList = new ArrayList<>();
        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            NamedNodeMap attributes = elements.item(i).getAttributes();
            String id = attributes.getNamedItem("id").getNodeName();
            String title = attributes.getNamedItem("title").getNodeName();
            String name = element.getElementsByTagName("name").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", name);
            String pharm = element.getElementsByTagName("pharm").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", pharm);
            String group = element.getElementsByTagName("group").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", group);
            String analog = element.getElementsByTagName("analog").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", analog);
            String version = element.getElementsByTagName("version").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", version);
            String number = element.getElementsByTagName("number").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", number);
            String dateOfIssue = element.getElementsByTagName("date-of-issue").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", dateOfIssue);
            String expirationDate = element.getElementsByTagName("expiration-date").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", expirationDate);
            String registeringOrganization = element.getElementsByTagName("registering-organization").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", registeringOrganization);
            String type = element.getElementsByTagName("type").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", type);
            String packageNumber = element.getElementsByTagName("package-number").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", packageNumber);
            String price = element.getElementsByTagName("price").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", price);
            String dosage = element.getElementsByTagName("dosage").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", dosage);
            String multiplicity = element.getElementsByTagName("multiplicity").item(0).getTextContent().trim();
            logger.info("DomParser read element {}", multiplicity);

            Drug drug = constructor.get();
            drug.setId(id);
            drug.setTitle(title);
            drug.setName(name);
            drug.setPharm(pharm);
            drug.setGroup(group);
            drug.setAnalog(analog);
            drug.setVersion(Version.valueOf(version));
            drug.setNumber(Long.parseLong(number));
            drug.setDateOfIssue(LocalDate.parse(dateOfIssue));
            drug.setExpirationDate(LocalDate.parse(expirationDate));
            drug.setRegisteringOrganization(registeringOrganization);
            drug.setType(type);
            drug.setPackageNumber(Integer.parseInt(packageNumber));
            drug.setPrice(Long.parseLong(price));
            drug.setDosage(Long.parseLong(dosage));
            drug.setMultiplicity(multiplicity);
            specificFieldSetter.accept(element, drug);
            medicinsList.add(drug);
        }
        return medicinsList;
    }

    protected List<Drug> collectInformation(Document document) {
        NodeList plantElements = document.getElementsByTagName("plant-drug");
        NodeList chemicalElements = document.getElementsByTagName("сhemical-drug");
        List<Drug> medicinsList = new ArrayList<>();
        medicinsList.addAll(createDrugs(
                plantElements,
                PlantDrug::new,
                (e, d) -> ((PlantDrug) d).setPlants(e.getElementsByTagName("plants").item(0).getTextContent().trim())
        ));
        medicinsList.addAll(createDrugs(
                chemicalElements,
                ChemicalDrug::new,
                (e, d) -> ((ChemicalDrug) d).setChemicalformula(e.getElementsByTagName("chemical-formula").item(0).getTextContent().trim())
        ));
        logger.info("DomParser read file successfully");
        return medicinsList;
    }
}

