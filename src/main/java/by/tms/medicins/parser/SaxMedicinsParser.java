package by.tms.medicins.parser;

import by.tms.medicins.entity.ChemicalDrug;
import by.tms.medicins.entity.Drug;
import by.tms.medicins.entity.PlantDrug;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.*;
import java.io.IOException;
import java.util.List;

public class SaxMedicinsParser extends AbstractParser<Drug> {
    private static final Logger logger = LogManager.getLogger();

    public SaxMedicinsParser(String fileName) {
        super(fileName);
    }

    private List<Drug> drugList;


    public void createSax() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            var handler = new SaxParserHandler();
            XMLReader reader = parser.getXMLReader();
            reader.setContentHandler(handler);
            reader.setErrorHandler(new DrugErrorHandler());
            reader.parse(fileName);
            parser.parse(fileName,handler);
            drugList = handler.getDrugList();

        } catch (ParserConfigurationException e) {
            logger.info("ParserConfigurationException");
        } catch (SAXException e) {
            logger.info("SAXException ", e);
        } catch (IOException e) {
            logger.info("IOException");
    }

    }

    @Override
    protected List<Drug> collectInformation(Document document) {
        logger.info("sax collect");
        return drugList;
    }
}

