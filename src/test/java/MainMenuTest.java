

import PersistenceInterface.FloristRepository;
import florist.Florist;
import menu.MainMenu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("json")  // Change to 'mongodb' or 'mysql' to test other implementations
public class MainMenuTest {

    @Autowired
    private MainMenu mainMenu;

    @Autowired
    private FloristRepository floristRepository;

    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @BeforeEach
    public void setUp() {
        // Redirect System.out to capture output for assertions
        System.setOut(new PrintStream(outputStreamCaptor));

        // Clean up the repository before each test to ensure isolation
        //floristRepository.deleteAll();  // Ensure this method exists and works with your repository
    }

    @Test
    public void testCreateFlorist() {
        String input = "1\nFloristName\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        mainMenu.start();

        String output = outputStreamCaptor.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Florist 'FloristName' created "));
    }

    @Test
    public void testCreateFloristWhenAlreadyExists() {
        floristRepository.save(new Florist("FloristName"));

        String input = "1\nFloristName\n0\n";
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes(StandardCharsets.UTF_8));
        System.setIn(in);

        mainMenu.start();

        String output = outputStreamCaptor.toString(StandardCharsets.UTF_8);
        assertTrue(output.contains("Florist already exists: FloristName"));
    }
}
