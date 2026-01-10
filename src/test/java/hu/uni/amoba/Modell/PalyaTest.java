package hu.uni.amoba.Modell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalyaTest {

    @Test
    void tesztUresPalyaLetrehozasa() {
        Palya palya = new Palya(10, 10);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(Jel.URES, palya.getMezo(i, j));
            }
        }
    }

    @Test
    void tesztJelLerakas() {
        Palya palya = new Palya(10, 10);

        palya.lerak(5, 5, Jel.X);

        assertEquals(Jel.X, palya.getMezo(5, 5));

        assertEquals(Jel.URES, palya.getMezo(0, 0));
    }

    @Test
    void tesztHatarertekKezeles() {
        Palya palya = new Palya(10, 10);

        assertNull(palya.getMezo(-1, 0));  // Túl kicsi index
        assertNull(palya.getMezo(10, 10)); // Túl nagy index
    }
}