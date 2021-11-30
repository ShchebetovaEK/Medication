package by.tms.medicins.parser;

import by.tms.medicins.entity.Drug;
import by.tms.medicins.entity.Version;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DomMedicinsParser extends AbstractParser<Drug> {

    private static final Logger logger = LogManager.getLogger();

    public DomMedicinsParser(String fileName) {
        super(fileName);
    }

    protected List<Drug> collectInformation(Document document) {
        NodeList elements = document.getElementsByTagName("drug");
        List<Drug> medicinsList = new ArrayList<>();
        for (int i = 0; i < elements.getLength(); i++) {

            Element element = (Element) elements.item(i);
//            NamedNodeMap attributes = elements.item(i).getAttributes();
            String name = element.getElementsByTagName("name").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",name);
            String pharm = element.getElementsByTagName("pharm").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",pharm);
            String group = element.getElementsByTagName("group").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",group);
            String analog = element.getElementsByTagName("analog").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",analog);
            String version = element.getElementsByTagName("version").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",version);
            String number = element.getElementsByTagName("number").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",number);
            String dateOfIssue = element.getElementsByTagName("date-of-issue").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",dateOfIssue);
            String expirationDate = element.getElementsByTagName("expiration-date").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",expirationDate);
            String registeringOrganization = element.getElementsByTagName("registering-organization").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",registeringOrganization);
            String type = element.getElementsByTagName("type").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",type);
            String packageNumber = element.getElementsByTagName("package-number").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",packageNumber);
            String price = element.getElementsByTagName("price").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",price);
            String dosage = element.getElementsByTagName("dosage").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",dosage);
            String multiplicity = element.getElementsByTagName("multiplicity").item(0).getTextContent().trim();
            logger.info("DomParser read element {}",multiplicity);
            medicinsList.add(new Drug(name, pharm, group, analog,Version.valueOf(version),
                    Long.parseLong(number), LocalDate.parse(dateOfIssue), LocalDate.parse(expirationDate),
                    registeringOrganization,type, Integer.parseInt(packageNumber), Long.parseLong(price),
                    Long.parseLong(dosage), multiplicity));
        } logger.info("DomParser read file successfully");
        return medicinsList;
    }
}

