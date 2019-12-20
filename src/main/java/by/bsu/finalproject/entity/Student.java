package by.bsu.finalproject.entity;

import java.io.Serializable;

public class Student implements Serializable, Cloneable {

    private int id;
    private String name;
    private String secondName;
    private String sex;
    private int weight;
    private int height;
    private int paidTraining;
    private String paidStatus;

    public Student() {
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

    public String getPaidStatus() {
        return paidStatus;
    }

    public void setPaidStatus(String paidStatus) {
        this.paidStatus = paidStatus;
    }

    @Override
    public String toString() {
        return "id='" + id + '\''+
                ", name='" + name + '\''+
                ", secondName='" + secondName + '\''+
                ", sex='" + sex + '\'' +
                ", weight='" + weight + '\''+
                ", height='" + height + '\'';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Student that = (Student) o;

        if (id != that.id) return false;
        if (weight != that.weight) return false;
        if (height != that.height) return false;
        if (paidTraining != that.paidTraining) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        return sex != null ? sex.equals(that.sex) : that.sex == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (sex != null ? sex.hashCode() : 0);
        result = 31 * result + weight;
        result = 31 * result + height;
        result = 31 * result + paidTraining;
        return result;
    }
}
