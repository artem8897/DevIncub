//package by.bsu.finalproject.filter;
//
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
//@WebFilter(urlPatterns = { "/controller" }, servletNames = { "MainServlet" })
//public class ServletSecurityFilter implements Filter {
//    public void init(FilterConfig fConfig) {
//    }
//        public void doFilter(ServletRequest request, ServletResponse response,
//                             FilterChain chain) throws IOException, ServletException {
//            HttpServletRequest req = (HttpServletRequest) request;
//            HttpServletResponse resp = (HttpServletResponse) response;
//            HttpSession session = req.getSession();
//            User user = (User) session.getAttribute("User");
//            UserType type = user.getUserType();
//            if (type == null) {
//               RequestDispatcher dispatcher = request.getServletContext()
//                        .getRequestDispatcher("/jsp/login.jsp");
//                dispatcher.forward(req, resp);
//                return;
//            }
//            // pass the request along the filter chain
//            chain.doFilter(request, response);
//        }
//    public void destroy() {
//    }
//
//}
