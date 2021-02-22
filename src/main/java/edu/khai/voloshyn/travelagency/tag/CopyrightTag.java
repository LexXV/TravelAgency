package edu.khai.voloshyn.travelagency.tag;

import edu.khai.voloshyn.travelagency.util.Message;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;

@SuppressWarnings("serial")
public class CopyrightTag extends TagSupport {
    private static final Logger LOGGER = LogManager.getLogger(CopyrightTag.class.getName());

    @Override
    public int doStartTag() {
        String copyrightInfo = "<p> Copyright Oleksiy Voloshyn © 2021 All Rights Reserved.</p>";
        try {
            JspWriter out = pageContext.getOut();
            out.write(copyrightInfo);
        } catch (IOException e) {
            LOGGER.error(Message.COPYRIGHT_TAG_ERROR, e);
        }
        return SKIP_BODY;
    }

    @Override
    public int doEndTag() {
        return EVAL_PAGE;
    }
}
