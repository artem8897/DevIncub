package by.bsu.devincub.command;

import by.bsu.devincub.command.impl.DefaultPageCommand;
import javax.servlet.http.HttpServletRequest;

public class ActionFactory {

    private static final String PARAM_NAME_COMMAND = "command";

    private ActionFactory(){
    }

    public static ActionCommand defineCommand(HttpServletRequest request) {
        ActionCommand current ;
        String action = request.getParameter(PARAM_NAME_COMMAND);

        if (action == null || action.isEmpty()) {
            return new DefaultPageCommand();
        }
        try {
            CommandType currentEnum = CommandType.valueOf(action.toUpperCase());
            current = currentEnum.getCurrentCommand();
        } catch (IllegalArgumentException e) {
            current = new DefaultPageCommand();
        }

        return current;
    }
}