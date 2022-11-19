package nk.unc.celadon.model;

import java.time.LocalDate;

/**
 * Represents a reminder.
 *
 * @author N☰IL
 */
public record Reminder(String title, String notes, LocalDate schedule, ReminderFlag flag) {
}
