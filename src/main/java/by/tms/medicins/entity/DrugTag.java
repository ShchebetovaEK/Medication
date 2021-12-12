package by.tms.medicins.entity;

public enum DrugTag {
    MEDICINS,
    PLANT_DRUG,
    CHEMICAL_DRUG,
    CERTIFICATE,
    PACKAGE_DRUG,
    DOSA,
    ID,
    TITLE,
    DRUG_NAME,
    PHARM,
    GROUP,
    ANALOG,
    VERSION,
    NUMBER,
    DATE_OF_ISSUE,
    EXPIRATION_DATE,
    REGISTERING_ORGANIZATION,
    TYPE,
    PACKAGE_NUMBER,
    PRICE,
    DOSAGE,
    MULTIPLICITY,
    CHEMICAL_FORMULA,
    PLANTS;

    private static final char UNDER = '_';
    private static final char HIGH = '-';

    public String getTagName() {
        String tagName = this.name();
        tagName = tagName.toLowerCase();
        tagName = tagName.replace(UNDER, HIGH);
        return tagName;
    }
}

