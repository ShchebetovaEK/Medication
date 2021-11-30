package by.tms.medicins.entity;

import java.time.LocalDate;

public class Drug {

    private String name;
    private String pharm;
    private String group;
    private String analog;
    private Version version;
    private long number;
    private LocalDate dateOfIssue;
    private LocalDate expirationDate;
    private String registeringOrganization;
    private String type;
    private int numberpac;
    private long price;
    private long dosage;
    private String multiplicity;



    public Drug(String name, String pharm, String group, String analog, long number, LocalDate dateOfIssue, String registeringOrganization, String type, int numberpac, long price, long dosage, String multiplicity) {
        this.name = name;
        this.pharm = pharm;
        this.group = group;
        this.analog = analog;
        this.number = number;
        this.dateOfIssue = dateOfIssue;
        this.registeringOrganization = registeringOrganization;
        this.type = type;
        this.numberpac = numberpac;
        this.price = price;
        this.dosage = dosage;
        this.multiplicity = multiplicity;
    }

    public Drug(String name, String pharm, String group, String analog, Version version, long number, LocalDate dateOfIssue,
                LocalDate expirationDate, String registeringOrganization, String type, int numberpac, long price, long dosage, String multiplicity) {
        this.name = name;
        this.pharm = pharm;
        this.group = group;
        this.analog = analog;
        this.version = version;
        this.number = number;
        this.dateOfIssue = dateOfIssue;
        this.expirationDate = expirationDate;
        this.registeringOrganization = registeringOrganization;
        this.type = type;
        this.numberpac = numberpac;
        this.price = price;
        this.dosage = dosage;
        this.multiplicity = multiplicity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPharm() {
        return pharm;
    }

    public void setPharm(String pharm) {
        this.pharm = pharm;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getAnalog() {
        return analog;
    }

    public void setAnalog(String analog) {
        this.analog = analog;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public LocalDate getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(LocalDate dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    public String getRegisteringOrganization() {
        return registeringOrganization;
    }

    public void setRegisteringOrganization(String registeringOrganization) {
        this.registeringOrganization = registeringOrganization;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNumberpac() {
        return numberpac;
    }

    public void setNumberpac(int numberpac) {
        this.numberpac = numberpac;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getDosage() {
        return dosage;
    }

    public void setDosage(long dosage) {
        this.dosage = dosage;
    }

    public String getMultiplicity() {
        return multiplicity;
    }

    public void setMultiplicity(String multiplicity) {
        this.multiplicity = multiplicity;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Drug{");
        sb.append("name='").append(name).append('\'');
        sb.append(", pharm='").append(pharm).append('\'');
        sb.append(", group='").append(group).append('\'');
        sb.append(", analog='").append(analog).append('\'');
        sb.append(", version=").append(version);
        sb.append(", number=").append(number);
        sb.append(", dateOfIssue=").append(dateOfIssue);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", registeringOrganization='").append(registeringOrganization).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", numberpac=").append(numberpac);
        sb.append(", price=").append(price);
        sb.append(", dosage=").append(dosage);
        sb.append(", multiplicity='").append(multiplicity).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

