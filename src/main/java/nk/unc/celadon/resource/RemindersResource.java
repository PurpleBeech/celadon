package nk.unc.celadon.resource;

import jakarta.inject.Inject;
import jakarta.mvc.Controller;
import jakarta.mvc.Models;
import jakarta.mvc.View;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
        LOGGER.info("getReminders");
        models.put("reminders", repo.getReminders());
    }

    @POST
    @View("reminders.html")
    public void createReminder(@FormParam("id") String id,
            @FormParam("title") String title,
            @FormParam("notes") String notes,
            @FormParam("completed") String completedParam,
            @FormParam("action") EditorAction action) {
        LOGGER.info("createReminder id={}, title={}, notes={}, completedParam={}", id, title, notes, completedParam, action);
        
        if (EditorAction.SAVE == action) {
            boolean completed = Boolean.valueOf(completedParam);
            LOGGER.info("createReminder completed={}", completed);
            if (id == null || id.isEmpty()) {
                Reminder reminder = new Reminder(title, notes, completed);
                LOGGER.info("createReminder create reminder={}", reminder);
                repo.addReminder(reminder);
            } else {
                Reminder reminder = new Reminder(id, title, notes, completed);
                LOGGER.info("createReminder add reminder={}", reminder);
                repo.updateReminder(reminder);
            }
            models.put("reminders", repo.getReminders());
        }
    }

    @GET
    @Path("{id}")
    @View("reminder-editor.html")
    public void getReminder(@PathParam("id") final String id) {
        Reminder reminder = repo.getReminder(id);
        if (reminder == null) {
            LOGGER.info("getEditor reminder is null");
            reminder = new Reminder("", "", false);
        }
        LOGGER.info("getEditor reminder={}", reminder);
        models.put("reminder", reminder);
    }

    @DELETE
    @Path("{id}")
    @View("empty.html")
    public void delete(@PathParam("id") final String id) {
        LOGGER.info("delete id={}", id);
        repo.deleteReminder(id);
    }
}
