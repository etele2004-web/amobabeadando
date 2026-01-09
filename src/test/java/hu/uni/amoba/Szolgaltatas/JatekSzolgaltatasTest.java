package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.Jel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JatekSzolgaltatasTest {

    @Test
    void tesztVizzsintesNyeres() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();
        // X X X X - Vízszintesen
        jatek.lepes(0, 0, Jel.X);
        jatek.lepes(0, 1, Jel.X);
        jatek.lepes(0, 2, Jel.X);
        jatek.lepes(0, 3, Jel.X);

        // Kell, hogy nyerjen
        assertTrue(jatek.nyertE(Jel.X));
    }

    @Test
    void tesztFuggolegesNyeres() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();
        // O
        // O
        // O
        // O
        jatek.lepes(0, 0, Jel.O);
        jatek.lepes(1, 0, Jel.O);
        jatek.lepes(2, 0, Jel.O);
        jatek.lepes(3, 0, Jel.O);

        assertTrue(jatek.nyertE(Jel.O));
    }

    @Test
    void tesztAtlosNyeres() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();
        // Átlósan rakjuk le
        jatek.lepes(0, 0, Jel.X);
        jatek.lepes(1, 1, Jel.X);
        jatek.lepes(2, 2, Jel.X);
        jatek.lepes(3, 3, Jel.X);

        assertTrue(jatek.nyertE(Jel.X));
    }

    @Test
    void tesztFoglaltMezoreNemLehetLepni() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();

        // 1. Lerakunk egy X-et
        jatek.lepes(5, 5, Jel.X);

        // 2. Megpróbálunk ugyanoda rakni egy O-t
        boolean sikerult = jatek.lepes(5, 5, Jel.O);

        // Nem szabad sikerülnie
        assertFalse(sikerult);
    }

    @Test
    void tesztPalyanKivuliLepes() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();

        // Olyan helyre lépünk, ami nincs (pl. -1. sor vagy 20. oszlop)
        assertFalse(jatek.lepes(-1, 0, Jel.X));
        assertFalse(jatek.lepes(0, 20, Jel.X));
    }

    @Test
    void tesztNincsNyertes() {
        JatekSzolgaltatas jatek = new JatekSzolgaltatas();
        // Csak 3 van egymás mellett, nem 4
        jatek.lepes(0, 0, Jel.X);
        jatek.lepes(0, 1, Jel.X);
        jatek.lepes(0, 2, Jel.X);

        assertFalse(jatek.nyertE(Jel.X));
    }
}