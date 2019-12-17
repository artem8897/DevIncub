package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.PersonInformation;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.InformationServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Go to page for create users training command
 * @author A. Kuzmik
 */


public class PageForCreatingTrainingCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page;
        int noOfRecords;
        String currentPageString = (request.getParameter(ParamName.CURRENT_PAGE));
        String recordPageString = (request.getParameter(ParamName.RECORDS_PER_PAGE));
        String type = request.getParameter(ParamName.TYPE);

        try {
            HttpSession session = request.getSession(true);
            User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
            int trainerId = user.getId();
            InformationServiceImpl informationService = new InformationServiceImpl();

            if(type.equals(ParamName.TRAINING)){
                noOfRecords = informationService.findNumberOfRowsStudentsWithPaidTraining(trainerId);
            }else if(type.equals(ParamName.DIET)){
                noOfRecords = informationService.findNumberOfRowsStudentsWithNoDiet(trainerId);
            }else{
                noOfRecords = informationService.findNumberOfRows();
            }
            Map<Integer, PersonInformation> personInformationMap = informationService.findStudentsByTrainer(trainerId, type, currentPageString, recordPageString);

            if(!personInformationMap.isEmpty()) {
                int currentPage = Integer.parseInt(currentPageString);
                int recordsPerPage  = Integer.parseInt(recordPageString);
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                if(noOfPages % recordsPerPage > 0){
                    noOfPages++;
                }
                page = ConfigurationManager.getProperty(PathName.PATH_PAGE_CHOOSE_STUDENT);
                request.setAttribute(ParamName.TYPE, type);
                request.setAttribute(ParamName.NUMBER_OF_PAGES, noOfPages);
                request.setAttribute(ParamName.CURRENT_PAGE, currentPage);
                request.setAttribute(ParamName.RECORDS_PER_PAGE,recordsPerPage);
                request.setAttribute(ParamName.PERSONAL_INFORMATION,personInformationMap);
            }else{
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.WRONG_ACTION));
                page = ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
            }

        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
