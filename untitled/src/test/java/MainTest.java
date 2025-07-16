import org.example.Main;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MainTest {
    @Test
    void testAdd() {
        assertEquals(5, Main.add(2, 3));
        assertEquals(0, Main.add(-2, 2));
        assertEquals(-5, Main.add(-2, -3));
    }
}
