package by.tms.medicins.parser;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;


public class DrugErrorHandler implements ErrorHandler {
    private static final Logger logger = LogManager.getLogger();
    @Override
    public void warning(SAXParseException exception) throws SAXException {
        logger.log(Level.WARN,"DrugErrorHandler has SAXParseException : {}", exception.getMessage());
    }

    @Override
    public void error(SAXParseException exception) throws SAXException {
        logger.log(Level.ERROR,"DrugErrorHandler has SAXParseException : {}", exception.getMessage());
    }

    @Override
    public void fatalError(SAXParseException exception) throws SAXException {
        logger.log(Level.FATAL,"DrugErrorHandler has SAXParseException : {}",exception.getMessage());
    }
}
