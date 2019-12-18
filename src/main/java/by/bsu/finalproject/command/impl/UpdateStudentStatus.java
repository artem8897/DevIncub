package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.InformationServiceImpl;
import by.bsu.finalproject.service.impl.PaymentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Update students pay status command
 * @author A. Kuzmik
 */

public class UpdateStudentStatus implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page  = ConfigurationManager.getProperty(PathName.ADMIN_CHOOSE_STUDENT_STATUS_PAGE);
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if(user != null) {

            int userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
            int status = Integer.parseInt(request.getParameter(ParamName.STATUS));
            String redirect = request.getParameter(ParamName.REDIRECT);
            InformationServiceImpl logic = new InformationServiceImpl();

            boolean wasUpdated;

            try {
                wasUpdated = logic.updatePaymentStatus(userId, status);
                if (wasUpdated) {
                    request.setAttribute(ParamName.REDIRECT, redirect);
                } else {
                    PaymentServiceImpl paymentService = new PaymentServiceImpl();
                    request.setAttribute(ParamName.STATUS, paymentService.selectStatuses());
                    request.setAttribute(ParamName.USER_ID, userId);
                    request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
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
