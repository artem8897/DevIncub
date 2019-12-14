package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.PersonInformation;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.InformationServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ButtonStudentInformationPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page;
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

            if(!attributeMap.isEmpty()){
                page  = ConfigurationManager.getProperty(PathName.PATH_PAGE_INFORMATION);
                request.setAttribute(ParamName.STUDENTS, attributeMap);
                request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
                request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.UPDATE);
                request.setAttribute(ParamName.USER_ID,userId);
            }else{
                page = ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.NO_USERS_EXIST));
            }

        } catch ( LogicException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
