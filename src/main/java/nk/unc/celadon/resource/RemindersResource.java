package nk.unc.celadon.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.io.IOException;
import java.time.LocalDate;
import nk.unc.celadon.model.Reminder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;

/**
 *
 * @author N☰IL
 */
@Path("/reminders")
public class RemindersResource extends CeladonViewController {

    /**
     * The logger used by this ReminderResource.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RemindersResource.class);

    /**
     *
     */
    @GET
    public void getReminders() {
        configResponse();
        final IWebExchange webExchange = thymeLeafApp.buildExchange(request, response);

        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        String today = LocalDate.now().toString();
        LOGGER.info("getReminders today={}", today);
        ctx.setVariable("today", today);
        try {
            templateEngine.process("reminders", ctx, response.getWriter());
        } catch (IOException ioe) {
            LOGGER.error("getReminders exception", ioe);
        }
    }

    @POST
    public void createReminder() {
        configResponse();
        final IWebExchange webExchange = thymeLeafApp.buildExchange(request, response);

        WebContext ctx = new WebContext(webExchange, webExchange.getLocale());
        String today = LocalDate.now().toString();
        LOGGER.info("createReminder today={}", today);
        ctx.setVariable("today", today);
        try {
            templateEngine.process("reminder-editor", ctx, response.getWriter());
        } catch (IOException ioe) {
            LOGGER.error("createReminder exception", ioe);
        }

    }

}
