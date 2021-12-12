package by.tms.medicins.entity;

public class ChemicalDrug extends Drug{
    private String Chemicalformula;

    public String getChemicalformula() {
        return Chemicalformula;
    }

    public void setChemicalformula(String chemicalformula) {
        Chemicalformula = chemicalformula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ChemicalDrug that = (ChemicalDrug) o;

        return Chemicalformula.equals(that.Chemicalformula);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + Chemicalformula.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChemicalDrug{");
        sb.append(super.toString());
        sb.append("Chemicalformula='").append(Chemicalformula).append('\'');
        sb.append('}');
        return sb.toString();
    }

}
