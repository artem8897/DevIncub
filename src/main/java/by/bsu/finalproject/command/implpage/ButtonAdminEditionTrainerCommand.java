package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ButtonAdminEditionTrainerCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page;
        TrainerServiceImpl trainerService = new TrainerServiceImpl();

        try {
            Map<Integer, Trainer> personInformationMap = trainerService.findAllTrainerMap();
            if(!personInformationMap.isEmpty()){
                request.setAttribute(ParamName.ALL_TRAINER_ATTRIBUTE, personInformationMap);
                page = ConfigurationManager.getProperty(PathName.ADMIN_EDITION_TRAINER);
            }else{
                page = ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.NO_TRAINERS_EXIST));
            }
            return page;
        } catch ( LogicException e) {
            throw new CommandException(e);
        }
    }
}
