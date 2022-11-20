package nk.unc.celadon.model;

/**
 * Represents a reminder.
 *
 * @author N☰IL
 */
public record Reminder(String title, String notes, boolean completed, ReminderInfo info) {

    public Reminder(String title, String notes, boolean completed) {
        this(title, notes, completed, null);
    }

    public Reminder(String title, String notes) {
        this(title, notes, false, null);
    }

}
