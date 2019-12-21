package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.TrainingServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Go to page for updating users training command
 * @author A. Kuzmik
 */


public class UpdatingTrainingPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page;
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if (user != null) {

            TrainingServiceImpl trainingService = new TrainingServiceImpl();
            int trainingId = Integer.parseInt(request.getParameter(ParamName.TRAINING));

            try {
                Map<String, String> mapTraining = trainingService.findTrainingById(trainingId);

                if (mapTraining == null) {
                    page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER);
                    request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.NO_TRAINING_EXIST));
                } else {
                    request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.UPDATE);
                    request.setAttribute(ParamName.TRAINING, mapTraining);
                    request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
                    page = ConfigurationManager.getProperty(PathName.PATH_TRAINING_PAGE);
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}
