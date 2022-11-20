package nk.unc.celadon.resource;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Context;
import java.io.IOException;
import java.io.Writer;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.IServletWebApplication;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author neil
 */
@Path("/index")
public class IndexResource {

    /**
     * The logger used by this ReminderResource.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RemindersResource.class);

    private HttpServletRequest request;

    private HttpServletResponse response;

    private JakartaServletWebApplication thymeLeafApp;
    
    private TemplateEngine templateEngine;

    /**
     * Provide response configuration of:
     *
     * <ul>
     * <li>contentType</li>
     * <li>headers</li>
     * </ul>
     */
    private void configResponse() {
        if (response == null) {
            LOGGER.warn("configResponse we have a NULL response:(");
        } else {
            LOGGER.info("configResponse response looks OK");
            response.setContentType("text/html;charset=UTF-8");
            response.setHeader("Pragma", "no-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
        }
    }

    @GET
    public void getIndex() {
        LOGGER.info("getIndex");
        configResponse();
        final IWebExchange webExchange = thymeLeafApp.buildExchange(request, response);
        try {
            WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
            ctx.setVariable("today", LocalDate.now().toString());
            final Writer writer = response.getWriter();
            templateEngine.process("index", ctx, writer);
        } catch (IOException ioe) {
            LOGGER.error("", ioe);
        }
    }

    /**
     * @param request the request to set
     */
    @Context
    public void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    /**
     * @param templateEngine the templateEngine to set
     */
    @Inject
    public void setTemplateEngine(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    /**
     * @param thymeLeafApp the resource to set
     */
    @Inject
    public void setThymeLeafApp(JakartaServletWebApplication thymeLeafApp) {
        LOGGER.info("setResource thymeLeafApp is NULL={}", thymeLeafApp == null);
        this.thymeLeafApp = thymeLeafApp;
    }

    /**
     * @param response the response to set
     */
    @Context
    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }

}
