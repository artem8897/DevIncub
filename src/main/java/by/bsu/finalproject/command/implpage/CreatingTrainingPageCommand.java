package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Crate page for creating users training command
 * @author A. Kuzmik
 */

public class CreatingTrainingPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_TRAINING_PAGE);
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if (user != null) {

            int id = Integer.parseInt(request.getParameter(ParamName.USER_ID));
            request.setAttribute(ParamName.USER_ID, id);
            request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.ADD);
            request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());

        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}
