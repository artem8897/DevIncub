package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.PaymentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Show all discounts
 * @author A. Kuzmik
 */

public class ShowAllDiscountsCommand implements ActionCommand {

    private PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        Map<String, Integer> discountsMap;
        try {
            discountsMap = paymentService.findDiscounts();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if (!discountsMap.isEmpty()) {
            request.setAttribute(ParamName.DISCOUNT, discountsMap);
            return ConfigurationManager.getProperty(PathName.DISCOUNTS_PAGE);
        } else {
            request.setAttribute(ParamName.WRONG_ACTION, MessageManager.getProperty(MessageName.WRONG_ACTION));
            return ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
        }
    }
}