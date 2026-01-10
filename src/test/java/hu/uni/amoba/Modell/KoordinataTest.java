package hu.uni.amoba.Modell;

import hu.uni.amoba.Modell.Koordinata;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KoordinataTest {

    @Test
    void tesztHelyesKoordinata() {
        Koordinata k = new Koordinata(5, 5);

        assertEquals(5, k.sor());
        assertEquals(5, k.oszlop());
    }

    @Test
    void tesztNegativKoordinataNemMegengedett() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Koordinata(-1, 5);
        });
    }

    @Test
    void tesztEgyenloseg() {
        Koordinata k1 = new Koordinata(2, 3);
        Koordinata k2 = new Koordinata(2, 3);

        assertEquals(k1, k2);
    }
}