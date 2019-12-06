package by.bsu.finalproject.entity;

public class Training implements Entity {

    private String trainingType;
    private String periodicity;
    private String personality;
    private int id;
    private String status;

    public Training() {
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDate() {
        return periodicity;
    }

    public void setPeriodicity(String periodicity) {
        this.periodicity = periodicity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getTrainingType() {
        return trainingType;
    }

    public void setTrainingType(String trainingType) {
        this.trainingType = trainingType;
    }

    public String getPersonality() {
        return personality;
    }

    public void setPersonality(String personality) {
        this.personality = personality;
    }

    @Override
    public String toString() {
        return "trainingType='" + trainingType + '\'' +
                ", periodicity='" + periodicity + '\'' +
                ", id=" + id;
    }
}
