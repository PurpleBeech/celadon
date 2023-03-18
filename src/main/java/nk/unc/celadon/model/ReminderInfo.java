package nk.unc.celadon.model;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Extra information about a reminder
 * @author N☰IL
 */
public record ReminderInfo(LocalDate date, LocalTime time, ReminderPriority priority, String tags) {
}
