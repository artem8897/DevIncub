package by.bsu.finalproject.command;

import by.bsu.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Main interface for the Command pattern implementation
 */

public interface ActionCommand {

    /**
     * Gets command name as request string.
     * @return request string.
     */

    String execute(HttpServletRequest request) throws CommandException;
}