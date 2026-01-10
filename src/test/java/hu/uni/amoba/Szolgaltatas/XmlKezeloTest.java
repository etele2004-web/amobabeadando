package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.Jel;
import hu.uni.amoba.Modell.Palya;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

class XmlKezeloTest {

    @Test
    void tesztMentesEsBetoltes() {
        Palya eredeti = new Palya(10, 10);
        eredeti.lerak(0, 0, Jel.X);
        eredeti.lerak(5, 5, Jel.O);

        XmlKezelo.mentes(eredeti, "TesztElek");

        Palya betoltott = XmlKezelo.betoltes();

        assertEquals(Jel.X, betoltott.getMezo(0, 0));
        assertEquals(Jel.O, betoltott.getMezo(5, 5));
        assertEquals(Jel.URES, betoltott.getMezo(1, 1)); // Üresnek is stimmelnie kell
    }

    @Test
    void tesztHibasFajl() {
        new File("mentes.xml").delete();

        assertDoesNotThrow(() -> XmlKezelo.betoltes());
    }
}