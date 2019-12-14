package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DiscountPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YY");
        Date todaysDate = new Date();
        Date noTimeDate;
        try {
           noTimeDate = dateFormat.parse(dateFormat.format(todaysDate));
        } catch (ParseException e) {
            throw new CommandException(e);
        }
        request.setAttribute("min",noTimeDate);
        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_DISCOUNT);
        return page;
    }
}
