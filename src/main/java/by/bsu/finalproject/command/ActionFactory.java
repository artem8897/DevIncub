package by.bsu.finalproject.command;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.CommandType;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.implpage.ButtonLoginPageCommand;
import by.bsu.finalproject.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    public static ActionCommand defineCommand(HttpServletRequest request) {
        //todo точно ли
        ActionCommand current =  null;
        String action = request.getParameter(ParamName.PARAM_NAME_COMMAND);
        if (action == null || action.isEmpty()) {
            return new ButtonLoginPageCommand();
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            request.setAttribute("wrongAction", action
                    + MessageManager.getProperty("message.wrongaction"));
        }
        return current;
    }
}