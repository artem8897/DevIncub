package by.bsu.finalproject.entity;

import java.io.Serializable;

public class Trainer {

    private int id;
    private int workExperience;
    private String trainingType;
    private String trainerName;

    public Trainer() {
    }

    public String getTrainerName() {
        return trainerName;
    }

    public void setTrainerName(String trainerName) {
        this.trainerName = trainerName;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Trainer trainer = (Trainer) o;

        if (id != trainer.id) return false;
        if (workExperience != trainer.workExperience) return false;
        if (trainingType != null ? !trainingType.equals(trainer.trainingType) : trainer.trainingType != null)
            return false;
        return trainerName != null ? trainerName.equals(trainer.trainerName) : trainer.trainerName == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + workExperience;
        result = 31 * result + (trainingType != null ? trainingType.hashCode() : 0);
        result = 31 * result + (trainerName != null ? trainerName.hashCode() : 0);
        return result;
    }
}
