package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.PersonInformation;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.InformationServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ButtonStudentInformationPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
        Map attributeMap;
        int userId;
        if (user.getUserType() == UserType.USER) {
            userId = user.getId();
        }else {
            userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
        }
        try {
            InformationServiceImpl informationService = new InformationServiceImpl();
            attributeMap = informationService.findPersonalInformation(userId);
            //@todo Напиши если не нашёл инфу, запррос есть ли
            request.setAttribute(ParamName.STUDENTS, attributeMap);
            request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
            request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.UPDATE);
            request.setAttribute(ParamName.USER_ID,userId);
        } catch ( LogicException e) {
            throw new CommandException(e);
        }
        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_INFORMATION);
        return page;
    }
}
