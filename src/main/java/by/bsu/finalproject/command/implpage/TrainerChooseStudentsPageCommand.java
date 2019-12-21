package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.Student;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.ServiceException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.StudentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Go to page for updating trainer command
 * @author A. Kuzmik
 */

public class TrainerChooseStudentsPageCommand implements ActionCommand {

    private StudentServiceImpl informationService = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page;
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if (user != null) {

            int noOfRecords;
            String currentPageString = (request.getParameter(ParamName.CURRENT_PAGE));
            String recordPageString = (request.getParameter(ParamName.RECORDS_PER_PAGE));
            Map<Integer, Student> userMap;

            try {
                userMap = informationService.findLimitTrainerMap(currentPageString, recordPageString, user.getId());
                noOfRecords = informationService.findNumberOfRowsStudentsWithPaidTraining(user.getId());
            } catch (ServiceException e) {
                throw new CommandException(e);
            }

            if (!userMap.isEmpty()) {
                int currentPage = Integer.parseInt(currentPageString);
                int recordsPerPage = Integer.parseInt(recordPageString);
                request.setAttribute(ParamName.NUMBER_OF_PAGES, noOfRecords);
                request.setAttribute(ParamName.CURRENT_PAGE, currentPage);
                request.setAttribute(ParamName.RECORDS_PER_PAGE, recordsPerPage);
                request.setAttribute(ParamName.PERSONAL_INFORMATION, userMap);
                page = ConfigurationManager.getProperty(PathName.PATH_TRAINER_EDITION_USER);
            } else {
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.WRONG_ACTION));
                page = ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
            }
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}
