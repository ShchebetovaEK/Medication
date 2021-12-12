package by.tms.medicins;

import by.tms.medicins.entity.Drug;
import by.tms.medicins.parser.DomMedicinsParser;
import by.tms.medicins.parser.SaxMedicinsParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {

        DomMedicinsParser medicinsInfoParser = new DomMedicinsParser("m.xml");
        List<Drug> drugList = medicinsInfoParser.parse();
        System.out.println(drugList);
        SaxMedicinsParser medicinsParser = new SaxMedicinsParser("m.xml");
        List<Drug> drugListSax = medicinsParser.parse();
        System.out.println(drugListSax);

    }
}
