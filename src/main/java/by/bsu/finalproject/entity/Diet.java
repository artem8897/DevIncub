package by.bsu.finalproject.entity;

import java.io.Serializable;

public class Diet implements Serializable, Cloneable {

    private String dietType;
    private int id;
    private int proteins;
    private int fats;
    private int carbohydrates;
    private String status;

    public Diet() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getProteins() {
        return proteins;
    }

    public void setProteins(int proteins) {
        this.proteins = proteins;
    }

    public int getFats() {
        return fats;
    }

    public void setFats(int fats) {
        this.fats = fats;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(int carbohydrates) {
        this.carbohydrates = carbohydrates;
    }


    public String getDietType() {
        return dietType;
    }

    public void setDietType(String dietType) {
        this.dietType = dietType;
    }

    public int getUsername() {
        return id;
    }

    public void setUsername(int id) {
        this.id = id;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Diet diet = (Diet) o;

        if (id != diet.id) return false;
        if (proteins != diet.proteins) return false;
        if (fats != diet.fats) return false;
        if (carbohydrates != diet.carbohydrates) return false;
        if (dietType != null ? !dietType.equals(diet.dietType) : diet.dietType != null) return false;
        return status != null ? status.equals(diet.status) : diet.status == null;
    }

    @Override
    public int hashCode() {
        int result = dietType != null ? dietType.hashCode() : 0;
        result = 31 * result + id;
        result = 31 * result + proteins;
        result = 31 * result + fats;
        result = 31 * result + carbohydrates;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "dietType='" + dietType + '\'' +
                ", id=" + id +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates;
    }
}
