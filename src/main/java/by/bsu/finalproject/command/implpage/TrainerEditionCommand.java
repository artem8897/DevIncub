package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.PersonInformation;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.service.impl.InformationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class TrainerEditionCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        InformationServiceImpl informationService = new InformationServiceImpl();
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
        Map<Integer, PersonInformation> userMap ;
        int noOfRecords;
        int currentPage = Integer.parseInt(request.getParameter(ParamName.CURRENT_PAGE));
        int recordsPerPage = Integer.parseInt(request.getParameter(ParamName.RECORDS_PER_PAGE));
        try {
            userMap = informationService.findLimitTrainerMap(currentPage,recordsPerPage,user.getId());
            noOfRecords = informationService.findNumberOfRows();
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        if(noOfPages % recordsPerPage > 0){
            noOfPages++;
        }
        request.setAttribute(ParamName.NUMBER_OF_PAGES, noOfPages);
        request.setAttribute(ParamName.CURRENT_PAGE, currentPage);
        request.setAttribute(ParamName.RECORDS_PER_PAGE,recordsPerPage);
        request.setAttribute(ParamName.PERSONAL_INFORMATION,userMap);

        return ConfigurationManager.getProperty(PathName.PATH_TRAINER_EDITION_USER);

    }
}
