package by.tms.medicins.parser;

import by.tms.medicins.entity.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Locale;

public class SaxParserHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger();
    private static final char HIGH = '-';
    private static final char UNDER = '_';
    private List<Drug> drugList;
    private DrugTag currentTag;
    private Drug currentDrug;
    private final EnumSet<DrugTag> withText;

    public SaxParserHandler() {
        drugList = new ArrayList<>();
        withText = EnumSet.range(DrugTag.TAG_NAME, DrugTag.TAG_PLANT);
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    @Override
    public void startDocument() throws SAXException {
        logger.info("SaxHandler Start");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("SaxHandler end read file");
        getDrugList();
        logger.info(" Drugs in drugList ");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        logger.info("SaxHandler start reading Element {}", qName);
        String name = qName.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
        if (qName.equals(DrugTag.CHEMICAL_DRUG.toString()) || qName.equals(DrugTag.PLANT_DRUG.toString())) {
            DrugType drugType = DrugType.valueOf(name);
            switch (drugType) {
                case CHEMICAL_DRUG:
                    currentDrug = new ChemicalDrug();
                    break;
                case PLANT_DRUG:
                    currentDrug = new PlantDrug();
                    break;
            }
            if (attributes.getLength() == 1) {
                currentDrug.setId(attributes.getValue(0));
                currentDrug.setTitle(attributes.getValue(0));
            } else {
                int idAttributeIndex = attributes.getLocalName(0).equals(DrugTag.ID.toString()) ? 0 : 1;
                                currentDrug.setId(attributes.getValue(idAttributeIndex));
                            }
        } else {
            DrugTag temp = DrugTag.valueOf(name);
            if (withText.contains(temp)) {
                currentTag = temp;
            }
        }
    }





    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals(DrugType.CHEMICAL_DRUG.toString()) || qName.equals(DrugType.PLANT_DRUG.toString())) {
            drugList.add(currentDrug);
            currentDrug = null;
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        logger.info("very good");
        String data = new String(ch, start, length).strip();
        if (currentTag != null) {
            switch (currentTag) {
                case TAG_NAME:
                    currentDrug.setName(data);
                    break;
                case TAG_ANALOG:
                    currentDrug.setAnalog(data);
                    break;

                case TAG_GROUP:
                    currentDrug.setGroup(data);
                    break;
                case TAG_PHARM:
                    currentDrug.setPharm(data);
                    break;
                case TAG_VERSION:
                    String version = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
                    currentDrug.setVersion(Version.valueOf(version));
                    break;
                case TAG_NUMBER:
                    String number = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
                    currentDrug.setNumber(Long.parseLong(number));
                    break;
                case TAG_DATA_OF_ISSUE:
                    String dateOfIssue = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
                    currentDrug.setDateOfIssue(LocalDate.parse(dateOfIssue));
                    break;
                case TAG_EXPIRATION_DATE:
                    String expirationDate = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
                    currentDrug.setExpirationDate(LocalDate.parse(expirationDate));
                    break;
                case TAG_REGISTERING_ORGANIZATION:
                    currentDrug.setRegisteringOrganization(data);
                    break;
                case TAG_TYPE:
                    currentDrug.setType(data);
                    break;
                case TAG_PACKAGE_NUMBER:
                    String packageNumber = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
                    currentDrug.setPackageNumber(Integer.parseInt(packageNumber));
                    break;
                case TAG_PRICE:
                    String price = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
                    currentDrug.setPrice(Long.parseLong(price));
                    break;
                case TAG_DOSAGE:
                    String dosage = data.toUpperCase(Locale.ROOT).replace(HIGH, UNDER);
                    currentDrug.setDosage(Long.parseLong(dosage));
                    break;
                case TAG_MULTIPLICITY:
                    currentDrug.setMultiplicity(data);
                    break;
                case TAG_CHEMICAL_FORMULA:
                    ChemicalDrug tempChemicalDrug = (ChemicalDrug) currentDrug;
                    String chemicalFormula = data.toUpperCase(Locale.ROOT);
                    tempChemicalDrug.setChemicalformula(chemicalFormula);
                    break;
                case TAG_PLANT:
                    PlantDrug tempPlantDrug = (PlantDrug) currentDrug;
                    String plant = data.toUpperCase(Locale.ROOT);
                    tempPlantDrug.setPlants(plant);
                    break;
                default:
                    throw new EnumConstantNotPresentException(
                            currentTag.getDeclaringClass(), currentTag.name());
            }
            currentTag = null;
        }
    }
}


