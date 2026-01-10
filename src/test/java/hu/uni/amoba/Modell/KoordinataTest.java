package hu.uni.amoba.Modell;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KoordinataTest {

    @Test
    void tesztHelyesKoordinata() {
        // Kipróbáljuk, hogy működik-e a létrehozás
        Koordinata k = new Koordinata(5, 10);

        assertEquals(5, k.sor());
        assertEquals(10, k.oszlop());
    }

    @Test
    void tesztEgyenloseg() {
        // Ellenőrizzük, hogy két azonos koordináta egyenlő-e
        Koordinata k1 = new Koordinata(2, 3);
        Koordinata k2 = new Koordinata(2, 3);

        assertEquals(k1, k2);
        assertEquals(k1.hashCode(), k2.hashCode());

        // Ellenőrizzük a toString-et is (ez is növeli a lefedettséget)
        assertNotNull(k1.toString());
    }
}