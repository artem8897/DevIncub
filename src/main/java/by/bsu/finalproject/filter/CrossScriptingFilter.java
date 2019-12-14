package by.bsu.finalproject.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(urlPatterns = { "/*" },  initParams = {
        @WebInitParam(name = "xss_protect", value = "XSS", description = "protect xss") })
public class CrossScriptingFilter implements Filter {

    private String filterConfig;
    private static final String XSS_PROTECT = "xss_protect";

    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig.getInitParameter(XSS_PROTECT);
    }

    public void destroy() {
        this.filterConfig = null;
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        chain.doFilter(new RequestWrapper((HttpServletRequest) request), response);

    }
}
