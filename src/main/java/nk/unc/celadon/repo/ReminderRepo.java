/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package nk.unc.celadon.repo;

import java.util.List;
import nk.unc.celadon.model.Reminder;

/**
 *
 * @author neil
 */
public interface ReminderRepo {

    /**
     * Adds a Reminder to this ReminderRepo.
     *
     * @param reminder the Reminder to add
     */
    void addReminder(Reminder reminder);

    /**
     * Gets the reminder for a specified identifier
     *
     * @param id the reminder identifier
     * @return the reminder
     */
    Reminder getReminder(String id);

    /**
     * Gets all the Reminders in this ReminderRepo.
     *
     * @return the list of Reminders
     */
    List<Reminder> getReminders();

    /**
     * Deletes a specified Reminder from the ReminderRepo
     *
     * @param id the unique identifier of the Reminder
     * @return ca copy of the deleted Reminder
     */
    Reminder deleteReminder(String id);
    
    /**
     * Update a specified reminder in this ReminderRepo.
     * 
     * @param reminder the Reminder to update
     */
    void updateReminder(Reminder reminder);

}
