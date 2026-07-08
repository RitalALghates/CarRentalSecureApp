import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cccy225.carrental.security.RecordIntegrityProtector;

class RecordIntegrityProtectorTest {

    @Test
    void testCreateChecksumValue() {
        String checksum = RecordIntegrityProtector.createChecksum("rital", "Rital@2210683");

        assertNotNull(checksum);
        assertFalse(checksum.isEmpty());
    }

    @Test
    void testMatchingChecksumForSameData() {
        String checksum = RecordIntegrityProtector.createChecksum("rital", "Rital@2210683");

        boolean result = RecordIntegrityProtector.matches(checksum, "rital", "Rital@2210683");

        assertTrue(result);
    }

    @Test
    void testRejectChangedData() {
        String checksum = RecordIntegrityProtector.createChecksum("rital", "Rital@2210683");

        boolean result = RecordIntegrityProtector.matches(checksum, "rital", "WrongPassword");

        assertFalse(result);
    }
}