package by.bsu.finalproject.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

/**
 * Request wrapper for Cross Scripting Filter
 * @author A. Kuzmik
 */


public class RequestWrapper extends HttpServletRequestWrapper {

    RequestWrapper(HttpServletRequest servletRequest) {
        super(servletRequest);
    }

    public String getParameter(String parameter) {

        String value = super.getParameter(parameter);

        if (value != null) {
            return cleanXSS(value);
        }
        return null;
    }
    private String cleanXSS(String value) {

        value = value.replaceAll("<", "& lt;").replaceAll(">", "& gt;");
        value = value.replaceAll("\\(", "& #40;").replaceAll("\\)", "& #41;");
        value = value.replaceAll("'", "& #39;");
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("script", "");
        return value;
    }
}
