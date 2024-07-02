package pl.bryczek.creditcard.pl.bryczek.creditcard;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class HelloTest {
    @Test
    void helloTest() {
        //A / Arrange
        var a = 2;
        var b = 4;

        //A / Act
        var result = a + b;

        //A / Assert
        assert 6 == result;

    }

    @Test
    void itfailed() {

        //A / Arrange
        var a = 2;
        var b = 4;

        //A / Act
        var result = a + b;

        //A / Assert
        assert 10 == result;
    }
    @Test
    void itGreetUsername(){
        // Arrange
        String name = "Bartek";
        // Act
        String message= String.format("Hello %s", name);
        // Assert
        assertEquals("Hello Bartek", message);
    }
}
