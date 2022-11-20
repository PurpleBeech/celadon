package nk.unc.celadon.model;

import jakarta.annotation.Priority;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @author N☰IL
 */
public record ReminderInfo(LocalDate date, LocalTime time, ReminderPriority priority, String tags) {
}
