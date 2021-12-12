package by.tms.medicins.entity;

public class PlantDrug extends Drug{
    private String plants;

    public PlantDrug() {
        super();
    }

    public String getPlants() {
        return plants;
    }

    public void setPlants(String plants) {
        this.plants = plants;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        PlantDrug plantDrug = (PlantDrug) o;

        return plants.equals(plantDrug.plants);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + plants.hashCode();
        return result;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("PlantDrug{");
        sb.append(super.toString());
        sb.append("plants='").append(plants).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
