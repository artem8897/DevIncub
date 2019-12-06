package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.dao.impl.TrainerDaoImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.DaoException;

import javax.servlet.http.HttpServletRequest;
public class ButtonReviewPageCommand implements ActionCommand {

     @Override

    public String execute(HttpServletRequest request) throws CommandException {
        try {
            TrainerDaoImpl trainerDao = new TrainerDaoImpl();
            request.setAttribute(ParamName.TRAINER_ATTRIBUTE,trainerDao.findAll());
        } catch (DaoException e) {
            throw new CommandException(e);
        }
        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_USER_REVIEW);
        return page;
    }

}
