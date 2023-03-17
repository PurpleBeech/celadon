package nk.unc.celadon.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import nk.unc.celadon.model.EditorAction;
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

    private final List<Reminder> reminders = new ArrayList<>();
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
        ctx.setVariable("reminders", reminders);
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
        final String titleInput = request.getParameter("titleInput");
        final String notesInput = request.getParameter("notesInput");
        final String completedCheck = request.getParameter("completedCheck");
        final boolean completed = completedCheck != null && "completed".equalsIgnoreCase(completedCheck);

        final String actionParam = request.getParameter("action");
        LOGGER.info("createReminder action={}", actionParam);
        String nextView = "reminder-editor";
        try {
            EditorAction action = EditorAction.valueOf(actionParam);
                        switch (action) {
                case CANCEL:
                    nextView = "reminders";
                    break;
                case SAVE:
                    Reminder reminder = new Reminder(titleInput, notesInput, completed);
                    LOGGER.info("createReminder reminder={}", reminder);
                    reminders.add(reminder);
                    nextView = "reminders";
                    break;
                default:
                    break;
            }
        } catch (Exception ex) {
            // no action or invalid action
        }

        try {
            ctx.setVariable("reminders", reminders);
            templateEngine.process(nextView, ctx, response.getWriter());
        } catch (IOException ioe) {
            LOGGER.error("createReminder exception", ioe);
        }

    }

}
