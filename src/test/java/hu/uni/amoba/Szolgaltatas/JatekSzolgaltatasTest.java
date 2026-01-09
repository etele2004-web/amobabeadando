package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JatekSzolgaltatasTest {
    @Test
    void tesztNyeres() {
        JatekSzolgaltatas s = new JatekSzolgaltatas(10, 10);
        for(int i=0; i<4; i++) s.lepes(new Koordinata(0, i), Jel.X);
        assertTrue(s.nyertE(Jel.X));
    }

    @Test
    void tesztRosszLepes() {
        JatekSzolgaltatas s = new JatekSzolgaltatas(10, 10);
        assertFalse(s.lepes(new Koordinata(20, 0), Jel.X));
    }
}