package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ButtonDiscountPageCommand implements ActionCommand {

    private static final String DATE_PATTERN = "yyyy-MM-dd";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        LocalDateTime localDateTimeMin = LocalDateTime.now().plusDays(18);
        LocalDateTime localDateTimeMax = LocalDateTime.now().plusMonths(2);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);

        String stringDataMin = dateTimeFormatter.format(localDateTimeMin);
        String stringDataMax = dateTimeFormatter.format(localDateTimeMax);

        request.setAttribute("min",stringDataMin);
        request.setAttribute("max",stringDataMax);

        return ConfigurationManager.getProperty(PathName.PATH_PAGE_DISCOUNT);
    }
}
