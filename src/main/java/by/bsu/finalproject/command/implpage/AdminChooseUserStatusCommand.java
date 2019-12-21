package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.PaymentServiceImpl;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to page for change users payment status command
 * @author A. Kuzmik
 */

public class AdminChooseUserStatusCommand implements ActionCommand {

    private PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        int userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
        try {
            request.setAttribute(ParamName.USER_ID, userId);
            request.setAttribute(ParamName.STATUS,paymentService.selectStatuses());
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(PathName.ADMIN_CHOOSE_STUDENT_STATUS_PAGE);

    }
}
