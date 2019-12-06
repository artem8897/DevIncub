package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ButtonAdminEditionTrainerCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        try {
            TrainerServiceImpl trainerService = new TrainerServiceImpl();
            Map<Integer, Trainer> personInformationMap = trainerService.findAllTrainerMap();

            request.setAttribute(ParamName.ALL_TRAINER_ATTRIBUTE, personInformationMap);

            String page = ConfigurationManager.getProperty(PathName.ADMIN_EDITION_TRAINER);
            return page;
        } catch ( LogicException e) {
            throw new CommandException(e);
        }
    }
}
