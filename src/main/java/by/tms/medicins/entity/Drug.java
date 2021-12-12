package by.tms.medicins.entity;

import java.time.LocalDate;

public abstract class Drug {

    private String id;
    private String title;
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
    private int packageNumber;
    private long price;
    private long dosage;
    private String multiplicity;

    public Drug() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public int getPackageNumber() {
        return packageNumber;
    }

    public void setPackageNumber(int packageNumber) {
        this.packageNumber = packageNumber;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Drug drug = (Drug) o;

        if (number != drug.number) return false;
        if (packageNumber != drug.packageNumber) return false;
        if (price != drug.price) return false;
        if (dosage != drug.dosage) return false;
        if (!id.equals(drug.id)) return false;
        if (!title.equals(drug.title)) return false;
        if (!name.equals(drug.name)) return false;
        if (!pharm.equals(drug.pharm)) return false;
        if (!group.equals(drug.group)) return false;
        if (!analog.equals(drug.analog)) return false;
        if (version != drug.version) return false;
        if (!dateOfIssue.equals(drug.dateOfIssue)) return false;
        if (!expirationDate.equals(drug.expirationDate)) return false;
        if (!registeringOrganization.equals(drug.registeringOrganization)) return false;
        if (!type.equals(drug.type)) return false;
        return multiplicity.equals(drug.multiplicity);
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + title.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + pharm.hashCode();
        result = 31 * result + group.hashCode();
        result = 31 * result + analog.hashCode();
        result = 31 * result + version.hashCode();
        result = 31 * result + (int) (number ^ (number >>> 32));
        result = 31 * result + dateOfIssue.hashCode();
        result = 31 * result + expirationDate.hashCode();
        result = 31 * result + registeringOrganization.hashCode();
        result = 31 * result + type.hashCode();
        result = 31 * result + packageNumber;
        result = 31 * result + (int) (price ^ (price >>> 32));
        result = 31 * result + (int) (dosage ^ (dosage >>> 32));
        result = 31 * result + multiplicity.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Drug{");
        sb.append("id='").append(id).append('\'');
        sb.append(", title='").append(title).append('\'');
        sb.append(", name='").append(name).append('\'');
        sb.append(", pharm='").append(pharm).append('\'');
        sb.append(", group='").append(group).append('\'');
        sb.append(", analog='").append(analog).append('\'');
        sb.append(", version=").append(version);
        sb.append(", number=").append(number);
        sb.append(", dateOfIssue=").append(dateOfIssue);
        sb.append(", expirationDate=").append(expirationDate);
        sb.append(", registeringOrganization='").append(registeringOrganization).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", packageNumber=").append(packageNumber);
        sb.append(", price=").append(price);
        sb.append(", dosage=").append(dosage);
        sb.append(", multiplicity='").append(multiplicity).append('\'');
        sb.append('}');
        return sb.toString();
    }
}

