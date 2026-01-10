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
        // 1. Készítünk egy pályát és rakunk rá jeleket
        Palya eredeti = new Palya(10, 10);
        eredeti.lerak(0, 0, Jel.X);
        eredeti.lerak(5, 5, Jel.O);

        // 2. Elmentjük fájlba
        XmlKezelo.mentes(eredeti, "TesztElek");

        // 3. Visszatöltjük
        Palya betoltott = XmlKezelo.betoltes();

        // 4. Ellenőrizzük, hogy ugyanazok-e az adatok
        assertEquals(Jel.X, betoltott.getMezo(0, 0));
        assertEquals(Jel.O, betoltott.getMezo(5, 5));
        assertEquals(Jel.URES, betoltott.getMezo(1, 1)); // Üresnek is stimmelnie kell
    }

    @Test
    void tesztHibasFajl() {
        // Megpróbálunk betölteni, ha nincs fájl (vagy töröljük előtte)
        new File("mentes.xml").delete();

        // Nem szabad összeomlania, csak üres pályát adnia
        assertDoesNotThrow(() -> XmlKezelo.betoltes());
    }
}