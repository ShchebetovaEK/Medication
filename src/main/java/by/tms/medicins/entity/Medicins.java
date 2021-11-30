package by.tms.medicins.entity;

import java.util.List;

public class Medicins {

    List<Drug> drugList;

    public Medicins() {
    }

    public Medicins(List<Drug> drugList) {
        this.drugList = drugList;
    }

    public List<Drug> getDrugList() {
        return drugList;
    }

    public void setDrugList(List<Drug> drugList) {
        this.drugList = drugList;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Medicins{");
        sb.append("drugList=").append(drugList);
        sb.append('}');
        return sb.toString();
    }
}
