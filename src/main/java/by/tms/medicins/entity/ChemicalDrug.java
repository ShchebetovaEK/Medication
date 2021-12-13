package by.tms.medicins.entity;

public class ChemicalDrug extends Drug {
    private String chemicalFormula;

    public ChemicalDrug() {
        super();
    }

    public String getChemicalFormula() {
        return chemicalFormula;
    }

    public void setChemicalFormula(String chemicalFormula) {
        this.chemicalFormula = chemicalFormula;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ChemicalDrug that = (ChemicalDrug) o;

        return chemicalFormula.equals(that.chemicalFormula);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + chemicalFormula.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ChemicalDrug{");
        sb.append(super.toString());
        sb.append("Chemicalformula='").append(chemicalFormula).append('\'');
        sb.append('}');
        return sb.toString();
    }
}