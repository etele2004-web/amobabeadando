package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.Jel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JatekSzolgaltatasTest {

    @Test
    void tesztVizzsintesNyeres() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();
        jatek.lepes(0, 0, Jel.X);
        jatek.lepes(0, 1, Jel.X);
        jatek.lepes(0, 2, Jel.X);
        jatek.lepes(0, 3, Jel.X);

        assertTrue(jatek.nyertE(Jel.X));
    }

    @Test
    void tesztFuggolegesNyeres() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();
        jatek.lepes(0, 0, Jel.O);
        jatek.lepes(1, 0, Jel.O);
        jatek.lepes(2, 0, Jel.O);
        jatek.lepes(3, 0, Jel.O);

        assertTrue(jatek.nyertE(Jel.O));
    }

    @Test
    void tesztAtlosNyeres() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();
        jatek.lepes(0, 0, Jel.X);
        jatek.lepes(1, 1, Jel.X);
        jatek.lepes(2, 2, Jel.X);
        jatek.lepes(3, 3, Jel.X);

        assertTrue(jatek.nyertE(Jel.X));
    }

    @Test
    void tesztFoglaltMezoreNemLehetLepni() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();

        jatek.lepes(5, 5, Jel.X);

        boolean sikerult = jatek.lepes(5, 5, Jel.O);

        assertFalse(sikerult);
    }

    @Test
    void tesztPalyanKivuliLepes() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();

        assertFalse(jatek.lepes(-1, 0, Jel.X));
        assertFalse(jatek.lepes(0, 20, Jel.X));
    }

    @Test
    void tesztNincsNyertes() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();
        jatek.lepes(0, 0, Jel.X);
        jatek.lepes(0, 1, Jel.X);
        jatek.lepes(0, 2, Jel.X);

        assertFalse(jatek.nyertE(Jel.X));
    }
}