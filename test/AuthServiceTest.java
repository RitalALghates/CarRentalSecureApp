import static org.junit.jupiter.api.Assertions.*;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;

import com.cccy225.carrental.auth.User;
import com.cccy225.carrental.persistence.UserFileRepository;
import com.cccy225.carrental.service.AuthService;

class AuthServiceTest {

    @Test
    void testSuccessfulSignUpAndSignIn() throws Exception {
        Path tempFile = Files.createTempFile("rital-users", ".txt");
        UserFileRepository repository = new UserFileRepository(tempFile);
        AuthService authService = new AuthService(repository);

        authService.signUp("ritalTest", "Rital@2210683");
        User user = authService.signIn("ritalTest", "Rital@2210683");

        assertNotNull(user);
        assertEquals("ritalTest", user.getUsername());
    }

    @Test
    void testSignInWithWrongPassword() throws Exception {
        Path tempFile = Files.createTempFile("rital-users", ".txt");
        UserFileRepository repository = new UserFileRepository(tempFile);
        AuthService authService = new AuthService(repository);

        authService.signUp("ritalTest", "Rital@2210683");

        assertThrows(IllegalArgumentException.class, () -> {
            authService.signIn("ritalTest", "WrongPass123");
        });
    }

    @Test
    void testSignUpWithWeakPassword() throws Exception {
        Path tempFile = Files.createTempFile("rital-users", ".txt");
        UserFileRepository repository = new UserFileRepository(tempFile);
        AuthService authService = new AuthService(repository);

        assertThrows(IllegalArgumentException.class, () -> {
            authService.signUp("ritalTest", "1234");
        });
    }

    @Test
    void testLoginWithNonExistingUser() throws Exception {
        Path tempFile = Files.createTempFile("rital-users", ".txt");
        UserFileRepository repository = new UserFileRepository(tempFile);
        AuthService authService = new AuthService(repository);

        assertThrows(IllegalArgumentException.class, () -> {
            authService.signIn("unknownUser", "AnyPass123");
        });
    }
}