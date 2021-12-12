package by.tms.medicins.entity;

public enum DrugTag {
    MEDICINS,
    PLANT_DRUG,
    CHEMICAL_DRUG,
    CERTIFICATE,
    PACKAGE_DRUG,
    DOSA,
    ID,
    TITLIE,
    TAG_NAME,
    TAG_PHARM,
    TAG_GROUP,
    TAG_ANALOG,
    TAG_VERSION,
    TAG_NUMBER,
    TAG_DATA_OF_ISSUE,
    TAG_EXPIRATION_DATE,
    TAG_REGISTERING_ORGANIZATION,
    TAG_TYPE,
    TAG_PACKAGE_NUMBER,
    TAG_PRICE,
    TAG_DOSAGE,
    TAG_MULTIPLICITY,
    TAG_CHEMICAL_FORMULA,
    TAG_PLANT;

    private static final char UNDER = '_';
    private static final char HIGH = '-';

    private String getTagName() {
        String tagName = this.name();
        tagName = tagName.toLowerCase();
        tagName = tagName.replace(UNDER, HIGH);
        return tagName;
    }
}

