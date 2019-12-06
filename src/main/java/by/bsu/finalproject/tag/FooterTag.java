package by.bsu.finalproject.tag;

import by.bsu.finalproject.manager.LocaleManager;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.GregorianCalendar;
import java.util.Locale;

public class FooterTag extends TagSupport {

    @Override
    public int doStartTag() throws JspException {
        GregorianCalendar gc = new GregorianCalendar();
        // locale
        pageContext.getSession().getAttribute("local");// тип того
        String footer = LocaleManager.getProperty("label.tag");
        try {
            JspWriter out = pageContext.getOut();
            out.write("<hr/>" + footer + "<b> " + gc.getWeekYear()+ " </b><hr/>" );
        } catch (IOException e) {
            throw new JspException(e.getMessage());
        }
        return SKIP_BODY;
    }
    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }
}
