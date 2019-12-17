package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Go to page for updating trainer information command
 * @author A. Kuzmik
 */


public class ButtonTrainerPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        try {
            HttpSession session = request.getSession(true);
            User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
            int userId;
            if (user.getUserType() == UserType.TRAINER) {
                userId = user.getId();
            }else {
                userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
            }
            TrainerServiceImpl trainerService = new TrainerServiceImpl();
            Map <String, String> trainer = trainerService.findTrainerInformation(userId);
            request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.UPDATE);
            request.setAttribute(ParamName.USER_ID,userId);
            request.setAttribute(ParamName.TRAINER_ATTRIBUTE,trainer);
            request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER_INFORMATION);
    }
}
