package by.tms.medicins;

import by.tms.medicins.entity.Drug;
import by.tms.medicins.parser.MedicinsInfoParser;

import java.util.List;

public class Main {

    public static void main(String[] args) {


        MedicinsInfoParser medicinsInfoParser = new MedicinsInfoParser("Medicins.xml");
        List<Drug> drugList = medicinsInfoParser.parse();
        System.out.println(drugList);




    }
}
