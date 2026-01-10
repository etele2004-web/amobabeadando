package hu.uni.amoba.Modell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PalyaTest {

    @Test
    void tesztUresPalyaLetrehozasa() {
        // Létrehozunk egy 10x10-es pályát
        Palya palya = new Palya(10, 10);

        // Ellenőrizzük, hogy tényleg üres-e mindenhol
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                assertEquals(Jel.URES, palya.getMezo(i, j));
            }
        }
    }

    @Test
    void tesztJelLerakas() {
        Palya palya = new Palya(10, 10);

        // Lerakunk egy jelet középre (5. sor, 5. oszlop)
        palya.lerak(5, 5, Jel.X);

        // Ellenőrizzük, hogy ott van-e
        assertEquals(Jel.X, palya.getMezo(5, 5));

        // Ellenőrizzük, hogy máshol továbbra is üres-e
        assertEquals(Jel.URES, palya.getMezo(0, 0));
    }

    @Test
    void tesztHatarertekKezeles() {
        Palya palya = new Palya(10, 10);

        // A kódodban a getMezo 'null'-t ad vissza, ha rossz indexet kérünk.
        // Ezt teszteljük itt le:
        assertNull(palya.getMezo(-1, 0));  // Túl kicsi index
        assertNull(palya.getMezo(10, 10)); // Túl nagy index
    }
}