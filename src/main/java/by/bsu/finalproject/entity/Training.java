package by.bsu.finalproject.entity;

public class Training {

    private String trainingType;
    private String date;
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
        return date;
    }

    public void setDate(String date) {
        this.date = date;
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
                ", periodicity='" + date + '\'' +
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
        if (date != null ? !date.equals(training.date) : training.date != null)
            return false;
        if (personality != null ? !personality.equals(training.personality) : training.personality != null)
            return false;
        return status != null ? status.equals(training.status) : training.status == null;
    }

    @Override
    public int hashCode() {
        int result = trainingType != null ? trainingType.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (personality != null ? personality.hashCode() : 0);
        result = 31 * result + id;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }
}
