package nk.unc.celadon.repo;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import nk.unc.celadon.model.Reminder;

/**
 *
 * @author neil
 */
@ApplicationScoped
public class MockReminderRepo implements ReminderRepo {

    private final List<Reminder> repo;
    
    public MockReminderRepo() {
        super();
        repo = Collections.synchronizedList(new ArrayList<>());
    }
    
    @Override
    public void addReminder(Reminder reminder) {  
       repo.add(reminder);
    }

    @Override
    public List<Reminder> getReminders() {
        return Collections.unmodifiableList(repo);
    }

    @Override
    public Reminder deleteReminder(final int id) {
        return repo.remove(id);
    }
    
}
