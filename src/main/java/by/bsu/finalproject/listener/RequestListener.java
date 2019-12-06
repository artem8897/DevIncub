package by.bsu.finalproject.listener;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {

    public void requestInitialized(ServletRequestEvent ev) {

        HttpServletRequest req = (HttpServletRequest) ev.getServletRequest();
        String uri = "Request Initialized for " + req.getRequestURI();
        String id = "Request Initialized with ID="+ req.getRequestedSessionId();
        System.out.println(uri + "\n" + id);
        ServletContext context = ev.getServletContext();

        Integer reqCount = (Integer)req.getSession().getAttribute("counter");
        if(reqCount == null) {
            reqCount = 0;
        }
        context.log(uri + "\n" +id + ", Request Counter =" + reqCount);
    }

    public void requestDestroyed(ServletRequestEvent ev) {
        System.out.println("Request Destroyed: "
                + ev.getServletRequest().getAttribute("lifecycle"));
    }

}
