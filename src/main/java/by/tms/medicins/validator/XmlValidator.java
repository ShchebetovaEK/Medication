package by.tms.medicins.validator;

import by.tms.medicins.parser.SaxMedicinsParser;
import org.apache.logging.log4j.Level;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XmlValidator {
    private static final Logger logger = LogManager.getLogger();

    public boolean isXmlFileValid(String xmlFilepath, String xsdFilepath) {
        URL fileURl1 = SaxMedicinsParser.class
                .getClassLoader()
                .getResource(xmlFilepath);
        File file1 = new File(fileURl1.getFile());

        URL fileURl2 = SaxMedicinsParser.class
                .getClassLoader()
                .getResource(xsdFilepath);
        File file2 = new File(fileURl2.getFile());

        try {
            SchemaFactory.newDefaultInstance()
                    .newSchema(file2)
                    .newValidator()
                    .validate(new StreamSource(file1));

            return true;
        } catch (IOException | SAXException e) {
            logger.log(Level.ERROR,"XMLValidator fail : {} ", e.getMessage());
            return false;

        }
    }
}