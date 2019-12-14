//package by.bsu.finalproject.filter;
//
//import by.bsu.finalproject.command.ParamName;
//import by.bsu.finalproject.command.PathName;
//import by.bsu.finalproject.entity.User;
//import by.bsu.finalproject.entity.UserType;
//
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;
//import java.io.IOException;
//
//@WebFilter(urlPatterns = { "/controller" })
//public class ServletSecurityFilter implements Filter {
//    public void init(FilterConfig fConfig) {
//    }
//        public void doFilter(ServletRequest request, ServletResponse response,
//                             FilterChain chain) throws IOException, ServletException {
//            HttpServletRequest req = (HttpServletRequest) request;
//            HttpServletResponse resp = (HttpServletResponse) response;
//            HttpSession session = req.getSession(true);
//            String userType = (String) session.getAttribute(ParamName.PARAM_NAME_USER_TYPE);
//            if (userType == null) {
//               RequestDispatcher dispatcher = request.getServletContext()
//                        .getRequestDispatcher(PathName.PATH_LOGIN_PAGE);
//                dispatcher.forward(req, resp);
//                return;
//            }
//            chain.doFilter(request, response);
//        }
//    public void destroy() {
//    }
//
//}
