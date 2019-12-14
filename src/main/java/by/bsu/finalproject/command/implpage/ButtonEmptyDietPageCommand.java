package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;
import javax.servlet.http.HttpServletRequest;


public class ButtonEmptyDietPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        int id = Integer.parseInt(request.getParameter(ParamName.USER_ID));
        request.setAttribute(ParamName.USER_ID, id);
        request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.ADD);
        page = ConfigurationManager.getProperty(PathName.PATH_DIET_PAGE);

        return page;
    }
}
