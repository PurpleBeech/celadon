package nk.unc.celadon.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import java.io.IOException;
import java.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

/**
 *
 * @author N☰IL
 */
@Path("/index")
public class IndexResource extends CeladonViewController {

    /**
     * The logger used by this IndexResource.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexResource.class);

    /**
     * 
     */
    @GET
    public void getIndex() {
        configResponse();
        final IWebExchange webExchange = thymeLeafApp.buildExchange(request, response);
        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        String today = LocalDate.now().toString();
        LOGGER.info("getIndex today={}", today);
        ctx.setVariable("today", today);
        try {
            templateEngine.process("index", ctx, response.getWriter());
        } catch (IOException ioe) {
            LOGGER.error("getIndex exception", ioe);
        }
    }

}
