package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Student;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.StudentServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Go to page for create users training command
 * @author A. Kuzmik
 */


public class EditAppointmentPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page;
        HttpSession session = request.getSession();
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if (user != null) {

            int noOfRecords;
            String currentPageString = (request.getParameter(ParamName.CURRENT_PAGE));
            String recordPageString = (request.getParameter(ParamName.RECORDS_PER_PAGE));
            String type = request.getParameter(ParamName.TYPE);
            StudentServiceImpl informationService = new StudentServiceImpl();

            try {
                int trainerId = user.getId();

                if (ParamName.TRAINING.equals(type)) {
                    noOfRecords = informationService.findNumberOfRowsStudentsWithPaidTraining(trainerId);
                    request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.NO_TRAINING_EXIST));
                } else if (ParamName.DIET.equals(type)) {
                    noOfRecords = informationService.findNumberOfRowsStudentsWithNoDiet(trainerId);
                    request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.NO_DIET_EXIST));
                } else {
                    noOfRecords = informationService.findNumberOfRows();
                }
                Map<Integer, Student> personInformationMap = informationService.findStudentsByTrainer(trainerId, type, currentPageString, recordPageString);

                if (!personInformationMap.isEmpty()) {
                    int currentPage = Integer.parseInt(currentPageString);
                    int recordsPerPage = Integer.parseInt(recordPageString);
                    int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                    if (noOfPages % recordsPerPage > 0) {
                        noOfPages++;
                    }
                    page = ConfigurationManager.getProperty(PathName.PATH_PAGE_CHOOSE_STUDENT);
                    request.setAttribute(ParamName.TYPE, type);
                    request.setAttribute(ParamName.NUMBER_OF_PAGES, noOfPages);
                    request.setAttribute(ParamName.CURRENT_PAGE, currentPage);
                    request.setAttribute(ParamName.RECORDS_PER_PAGE, recordsPerPage);
                    request.setAttribute(ParamName.PERSONAL_INFORMATION, personInformationMap);
                } else {
                    if(user.getUserType() == UserType.ADMIN){
                        page = ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
                    }else{
                        page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER);
                    }
                }
            } catch (LogicException e) {
                throw new CommandException(e);
            }
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}
