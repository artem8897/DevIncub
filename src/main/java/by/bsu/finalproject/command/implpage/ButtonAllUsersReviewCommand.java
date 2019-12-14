package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Review;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.ReviewServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ButtonAllUsersReviewCommand implements ActionCommand {

  @Override

    public String execute(HttpServletRequest request) throws CommandException {
        try {
            HttpSession session = request.getSession(true);
            User user = ((User) (session.getAttribute(ParamName.USER_SESSION)));
            int userId;
            if (user.getUserType() == UserType.USER) {
                userId = user.getId();
            } else {
                userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
            }
            Map<Integer, Review> reviewMap;
            ReviewServiceImpl reviewService = new ReviewServiceImpl();

            reviewMap = (reviewService.findReview(userId, UserType.USER));

            request.setAttribute(ParamName.REVIEW, reviewMap);

        } catch (LogicException e) {
            throw new CommandException(e);
        }
         return ConfigurationManager.getProperty(PathName.PATH_PAGE_ALL_USERS_REVIEW);
  }
}
