package nk.unc.celadon.resource;

import jakarta.inject.Inject;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

/**
 *
 * @author N☰IL
 */
public class CeladonViewController {

    /**
     * The logger used by this IndexResource.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(CeladonViewController.class);

    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected JakartaServletWebApplication thymeLeafApp;
    protected TemplateEngine templateEngine;

    /**
     * Provide response configuration of:
     *
     * <ul>
     * <li>contentType</li>
     * <li>headers</li>
     * </ul>
     */
    protected void configResponse() {
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

    protected WebContext buildWebContext() {
        LOGGER.info("buildWebContext");
        var webExchange = buildWebExchange();
        return new WebContext(webExchange, webExchange.getLocale());
    }
    
    protected IWebExchange buildWebExchange() {
        LOGGER.info("buildWebExchange");
        return thymeLeafApp.buildExchange(request, response);
    }

}
