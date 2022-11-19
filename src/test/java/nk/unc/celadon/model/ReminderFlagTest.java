package nk.unc.celadon.model;

import java.awt.Color;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 * Responsible for testing ReminderFlag record.
 *
 * @author N☰IL
 */
class ReminderFlagTest {

    /**
     * Tests the ReminderFlag#toString() method.
     */
    @Test
    void testToString() {
       ReminderFlag testCase = new ReminderFlag(true, new Color(255, 255, 255));
       assertEquals("ReminderFlag[set=true, color=java.awt.Color[r=255,g=255,b=255]]", testCase.toString(), "test toString");
    }
}
