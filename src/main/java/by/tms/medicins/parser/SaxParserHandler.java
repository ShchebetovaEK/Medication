package by.tms.medicins.parser;

import by.tms.medicins.entity.Drug;
import by.tms.medicins.entity.Medicins;
import by.tms.medicins.entity.Version;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SaxParserHandler extends DefaultHandler {

    private static final Logger logger = LogManager.getLogger();
    private static final String TAG_DRUG = "drug";
    private static final String TAG_NAME = "name";
    private static final String TAG_PHARM = "pharm";
    private static final String TAG_GROUP = "group";
    private static final String TAG_ANALOG = "analog";
    private static final String TAG_VERSION = "version";
    private static final String TAG_NUMBER = "number";
    private static final String TAG_DATA_OF_ISSUE = "date-of-issue";
    private static final String TAG_EXPIRATION_DATE = "expiration-date";
    private static final String TAG_REGISTERING_ORGANIZATION = "registering-organization";
    private static final String TAG_TYPE = "type";
    private static final String TAG_PACKAGE_NUMBER = "package-number";
    private static final String TAG_PRICE = "price";
    private static final String TAG_DOSAGE = "dosage";
    private static final String TAG_MULTIPLICITY = "multiplicity";

    private boolean isDrug = false;

    private String name;
    private String pharm;
    private String group;
    private String analog;
    private Version version;
    private long number;
    private LocalDate dateOfIssue;
    private LocalDate expirationDate;
    private String registeringOrganization;
    private String type;
    private int packageNumber;
    private long price;
    private long dosage;
    private String multiplicity;

    private List<Drug> drugList = new ArrayList<>();
    private String currentTagName;

    Medicins medicins = new Medicins();

    public Medicins getMedicins() {
        return medicins;
    }

    @Override
    public void startDocument() throws SAXException {
        logger.info("SaxHandler Start");
    }

    @Override
    public void endDocument() throws SAXException {
        logger.info("SaxHandler end read file");
        medicins.setDrugList(drugList);
        logger.info(" Drugs in drugList ");
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        currentTagName = qName;
        if (currentTagName.equals(TAG_DRUG)) {
            isDrug = true;
            logger.info("SaxHandler start reading Element {}",qName);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {

        if (qName.equals(TAG_DRUG)) {
            isDrug = false;
            logger.info("SaxHandler complete {}",qName);
            Drug drug = new Drug(name, pharm, group, analog, version, number, dateOfIssue, expirationDate,
                    registeringOrganization, type, packageNumber, price, dosage, multiplicity);
            drugList.add(drug);
            logger.info("SaxHandler stop reading Element {}",qName);
        }
        currentTagName = null;
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (currentTagName == null) {
            return;
        } else if (currentTagName.equals(TAG_NAME)) {
            name = new String(ch, start, length);
            logger.info("SaxHandler create {} drug ",TAG_NAME);
        } else if (currentTagName.equals(TAG_PHARM)) {
            pharm = new String(ch, start, length);
            logger.info("SaxHandler create {} drug ",TAG_PHARM);
        } else if (currentTagName.equals(TAG_GROUP)) {
            group = new String(ch, start, length);
            logger.info("SaxHandler create {} drug ",TAG_GROUP);
        } else if (currentTagName.equals(TAG_ANALOG)) {
            analog = new String(ch, start, length);
            logger.info("SaxHandler create {} drug ",TAG_ANALOG);
        } else if (currentTagName.equals(TAG_VERSION)) {
            version = Version.valueOf(new String(ch, start, length));
            logger.info("SaxHandler create {} drug ",TAG_VERSION);
        } else if (currentTagName.equals(TAG_NUMBER)) {
            number = Integer.valueOf(new String(ch, start, length));
            logger.info("SaxHandler create {} drug ",TAG_NUMBER);
        } else if (currentTagName.equals(TAG_DATA_OF_ISSUE)) {
            dateOfIssue = LocalDate.parse(new String(ch, start, length));
            logger.info("SaxHandler create {} drug ",TAG_DATA_OF_ISSUE);
        } else if (currentTagName.equals(TAG_EXPIRATION_DATE)) {
            expirationDate = LocalDate.parse(new String(ch, start, length));
            logger.info("SaxHandler create {} drug ",TAG_EXPIRATION_DATE);
        } else if (currentTagName.equals(TAG_REGISTERING_ORGANIZATION)) {
            registeringOrganization = new String(ch, start, length);
            logger.info("SaxHandler create {} drug ",TAG_REGISTERING_ORGANIZATION);
        } else if (currentTagName.equals(TAG_TYPE)) {
            type = new String(ch, start, length);
            logger.info("SaxHandler create {} drug ",TAG_TYPE);
        } else if (currentTagName.equals(TAG_PACKAGE_NUMBER)) {
            packageNumber = Integer.valueOf(new String(ch, start, length));
            logger.info("SaxHandler create {} drug ",TAG_PACKAGE_NUMBER);
        } else if (currentTagName.equals(TAG_PRICE)) {
            price = Long.valueOf(new String(ch, start, length));
            logger.info("SaxHandler create {} drug ",TAG_PRICE);
        } else if (currentTagName.equals(TAG_DOSAGE)) {
            dosage = Long.valueOf(new String(ch, start, length));
            logger.info("SaxHandler create {} drug ",TAG_DOSAGE);
        } else if (currentTagName.equals(TAG_MULTIPLICITY)) {
            multiplicity = new String(ch, start, length);
            logger.info("SaxHandler create {} drug ",TAG_MULTIPLICITY);
        }
    }
}

