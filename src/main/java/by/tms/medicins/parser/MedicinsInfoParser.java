package by.tms.medicins.parser;

import by.tms.medicins.entity.Drug;
import by.tms.medicins.entity.Version;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MedicinsInfoParser extends AbstractParser<Drug> {

    public MedicinsInfoParser(String fileName) {
        super(fileName);
    }

    protected List<Drug> collectInformation(Document document) {
        NodeList elements = document.getElementsByTagName("drug");
        List<Drug> medicinsList = new ArrayList<>();
        for (int i = 0; i < elements.getLength(); i++) {

            Element element = (Element) elements.item(i);
//            NamedNodeMap attributes = elements.item(i).getAttributes();
            String name = element.getElementsByTagName("name").item(0).getTextContent().trim();
            String pharm = element.getElementsByTagName("pharm").item(0).getTextContent().trim();
            String group = element.getElementsByTagName("group").item(0).getTextContent().trim();
            String analog = element.getElementsByTagName("analog").item(0).getTextContent().trim();
            String version = element.getElementsByTagName("version").item(0).getTextContent().trim();
            String number = element.getElementsByTagName("number").item(0).getTextContent().trim();
            String dateOfIssue = element.getElementsByTagName("date-Of-Issue").item(0).getTextContent().trim();
            String expirationDate = element.getElementsByTagName("expirationDate").item(0).getTextContent().trim();
            String registeringOrganization = element.getElementsByTagName("registeringOrganization").item(0).getTextContent().trim();
            String type = element.getElementsByTagName("type").item(0).getTextContent().trim();
            String numberpac = element.getElementsByTagName("numberpac").item(0).getTextContent().trim();
            String price = element.getElementsByTagName("price").item(0).getTextContent().trim();
            String dosage = element.getElementsByTagName("dosage").item(0).getTextContent().trim();
            String multiplicity = element.getElementsByTagName("multiplicity").item(0).getTextContent().trim();
            medicinsList.add(new Drug(name, pharm, group, analog,Version.valueOf(version),
                    Long.parseLong(number), LocalDate.parse(dateOfIssue), LocalDate.parse(expirationDate),
                    registeringOrganization,type, Integer.parseInt(numberpac), Long.parseLong(price),
                    Long.parseLong(dosage), multiplicity));
        }
        return medicinsList;
    }
}

