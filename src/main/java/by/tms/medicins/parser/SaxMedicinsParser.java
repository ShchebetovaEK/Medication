package by.tms.medicins.parser;

import by.tms.medicins.entity.ChemicalDrug;
import by.tms.medicins.entity.Drug;
import by.tms.medicins.entity.PlantDrug;
import by.tms.medicins.validator.XmlValidator;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;

public class SaxMedicinsParser {
    private static final Logger logger = LogManager.getLogger();
    private final String fileName;
    private List<Drug> drugList;

    public SaxMedicinsParser(String fileName) {
        this.fileName = fileName;
    }

    public List<Drug> parse() {
        if (!new XmlValidator().isXmlFileValid("m.xml", "m.xsd")) {
            return drugList;
        }
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            SaxParserHandler handler = new SaxParserHandler();
            URL fileURl = SaxMedicinsParser.class
                    .getClassLoader()
                    .getResource(fileName);
            File file = new File(fileURl.getFile());
            parser.parse(file, handler);
            drugList = handler.getDrugList();
        } catch (ParserConfigurationException e) {
            logger.log(Level.ERROR, "Error getting new parser: {}", e.getMessage());
        } catch (SAXException e) {
            logger.log(Level.ERROR,"SAXException : {}", e.getMessage());
        } catch (IOException e) {
            logger.log(Level.ERROR,"IOException : {}", e.getMessage());
        }
        return drugList;
    }
}

