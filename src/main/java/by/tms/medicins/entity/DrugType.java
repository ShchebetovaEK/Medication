package by.tms.medicins.entity;

public enum DrugType {
    CHEMICAL_DRUG,
    PLANT_DRUG;

    @Override
    public String toString() {
        String result = this.name();
        result = result.toLowerCase();
        return result;
    }
}
