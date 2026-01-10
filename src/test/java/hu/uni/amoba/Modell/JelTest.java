package hu.uni.amoba.Modell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JelTest {

    @Test
    void tesztToString() {
        assertEquals("X", Jel.X.toString());
        assertEquals("O", Jel.O.toString());
        assertEquals(".", Jel.URES.toString());
    }

    @Test
    void tesztErtekek() {
        assertEquals(3, Jel.values().length);
    }
}