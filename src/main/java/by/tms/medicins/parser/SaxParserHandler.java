package by.tms.medicins.parser;

import by.tms.medicins.entity.*;
import org.apache.logging.log4j.Level;
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
    private Drug currentDrug = new ChemicalDrug();
    private String currentElement;

    public List<Drug> getDrugList() {
        return drugList;
    }

    @Override
    public void startDocument() throws SAXException {
        logger.log(Level.TRACE,"SaxHandler Start");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.log(Level.TRACE," Drugs in drugList ");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentElement = qName;

        if (currentElement.equals("chemical-drug")) {
            currentDrug = new ChemicalDrug();
            currentDrug.setId(attributes.getValue(ID.getTagName()));
            currentDrug.setTitle(attributes.getValue(TITLE.getTagName()));
            logger.log(Level.TRACE,"start read chemical drug {}",currentDrug.getId());
        } else if (currentElement.equals("plant-drug")) {
            currentDrug = new PlantDrug();
            currentDrug.setId(attributes.getValue(ID.getTagName()));
            currentDrug.setTitle(attributes.getValue(TITLE.getTagName()));
            logger.log(Level.TRACE,"start read plant {}",currentDrug.getId());
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals(CHEMICAL_DRUG.getTagName()) || qName.equals(PLANT_DRUG.getTagName())) {
            drugList.add(currentDrug);
            logger.log(Level.TRACE,"end read element {}",qName);
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
                currentDrug.setNumber(Long.parseLong(data));
            } else if (currentElement.equals(DATE_OF_ISSUE.getTagName())) {
                currentDrug.setDateOfIssue(LocalDate.parse(data));
            } else if (currentElement.equals(DrugTag.EXPIRATION_DATE.getTagName())) {
                currentDrug.setExpirationDate(LocalDate.parse(data));
            } else if (currentElement.equals(DrugTag.REGISTERING_ORGANIZATION.getTagName())) {
                currentDrug.setRegisteringOrganization(data);
            } else if (currentElement.equals(DrugTag.TYPE.getTagName())) {
                currentDrug.setType(data);
            } else if (currentElement.equals(DrugTag.PACKAGE_NUMBER.getTagName())) {
                currentDrug.setPackageNumber(Integer.parseInt(data));
            } else if (currentElement.equals(DrugTag.PRICE.getTagName())) {
                currentDrug.setPrice(Long.parseLong(data));
            } else if (currentElement.equals(DrugTag.DOSAGE.getTagName())) {
                currentDrug.setDosage(Long.parseLong(data));
            } else if (currentElement.equals(DrugTag.MULTIPLICITY.getTagName())) {
                currentDrug.setMultiplicity(data);
            } else if(currentElement.equals(CHEMICAL_FORMULA.getTagName())){
                ((ChemicalDrug) currentDrug).setChemicalFormula(data);
            } else if(currentElement.equals(PLANTS.getTagName())){
                ((PlantDrug)currentDrug).setPlants(data);
            }
            logger.log(Level.TRACE,"read characters {}",currentElement);
        }
    }
}