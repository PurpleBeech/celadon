package nk.unc.celadon.repo;

import jakarta.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import nk.unc.celadon.config.IdHelper;
import nk.unc.celadon.model.Reminder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author neil
 */
@ApplicationScoped
public class MockReminderRepo implements ReminderRepo {
    /**
     * The logger used by this MockReminderRepo.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(MockReminderRepo.class);

    private int repoId = 0; 
    
    private final Map<String, Reminder> mockRepo;
    
    public MockReminderRepo() {
        super();
        mockRepo = Collections.synchronizedMap(new HashMap<String,Reminder>(64));
    }
    
    @Override
    public void addReminder(Reminder reminder) {
        final String idValue = IdHelper.generateHash();
        Reminder createdReminder = reminder.forId(idValue);
        LOGGER.info("addReminder createdReminder={}",createdReminder);
        mockRepo.put(idValue, createdReminder);
    }

    @Override
    public List<Reminder> getReminders() {
        LOGGER.info("getReminders repo size={}",mockRepo.size());
        return Collections.unmodifiableList(new ArrayList<>(mockRepo.values()));
    }

    @Override
    public Reminder deleteReminder(final String id) {
        LOGGER.info("deleteReminder id={}", id);
        final Reminder deletedReminder = mockRepo.remove(id);
        LOGGER.info("deleteReminder size={}", mockRepo.size());
        LOGGER.info("deleteReminder deletedReminder={}", deletedReminder);
        return deletedReminder;
    }

    @Override
    public Reminder getReminder(String id) {
        return mockRepo.get(id);
    }

    @Override
    public void updateReminder(Reminder reminder) {
        final String id = reminder.id();
        mockRepo.replace(id, reminder);
    }
    
}
