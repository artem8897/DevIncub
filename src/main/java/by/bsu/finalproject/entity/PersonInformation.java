package by.bsu.finalproject.entity;

public class PersonInformation implements Entity {

    private int id;
    private String name;
    private String secondName;
    private String sex;
    private int weight;
    private int height;
    private int paidTraining;

    public PersonInformation() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getPaidTraining() {
        return paidTraining;
    }

    public void setPaidTraining(int paidTraining) {
        this.paidTraining = paidTraining;
    }


    @Override
    public String toString() {
        return "id='" + id + '\'' + "</td></tr>"+
                ", name='" + name + '\'' + "</td></tr>"+
                ", secondName='" + secondName + '\'' + "</td></tr>"+
                ", sex='" + sex + '\'' + "</td></tr>"+
                ", weight='" + weight + '\'' + "</td></tr>"+
                ", height='" + height + '\'';
    }
}
