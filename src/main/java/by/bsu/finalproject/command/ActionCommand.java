package by.bsu.finalproject.command;

import by.bsu.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;
public interface ActionCommand {
    String execute(HttpServletRequest request) throws CommandException;
}