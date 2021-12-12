package by.tms.medicins.parser;

import by.tms.medicins.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static by.tms.medicins.entity.DrugTag.*;

public class SaxParserHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger();
    private static final char HIGH = '-';
    private static final char UNDER = '_';
    private List<Drug> drugList = new ArrayList<>();
    private DrugTag currentTag;
    private Drug currentDrug = new ChemicalDrug();
    private String currentElement;

    public List<Drug> getDrugList() {
        return drugList;
    }

    @Override
    public void startDocument() throws SAXException {
        logger.info("SaxHandler Start");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info(" Drugs in drugList ");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;

        if (currentElement.equals("chemical-drug")) {
            currentDrug = new ChemicalDrug();
            currentDrug.setId(attributes.getValue(ID.getTagName()));
            currentDrug.setTitle(attributes.getValue(TITLE.getTagName()));
            logger.info("chemical drug {}",currentDrug.getId());
        } else if (currentElement.equals("plant-drug")) {
            currentDrug = new PlantDrug();
            currentDrug.setId(attributes.getValue(ID.getTagName()));
            currentDrug.setTitle(attributes.getValue(TITLE.getTagName()));
            logger.info("plant {}",currentDrug.getId());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(CHEMICAL_DRUG.getTagName()) || qName.equals(PLANT_DRUG.getTagName())) {
            drugList.add(currentDrug);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String data = new String(ch, start, length).replace("\n", "").strip();
        if (!data.isEmpty()) {
            if (currentElement.equals(DrugTag.DRUG_NAME.getTagName())) {
                currentDrug.setName(data);
            } else if (currentElement.equals(DrugTag.PHARM.getTagName())) {
                currentDrug.setPharm(data);
            } else if (currentElement.equals(DrugTag.GROUP.getTagName())) {
                currentDrug.setGroup(data);
            } else if (currentElement.equals(DrugTag.ANALOG.getTagName())) {
                currentDrug.setAnalog(data);
            } else if (currentElement.equals(DrugTag.VERSION.getTagName())) {
                currentDrug.setVersion(Version.valueOf(data));
            } else if (currentElement.equals(DrugTag.NUMBER.getTagName())) {
                currentDrug.setNumber(Integer.parseInt(data));
            } else if (currentElement.equals(DrugTag.PACKAGE_NUMBER.getTagName())) {
                currentDrug.setPackageNumber(Integer.parseInt(data));
            } else if (currentElement.equals(DATE_OF_ISSUE.getTagName())) {
                currentDrug.setDateOfIssue(LocalDate.parse(data));
            } else if (currentElement.equals(DrugTag.EXPIRATION_DATE.getTagName())) {
                currentDrug.setExpirationDate(LocalDate.parse(data));
            } else if (currentElement.equals(DrugTag.REGISTERING_ORGANIZATION.getTagName())) {
                currentDrug.setRegisteringOrganization(data);
            } else if (currentElement.equals(DrugTag.TYPE.getTagName())) {
                currentDrug.setType(data);
            } else if (currentElement.equals(DrugTag.PRICE.getTagName())) {
                currentDrug.setPrice(Long.parseLong(data));
            } else if (currentElement.equals(DrugTag.DOSAGE.getTagName())) {
                currentDrug.setDosage(Long.parseLong(data));
            } else if (currentElement.equals(DrugTag.MULTIPLICITY.getTagName())) {
                currentDrug.setMultiplicity(data);
            } else if(currentElement.equals(CHEMICAL_FORMULA.getTagName())){
                ((ChemicalDrug) currentDrug).setChemicalformula(data);
            } else if(currentElement.equals(PLANTS.getTagName())){
                ((PlantDrug)currentDrug).setPlants(data);
            }
        }
    }
}



//        }
//            switch (currentTag) {
//                case TAG_NAME:
//                    currentDrug.setName(data);
//                    break;
//                case TAG_ANALOG:
//                    currentDrug.setAnalog(data);
//                    break;
//                case TAG_GROUP:
//                    currentDrug.setGroup(data);
//                    break;
//                case TAG_PHARM:
//                    currentDrug.setPharm(data);
//                    break;
//                case TAG_VERSION:
//                    String version = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
//                    currentDrug.setVersion(Version.valueOf(version));
//                    break;
//                case TAG_NUMBER:
//                    String number = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
//                    currentDrug.setNumber(Long.parseLong(number));
//                    break;
//                case TAG_DATA_OF_ISSUE:
//                    String dateOfIssue = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
//                    currentDrug.setDateOfIssue(LocalDate.parse(dateOfIssue));
//                    break;
//                case TAG_EXPIRATION_DATE:
//                    String expirationDate = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
//                    currentDrug.setExpirationDate(LocalDate.parse(expirationDate));
//                    break;
//                case TAG_REGISTERING_ORGANIZATION:
//                    currentDrug.setRegisteringOrganization(data);
//                    break;
//                case TAG_TYPE:
//                    currentDrug.setType(data);
//                    break;
//                case TAG_PACKAGE_NUMBER:
//                    String packageNumber = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
//                    currentDrug.setPackageNumber(Integer.parseInt(packageNumber));
//                    break;
//                case TAG_PRICE:
//                    String price = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
//                    currentDrug.setPrice(Long.parseLong(price));
//                    break;
//                case TAG_DOSAGE:
//                    String dosage = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
//                    currentDrug.setDosage(Long.parseLong(dosage));
//                    break;
//                case TAG_MULTIPLICITY:
//                    currentDrug.setMultiplicity(data);
//                    break;
//                case TAG_CHEMICAL_FORMULA:
//                    ChemicalDrug tempChemicalDrug = (ChemicalDrug) currentDrug;
//                    String chemicalFormula = data.toUpperCase(Locale.ROOT);
//                    tempChemicalDrug.setChemicalformula(chemicalFormula);
//                    break;
//                case TAG_PLANT:
//                    PlantDrug tempPlantDrug = (PlantDrug) currentDrug;
//                    String plant = data.toUpperCase(Locale.ROOT);
//                    tempPlantDrug.setPlants(plant);
//                    break;
//                default:
//                    throw new EnumConstantNotPresentException(
//                            currentTag.getDeclaringClass(), currentTag.name());
//            }
//            currentTag = null;
//        }




