package by.bsu.devincub.command;

import by.bsu.devincub.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public interface ActionCommand {

    String execute(HttpServletRequest request) throws CommandException;
}