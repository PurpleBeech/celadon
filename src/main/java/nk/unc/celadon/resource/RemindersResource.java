package nk.unc.celadon.resource;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import java.time.LocalDate;
import nk.unc.celadon.model.EditorAction;
import nk.unc.celadon.model.Reminder;
import nk.unc.celadon.repo.ReminderRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author N☰IL
 */
@Path("/reminders")
@Controller
public class RemindersResource //extends CeladonViewController
{

    /**
     * The logger used by this ReminderResource.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(RemindersResource.class);

    @Inject
    private Models models;

    @Inject
    ReminderRepo repo;

    /**
     *
     */
    @GET
    @Produces("text/html")
    @View("reminders.html")
    public void getReminders() {
        models.put("reminders", repo.getReminders());
    }

    @POST
    @View("reminders.html")
    public void createReminder(@FormParam("title") String title,
            @FormParam("notes") String notes,
            @FormParam("completed") boolean completed, 
            @FormParam("action") EditorAction action) {

        String today = LocalDate.now().toString();
        LOGGER.info("createReminder today={}", today);
        models.put("today", today);

        if (EditorAction.SAVE == action) {
            Reminder reminder = new Reminder(title, notes, completed);
            LOGGER.info("createReminder reminder={}", reminder);
            repo.addReminder(reminder);
            models.put("reminders", repo.getReminders());
        }
    }

    @GET
    @Path("editor")
    @View("reminder-editor.html")
    public void getEditor() {
        String today = LocalDate.now().toString();
        LOGGER.info("getEditor today={}", today);
        models.put("today", today);
    }

}
