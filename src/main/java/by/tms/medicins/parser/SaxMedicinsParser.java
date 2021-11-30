package by.tms.medicins.parser;

import by.tms.medicins.entity.Medicins;
import org.xml.sax.SAXException;

import javax.xml.parsers.*;
import java.io.File;
import java.io.IOException;

public class SaxMedicinsParser {

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
            System.out.println("ops");
        } catch (SAXException e) {
            System.out.println("gh");
        }

        File file = new File( "C:\\Users\\HP\\IdeaProjects\\Medication\\src\\main\\resources\\Medicins.xml");
        try {
           parser.parse(file,saxParserHandler);
        } catch (SAXException e) {
            System.out.println("parse err");
        }
        catch (NullPointerException e){
            System.out.println(e);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return saxParserHandler.getMedicins();
    }

}
