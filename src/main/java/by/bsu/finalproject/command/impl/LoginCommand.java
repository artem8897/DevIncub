package by.bsu.finalproject.command.impl;
import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.InformationServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;
import by.bsu.finalproject.service.impl.UserServiceImpl;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Log in application command
 * @author A. Kuzmik
 */

public class LoginCommand implements ActionCommand {

    private static final String MESSAGE_LOGIN_ERROR = "message.loginerror";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        User user;
        String email = request.getParameter(ParamName.PARAM_NAME_EMAIL);
        String pass = request.getParameter(ParamName.PARAM_NAME_PASSWORD);
        UserServiceImpl userService = new UserServiceImpl();
        InformationServiceImpl informationService = new InformationServiceImpl();
        TrainerServiceImpl trainerService = new TrainerServiceImpl();

        try {

            user = userService.findUserInBase(email, pass);

            if (user != null) {
                HttpSession session = request.getSession(true);
                session.setAttribute(ParamName.USER_ATTRIBUTE, user);
                session.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());

                UserType userType = user.getUserType();

                switch (userType) {
                    case USER:
                        if (informationService.isExist(user.getId())) {
                            page = ConfigurationManager.getProperty(PathName.PATH_USER_PAGE);
                        } else {
                            request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.ADD);
                            session.setAttribute(ParamName.PARAM_NAME_USER_TYPE, null);
                            page = ConfigurationManager.getProperty(PathName.PATH_PAGE_INFORMATION);
                        }
                        break;
                    case ADMIN:
                        page = ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
                        break;
                    case TRAINER:
                        if (trainerService.isExist(user.getId())) {
                            page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER);
                        } else {
                            request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.ADD);
                            session.setAttribute(ParamName.PARAM_NAME_USER_TYPE, null);
                            page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER_INFORMATION);
                        }
                        break;
                    default:
                        page = ConfigurationManager.getProperty(PathName.PATH_PAGE_ERROR);
                }
            } else {
                request.setAttribute("errorLoginPassMessage",
                        MessageManager.getProperty(MESSAGE_LOGIN_ERROR));
                page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return page;
    }


}