package by.tms.medicins.parser;

import by.tms.medicins.entity.*;
import by.tms.medicins.validator.XmlValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class DomMedicinsParser {
    private static final Logger logger = LogManager.getLogger();
    private final String fileName;

    public DomMedicinsParser(String fileName) {
        this.fileName = fileName;
    }

    public List<Drug> parse() {
        if (!new XmlValidator().isXmlFileValid("m.xml", "m.xsd")) {
            return null;
        }
        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            return collectInformation(document);
        } catch (ParserConfigurationException e) {
            logger.error("ParserConfigurationException", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            logger.error("IOException ", e);
            throw new RuntimeException(e);
        } catch (SAXException e) {
            logger.error("SAXException", e);
            throw new RuntimeException(e);
        }
    }

    private List<Drug> createDrugs(NodeList elements, Supplier<Drug> constructor, BiConsumer<Element, Drug> specificFieldSetter) {
        List<Drug> medicinsList = new ArrayList<>();
        for (int i = 0; i < elements.getLength(); i++) {
            Element element = (Element) elements.item(i);
            NamedNodeMap attributes = elements.item(i).getAttributes();
            String id = element.getAttribute("id");
            String title = element.getAttribute("title");
            String name = element.getElementsByTagName("drug-name").item(0).getTextContent().trim();
            String pharm = element.getElementsByTagName("pharm").item(0).getTextContent().trim();
            String group = element.getElementsByTagName("group").item(0).getTextContent().trim();
            String analog = element.getElementsByTagName("analog").item(0).getTextContent().trim();
            String version = element.getElementsByTagName("version").item(0).getTextContent().trim();
            String number = element.getElementsByTagName("number").item(0).getTextContent().trim();
            String dateOfIssue = element.getElementsByTagName("date-of-issue").item(0).getTextContent().trim();
            String expirationDate = element.getElementsByTagName("expiration-date").item(0).getTextContent().trim();
            String registeringOrganization = element.getElementsByTagName("registering-organization").item(0).getTextContent().trim();
            String type = element.getElementsByTagName("type").item(0).getTextContent().trim();
            String packageNumber = element.getElementsByTagName("package-number").item(0).getTextContent().trim();
            String price = element.getElementsByTagName("price").item(0).getTextContent().trim();
            String dosage = element.getElementsByTagName("dosage").item(0).getTextContent().trim();
            String multiplicity = element.getElementsByTagName("multiplicity").item(0).getTextContent().trim();

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
        NodeList chemicalElements = document.getElementsByTagName("chemical-drug");
        List<Drug> medicinsList = new ArrayList<>();
        medicinsList.addAll(createDrugs(
                plantElements,
                PlantDrug::new,
                (e, d) -> ((PlantDrug) d).setPlants(e.getElementsByTagName("plants").item(0).getTextContent().trim())
        ));
        medicinsList.addAll(createDrugs(
                chemicalElements,
                ChemicalDrug::new,
                (e, d) -> ((ChemicalDrug) d).setChemicalFormula(e.getElementsByTagName("chemical-formula").item(0).getTextContent().trim())
        ));
        logger.log(Level.INFO,"DomParser read file successfully");
        return medicinsList;
    }
}

