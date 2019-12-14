package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.dao.impl.TrainerDaoImpl;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;

import javax.servlet.http.HttpServletRequest;
public class ButtonReviewPageCommand implements ActionCommand {

     @Override

    public String execute(HttpServletRequest request) throws CommandException {
        try {
            TrainerServiceImpl trainerService = new TrainerServiceImpl();
            request.setAttribute(ParamName.TRAINER_ATTRIBUTE,trainerService.findAllTrainerMap());
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_USER_REVIEW);
        return page;
    }

}
