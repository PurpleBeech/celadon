package nk.unc.celadon.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Responsible for testing the Reminder record.
 *
 * @author N☰IL
 */
class ReminderTest {

    /**
     * The Logger used by this ReminderTest.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReminderTest.class);

    /**
     * Test the Reminder#toString method.
     */
    @Test
    public void testToString() {
        Reminder testReminder = new Reminder("Test title", "Test notes");
        LOGGER.info("testToString testReminder={}", testReminder);
        assertEquals("Reminder[title=Test title, notes=Test notes, completed=false, info=null]", testReminder.toString(), "test title and notes");
    }
}
