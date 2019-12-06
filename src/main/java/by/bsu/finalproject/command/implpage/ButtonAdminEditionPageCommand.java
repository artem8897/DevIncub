package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

public class ButtonAdminEditionPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

//        try {
//            HttpSession session = request.getSession(true);
//            User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
//            InformationServiceImpl informationService = new InformationServiceImpl();
//            Map<Integer, PersonInformation> personInformationMap;
////            switch (user.getUserType()) {
//                case ADMIN: personInformationMap = informationService.findAllPersonalInformationMap();
//                break;
//                case TRAINER: personInformationMap = informationService.findStudentsByTrainer(user.getId(), "all");
//                break;
//                default: throw new EnumConstantNotPresentException(UserType.class, "wrong user type");
//            }
//            request.setAttribute(ParamName.ALL_USER_ATTRIBUTE, personInformationMap);
        int userId = Integer.parseInt(request.getParameter("user_id"));
        request.setAttribute("user_id",userId);
            String page = ConfigurationManager.getProperty(PathName.ADMIN_EDITION_PAGE);
            return page;
//        } catch ( LogicException e) {
//            throw new CommandException(e);
//        }
    }
}
