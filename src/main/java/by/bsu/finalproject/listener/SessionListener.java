package by.bsu.finalproject.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionListener implements HttpSessionAttributeListener {

   Logger logger = LogManager.getLogger();

    public void attributeRemoved(HttpSessionBindingEvent ev) {
    }
    public void attributeAdded(HttpSessionBindingEvent ev) {

        logger.info("add: " + ev.getClass().getSimpleName() + " : "+ ev.getName()
                + " : " + ev.getValue());
    }
    public void attributeReplaced(HttpSessionBindingEvent ev) {

        logger.info("replace: " + ev.getClass().getSimpleName() + " : " + ev.getName()
                + " : " + ev.getValue());
    }
}
