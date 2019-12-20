package by.bsu.finalproject.command;

import by.bsu.finalproject.command.impl.*;
import by.bsu.finalproject.command.implpage.*;

/**
 * Storage all commands
 * @author Artem Kuzmik
 */

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
            this.command = new CreatingDiscountPageCommand();
        }
    },
    GO_TO_PASSWORD{
        {
           this.command = new ChangePasswordPageCommand();
        }
    },
    GOTOREGISTRATION {
        {
            this.command = new RegistrationPageCommand();
        }
    },
    GOTOLOGIN {
        {
            this.command = new LoginPageCommand();
        }
    },
    GO_TO_TRAINER{
        {
            this.command = new TrainerMainPageCommand();
        }
    },
    GO_TO_ADMIN{
        {
            this.command = new AdminMainPageCommand();
        }
    },
    ADDINFORMATIONCOMMAND{
        {
            this.command = new CreateStudentInformationCommand();
        }
    },
    UPDATE_USER_INFORMATION_PAGE {
        {
            this.command = new UpdatingStudentInformationPageCommand();
        }
    },
    ADD_DIET{
        {
            this.command = new CreateDietCommand();
        }

    },
    ADD_PERSONAL_TRAINING{
        {
            this.command = new CreatePersonalTrainingCommand();
        }
    },
    ADD_DISCOUNT{
        {
            this.command = new CreateDiscountCommand();
        }
    },
    EMPTY_REVIEW{
        {
            this.command = new ReviewPageCommand();
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
            this.command = new CreatingTrainingPageCommand();
        }
    },
    ADMIN_STATUS{
        {
            this.command = new AdminChooseUserStatusCommand();
        }
    },
    BUTTON_DIET_PAGE{
        {
            this.command = new EditDietPageCommand();
        }
    },
    USER_INFORMATION_UPDATE{
        {
            this.command = new UpdateUserInformationCommand();
        }
    },
    CHANGE_USER_STATUS{
        {
            this.command = new UpdateUserStatusCommand();
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
            this.command = new AdminEditStudentPageCommand();
        }
    },
    EDITION_TRAINER{
        {
            this.command = new AdminEditTrainerPageCommand();
        }
    },
    UPDATE_TRAINER_INFORMATION{
        {
            this.command = new UpdateTrainerInformationCommand();
        }
    },
    TRAINER_PAGE {
        {
            this.command = new UpdatingTrainerInformationPageCommand();
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
            this.command = new CreatingStudentInformationPageCommand();
        }
    },
    TRAINING_PAGE{
        {
            this.command = new UpdatingTrainingPageCommand();
        }
    },
    ALL_USERS_TRAINING_PAGE{
        {
            this.command = new UsersTrainingsPageCommand();
        }
    },
    TRAINER_EDITION{
        {
            this.command = new TrainerChooseStudentsPageCommand();
        }
    },
    CHOOSE_USER_FOR_CREATING_DATA{
        {
            this.command = new EditAppointmentPageCommand();
        }
    },
    EMPTYDIET{
        {
            this.command= new CreatingDietPageCommand();
        }
    },
    SHOW_ALL_TRAINERS{
        {
            this.command = new ShowAllTrainersCommand();
        }
    },
    EMPTY_TRAINER{
        {
            this.command = new CreatingTrainerInformationPageCommand();
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
            this.command = new CalculatePriceForTrainingCommand();
        }
    },
    PAY{
        {
            this.command = new PayCommand();
        }
    },
    ADMIN_CHOOSE_PERSON{
        {
            this.command = new AdminChooseStudentPageCommand();
        }
    },
    ADMIN_EDIT_USER{
        {
            this.command = new AdminEditUserStatusesPageCommand();
        }
    },
    STUDENT_ACC{
        {
            this.command = new StudentMainPageCommand();
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
