package by.bsu.finalproject.entity;

public class Trainer implements Entity {

    private int id;
    private int workExperience;
    private String trainingType;
    private String Name;

    public Trainer() {
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getWorkExperience() {
        return workExperience;
    }

    public void setWorkExperience(int workExperience) {
        this.workExperience = workExperience;
    }


    @Override
    public String toString() {
        return
                "id ='" + id + '\'' +
                ", workExperience=" + workExperience +
                ", trainingType='" + trainingType + '\'' ;
    }
}
