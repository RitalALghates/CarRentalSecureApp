import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.cccy225.carrental.security.PasswordHasher;

class PasswordHasherTest {

    @Test
    void testHashIsGenerated() {
        String hash = PasswordHasher.hash("Rital@2210683");

        assertNotNull(hash);
        assertFalse(hash.isEmpty());
    }

    @Test
    void testHashIsDifferentFromPassword() {
        String password = "Rital@2210683";
        String hash = PasswordHasher.hash(password);

        assertNotEquals(password, hash);
    }

    @Test
    void testSamePasswordCreatesSameHash() {
        String hash1 = PasswordHasher.hash("Rital@2210683");
        String hash2 = PasswordHasher.hash("Rital@2210683");

        assertEquals(hash1, hash2);
    }
}