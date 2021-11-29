package by.tms.medicins.parser;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.util.List;

public abstract class AbstractParser<T> {

    private final String fileName;

    public AbstractParser(String fileName) {
        this.fileName = fileName;
    }

    public final List<T> parse() {

        try (InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(fileName)) {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(inputStream);
            return collectInformation(document);

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }
    }

    protected abstract List<T> collectInformation(Document document);
}



