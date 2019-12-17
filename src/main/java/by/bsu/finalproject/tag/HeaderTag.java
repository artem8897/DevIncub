package by.bsu.finalproject.tag;

import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;


public class HeaderTag extends TagSupport {

    /**
     * Custom tag for user role.
     * @author A. Kuzmik
     */

    @Override
    public int doStartTag() throws JspException {

        User user = (User) pageContext.getSession().getAttribute(ParamName.USER_SESSION);
        try {
            JspWriter out = pageContext.getOut();
            out.write(user.getUsername() + "  </b>" + user.getUserType() + " </b><hr/>" );
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
