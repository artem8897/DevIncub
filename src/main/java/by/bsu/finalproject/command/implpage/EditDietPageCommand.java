//package by.bsu.finalproject.command.implpage;
//
//import by.bsu.finalproject.command.ActionCommand;
//import by.bsu.finalproject.command.PathName;
//import by.bsu.finalproject.command.ParamName;
//import by.bsu.finalproject.entity.Diet;
//import by.bsu.finalproject.entity.User;
//import by.bsu.finalproject.entity.UserType;
//import by.bsu.finalproject.manager.ConfigurationManager;
//import by.bsu.finalproject.service.impl.DietServiceImpl;
//import by.bsu.finalproject.exception.CommandException;
//import by.bsu.finalproject.exception.LogicException;
//
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
//
//public class EditDietPageCommand implements ActionCommand {
//    @Override
//    public String execute(HttpServletRequest request) throws CommandException {
//
//        try {
//            HttpSession session = request.getSession(true);
//            User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
//            int userId;
//            if (user.getUserType() == UserType.USER) {
//                userId = user.getId();
//            }else {
//                userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
//            }
//            DietServiceImpl dietService = new DietServiceImpl();
//            Diet diet = dietService.findUsersDiet(userId);
//            request.setAttribute(ParamName.PROTEINS,diet.getProteins());
//            request.setAttribute(ParamName.FATS,diet.getFats());
//            request.setAttribute( ParamName.DIET_TYPE,diet.getDietType());
//            request.setAttribute(ParamName.CARBOHYDRATES,diet.getCarbohydrates());
//        } catch ( LogicException e) {
//            throw new CommandException(e);
//        }
//        request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.UPDATE);
//        String page = ConfigurationManager.getProperty(PathName.PATH_DIET_PAGE);
//        return page;
//    }
//}
