package by.bsu.finalproject.command;

import by.bsu.finalproject.command.impl.*;
import by.bsu.finalproject.command.implpage.*;

public enum CommandType {
    LOGIN {
        {
            this.command = new LoginCommand();
        }
    },
    DELETE_TRAINING{
        {
            this.command = new DeleteTrainingCommand();
        }
    },
    LOGOUT {
        {
            this.command = new LogoutCommand();
        }
    },
    REGISTRATION {
        {
            this.command = new RegistrationCommand();
        }
    },
    DISCOUNT{
        {
            this.command = new DiscountPageCommand();
        }
    },
    GO_TO_PASSWORD{
        {
           this.command = new ChangePasswordPageCommand();
        }
    },
    GOTOREGISTRATION {
        {
            this.command = new ButtonRegistrationPageCommand();
        }
    },
    GOTOLOGIN {
        {
            this.command = new ButtonLoginPageCommand();
        }
    },
    GO_TO_TRAINER{
        {
            this.command = new TrainerMainAccCommand();
        }
    },
    GO_TO_ADMIN{
        {
            this.command = new AdminMainAccCommand();
        }
    },
    ADDINFORMATIONCOMMAND{
        {
            this.command = new AddStudentInformationCommand();
        }
    },
    UPDATE_USER_INFORMATION_PAGE {
        {
            this.command = new ButtonStudentInformationPageCommand();
        }
    },
    ADD_DIET{
        {
            this.command = new AddDietCommand();
        }

    },
    ADD_PERSONAL_TRAINING{
        {
            this.command = new AddPersonalTrainingCommand();
        }
    },
    ADD_DISCOUNT{
        {
            this.command = new AddDiscountCommand();
        }
    },
    EMPTY_REVIEW{
        {
            this.command = new ButtonReviewPageCommand();
        }
    },
    UPDATE_PAY_STATUS{
        {
            this.command = new UpdateStudentStatus();
        }
    },
    CREATEUSERREVIEW{
        {
            this.command = new CreateUserReviewCommand();
        }
    },
    ERROR_PAGE{
        {
            this.command = new ErrorPageCommand();
        }
    },
    EMPTY_TRAINING_PAGE{
        {
            this.command = new ButtonEmptyTrainingPageCommand();
        }
    },
    ADMIN_STATUS{
        {
            this.command = new AdminChooseUserStatusCommand();
        }
    },
    BUTTON_DIET_PAGE{
        {
            this.command = new ButtonDietPageCommand();
        }
    },
    USER_INFORMATION_UPDATE{
        {
            this.command = new UpdateUserInformationCommand();
        }
    },
    CHANGE_USER_STATUS{
        {
            this.command = new ChangeUserStatusCommand();
        }
    },
    DELETE_USER{
        {
            this.command = new DeleteUserCommand();
        }
    },
    UPDATE_PASSWORD{
        {
            this.command = new UpdatePasswordCommand();
        }
    },
    UPDATE_USERNAME{
        {
            this.command = new UpdateUsernameCommand();
        }
    },
    EDITION_PAGE_COMMAND{
        {
            this.command = new ButtonAdminEditionPageCommand();
        }
    },
    EDITION_TRAINER{
        {
            this.command = new ButtonAdminEditionTrainerCommand();
        }
    },
    UPDATE_TRAINER_INFORMATION{
        {
            this.command = new UpdateTrainerInformationCommand();
        }
    },
    TRAINER_PAGE {
        {
            this.command = new ButtonTrainerPageCommand();
        }
    },
    UPDATE_DIET{
        {
            this.command = new UpdateDietCommand();
        }
    },
    UPDATE_TRAINING{
        {
            this.command = new UpdateTrainingCommand();
        }
    },
    EMPTY_USER_INFORMATION_PAGE {
        {
            this.command = new ButtonEmptyInformationPageCommand();
        }
    },
    TRAINING_PAGE{
        {
            this.command = new ButtonTrainingPageCommand();
        }
    },
    ALL_USERS_TRAINING_PAGE{
        {
            this.command = new ButtonAllUsersTrainingsCommand();
        }
    },
    TRAINER_EDITION{
        {
            this.command = new TrainerEditionCommand();
        }
    },
    CHOOSE_USER_FOR_CREATING_DATA{
        {
            this.command = new PageForCreatingTrainingCommand();
        }
    },
    EMPTYDIET{
        {
            this.command= new ButtonEmptyDietPageCommand();
        }
    },
    SHOW_ALL_TRAINERS{
        {
            this.command = new ShowAllTrainersCommand();
        }
    },
    EMPTY_TRAINER{
        {
            this.command = new ButtonEmptyTrainerPageCommand();
        }
    },
    SHOW_TRAINERS_REVIEWS{
        {
            this.command = new FindTrainersReview();
        }
    },
    GO_TO_CHOOSE_TRAINER{
        {
            this.command = new TrainingOrder();
        }
    },
    GO_TO_PAY {
        {
            this.command = new CalcPriceForTrainingCommand();
        }
    },
    PAY{
        {
            this.command = new PayCommand();
        }
    },
    ADMIN_CHOOSE_PERSON{
        {
            this.command = new AdminChooseStudentCommand();
        }
    },
    ADMIN_EDIT_USER{
        {
            this.command = new ButtonAdminEditionAllCommand();
        }
    },
    STUDENT_ACC{
        {
            this.command = new StudentMainAccCommand();
        }
    },
    CHANGE_LOCALIZATION{
        {
            this.command = new ChangeLocaleCommand();
        }
    };
    ActionCommand command;
    public ActionCommand getCurrentCommand() {
        return command;
    }
}
