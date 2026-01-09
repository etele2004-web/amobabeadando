package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.Koordinata;
import hu.uni.amoba.Modell.Jel;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JatekSzolgaltatasTest {

    private JatekSzolgaltatas jatekSzolgaltatas;

    @BeforeEach
    void elokeszites() {
        jatekSzolgaltatas = new JatekSzolgaltatas(10, 10);
    }

    @Test
    void tesztLepesValid() {
        Koordinata k = new Koordinata(5, 5);
        boolean eredmeny = jatekSzolgaltatas.lepesTetele(k, Jel.X);

        assertTrue(eredmeny, "Az első lépésnek sikeresnek kell lennie.");
        assertEquals(Jel.X, jatekSzolgaltatas.getPalya().getMezoErtek(5, 5));
    }

    @Test
    void tesztLepesPalyanKivul() {
        Koordinata k = new Koordinata(15, 0);
        boolean eredmeny = jatekSzolgaltatas.lepesTetele(k, Jel.X);

        assertFalse(eredmeny, "Túlindexelésnél false-t várunk.");
    }

    @Test
    void tesztLepesFoglaltMezore() {
        Koordinata k = new Koordinata(5, 5);
        jatekSzolgaltatas.lepesTetele(k, Jel.X);

        boolean eredmeny = jatekSzolgaltatas.lepesTetele(k, Jel.O);
        assertFalse(eredmeny, "Foglalt mezőre nem léphetünk.");
    }
}