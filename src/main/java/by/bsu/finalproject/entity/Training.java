package by.bsu.finalproject.entity;

import java.io.Serializable;

public class Training implements Serializable, Cloneable {

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Training training = (Training) o;

        if (id != training.id) return false;
        if (trainingType != null ? !trainingType.equals(training.trainingType) : training.trainingType != null)
            return false;
        if (periodicity != null ? !periodicity.equals(training.periodicity) : training.periodicity != null)
            return false;
        if (personality != null ? !personality.equals(training.personality) : training.personality != null)
            return false;
        return status != null ? status.equals(training.status) : training.status == null;
    }

    @Override
    public int hashCode() {
        int result = trainingType != null ? trainingType.hashCode() : 0;
        result = 31 * result + (periodicity != null ? periodicity.hashCode() : 0);
        result = 31 * result + (personality != null ? personality.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
