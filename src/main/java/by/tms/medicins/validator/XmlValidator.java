package by.tms.medicins.validator;

import org.xml.sax.SAXException;
import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class XmlValidator {

    private static final Logger logger = LogManager.getLogger();

    public boolean isXmlFileValid(String xmlFilepath, String xsdFilepath) {
        try {
            SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI)
                    .newSchema(new File(xsdFilepath))
                    .newValidator()
                    .validate(new StreamSource(new File(xmlFilepath)));

            return true;
        } catch (IOException | SAXException e) {
            logger.error("XMLvalidator fail ", e);
            return false;

        }
    }
}



