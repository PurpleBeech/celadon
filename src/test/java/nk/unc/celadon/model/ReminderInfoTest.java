package nk.unc.celadon.model;

import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Responsible for testing the ReminderInfo record.
 *
 * @author N☰IL
 */
class ReminderInfoTest {

    /**
     * The Logger used by this ReminderInfoTest.
     */
    private static final Logger LOGGER = LoggerFactory.getLogger(ReminderInfoTest.class);

    @Test
    void testToString() {
        LocalDate infoDate = LocalDate.of(2025, 1, 1);
        LocalTime infoTime = LocalTime.of(12, 0, 0);
        String testTags = "testTag";
        ReminderInfo testInfo = new ReminderInfo(infoDate, infoTime, ReminderPriority.NONE, testTags);
        LOGGER.info("testToString testInfo={}", testInfo);
        assertEquals("ReminderInfo[date=2025-01-01, time=12:00, priority=NONE, tags=testTag]", testInfo.toString(), "");
    }

}
