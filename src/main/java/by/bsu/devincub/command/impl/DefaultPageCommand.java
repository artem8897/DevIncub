package by.bsu.devincub.command.impl;

import by.bsu.devincub.command.ActionCommand;
import by.bsu.devincub.command.PathName;
import by.bsu.devincub.manager.ConfigurationManager;
import javax.servlet.http.HttpServletRequest;

public class DefaultPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) {
        return ConfigurationManager.getProperty(PathName.PATH_MAIN_PAGE);
    }
}
