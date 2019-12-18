package by.bsu.finalproject.filter;

import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Page redirect for illegal access Filter
 * @author A. Kuzmik
 */

@WebFilter(filterName = "TrainerFilter", urlPatterns = { "/jsp/trainer/*" }, dispatcherTypes = { DispatcherType.FORWARD,
        DispatcherType.REQUEST })
public class TrainerAccessFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String role = (String) httpRequest.getSession().getAttribute(ParamName.PARAM_NAME_USER_TYPE);

        if (role == null || !role.toUpperCase().equals(UserType.ADMIN.toString())) {
            RequestDispatcher dispatcher = httpRequest.getServletContext()
                    .getRequestDispatcher(ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE));
            dispatcher.forward(httpRequest, httpResponse);
        } else {
            chain.doFilter(httpRequest, httpResponse);
        }
    }

    public void destroy() {
    }
}
