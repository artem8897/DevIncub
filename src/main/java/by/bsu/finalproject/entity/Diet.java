package by.bsu.finalproject.entity;

public class Diet implements Entity {

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
    public String toString() {
        return "dietType='" + dietType + '\'' +
                ", id=" + id +
                ", proteins=" + proteins +
                ", fats=" + fats +
                ", carbohydrates=" + carbohydrates;
    }
}
