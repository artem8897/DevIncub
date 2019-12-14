package by.bsu.finalproject.dao;

import by.bsu.finalproject.dao.impl.*;

public enum DaoFactory {

    INSTANCE;

    private DietDaoImpl dietDao = new DietDaoImpl();
    private PaymentDaoImpl paymentDao = new PaymentDaoImpl();
    private PersonalInformationDaoImpl personalInformationDao = new PersonalInformationDaoImpl();
    private ReviewDaoImpl reviewDao = new ReviewDaoImpl();
    private UserDaoImpl userDao = new UserDaoImpl();
    private TrainingDaoImpl trainingDao = new TrainingDaoImpl();
    private TrainerDaoImpl trainerDao = new TrainerDaoImpl();


    public DietDaoImpl getDietDao(){
        return dietDao;
    }
    public PaymentDaoImpl getPaymentDao(){return paymentDao;}
    public PersonalInformationDaoImpl getPersonalInformationDao(){
        return personalInformationDao;
    }
    public UserDaoImpl getUserDao(){
        return userDao;
    }
    public TrainingDaoImpl getTrainingDao(){
        return trainingDao;
    }
    public TrainerDaoImpl getTrainerDao(){
        return trainerDao;
    }
    public ReviewDaoImpl getReviewDao(){
        return reviewDao;
    }

}
