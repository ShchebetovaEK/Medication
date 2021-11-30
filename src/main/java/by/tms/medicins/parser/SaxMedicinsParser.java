package by.tms.medicins.parser;

import by.tms.medicins.entity.Medicins;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;

public class SaxMedicinsParser {

    private static final Logger logger = LogManager.getLogger();
    private final String fileName;

    public SaxMedicinsParser(String fileName) {
        this.fileName = fileName;
    }

    public Medicins parse() {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SaxParserHandler saxParserHandler = new SaxParserHandler();
        SAXParser parser = null;

        try {
            parser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException e) {
            logger.error("ParserConfigurationException {}", e);
        } catch (SAXException e) {
            logger.error("SAXException {}", e);
        }

        File file = new File("C:\\Users\\HP\\IdeaProjects\\Medication\\src\\main\\resources\\Medicins.xml");
        try {
            parser.parse(file, saxParserHandler);
        } catch (SAXException e) {
            logger.error("SAXException {}", e);
        } catch (NullPointerException e) {
            logger.error("NullPointerException {}", e);
        } catch (IOException e) {
            logger.error("SAxparser have {}", e);
        }
        return saxParserHandler.getMedicins();
    }

}
