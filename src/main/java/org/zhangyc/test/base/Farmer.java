package org.zhangyc.test.base;

/**
 * Created by user on 16/7/18.
 */
public class Farmer extends Person {
    private Double quantityOfLand;
    private Double plantingTypes;

    public Farmer(
            Long id, String name, Integer age,
            Double quantityOfLand, Double plantingTypes) {
        super(id, name, age);
        this.quantityOfLand = quantityOfLand;
        this.plantingTypes = plantingTypes;
    }

    public Double getQuantityOfLand() {
        return quantityOfLand;
    }

    public void setQuantityOfLand(Double quantityOfLand) {
        this.quantityOfLand = quantityOfLand;
    }

    public Double getPlantingTypes() {
        return plantingTypes;
    }

    public void setPlantingTypes(Double plantingTypes) {
        this.plantingTypes = plantingTypes;
    }

    @Override
    public String toString() {
        return "Farmer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                "quantityOfLand=" + quantityOfLand +
                ", plantingTypes=" + plantingTypes +
                '}';
    }
}
