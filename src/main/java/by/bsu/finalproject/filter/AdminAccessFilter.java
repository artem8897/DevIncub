package by.bsu.finalproject.filter;

import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;

import java.io.IOException;
import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Page redirect for illegal access to admin Filter
 * @author A. Kuzmik
 */

@WebFilter(filterName = "TrainerFilter", urlPatterns = { "/jsp/admin/*" }, dispatcherTypes = { DispatcherType.FORWARD,
        DispatcherType.REQUEST })

public class AdminAccessFilter implements Filter{

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

