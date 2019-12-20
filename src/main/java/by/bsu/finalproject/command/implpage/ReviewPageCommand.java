package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Go to page for creation users review command
 * @author A. Kuzmik
 */

public class ReviewPageCommand implements ActionCommand {

    private TrainerServiceImpl trainerService = new TrainerServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        try {
            Map<Integer, Trainer> trainerMap = trainerService.findAllTrainerMap();
            request.setAttribute(ParamName.TRAINER_ATTRIBUTE, trainerMap);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
         return ConfigurationManager.getProperty(PathName.PATH_PAGE_USER_REVIEW);
    }

}
