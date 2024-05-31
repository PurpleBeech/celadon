package nk.unc.celadon.model;

/**
 * Represents a reminder.
 *
 * @author N☰IL
 */
public record Reminder(String id, String title, String notes, boolean completed, ReminderInfo info) {

    public static final String DEFAULT_ID = "";  
    public static final boolean DEFAULT_COMPLETED = false;
    
    public Reminder(String id, String title, String notes, boolean completed) {
        this(id, title, notes, completed, null);
    }

    public Reminder(String id, String title, String notes) {
        this(id, title, notes, DEFAULT_COMPLETED, null);
    }
    public Reminder(String title, String notes, boolean completed) {
        this(DEFAULT_ID, title, notes, completed, null);
    }
    public Reminder(String title, String notes) {
        this(DEFAULT_ID, title, notes, DEFAULT_COMPLETED, null);
    }    
    public Reminder forId(String id) {
        return new Reminder(id, this.title, this.notes, this.completed, this.info);
    }
}
