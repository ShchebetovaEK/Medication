package by.tms.medicins;

import by.tms.medicins.entity.Drug;
import by.tms.medicins.entity.Medicins;
import by.tms.medicins.parser.DomMedicinsParser;
import by.tms.medicins.parser.SaxMedicinsParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        DomMedicinsParser medicinsInfoParser = new DomMedicinsParser("Medicins.xml");
        List<Drug> drugList = medicinsInfoParser.parse();
        System.out.println(drugList);
        SaxMedicinsParser saxMedicinsParser = new SaxMedicinsParser("Medicins.xml");
        Medicins medicins = saxMedicinsParser.parse();
        System.out.println(medicins.toString());



    }
}
