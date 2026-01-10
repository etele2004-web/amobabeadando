package hu.uni.amoba.Szolgaltatas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdatbazisKezeloTest {

    @Test
    void tesztAdatbazisMukodes() {
        // Létrehozzuk az osztályt (ez lefuttatja a tábla létrehozását is)
        AdatbazisKezelo db = new AdatbazisKezelo();

        // Megpróbálunk menteni egy teszt felhasználót
        // Nem dobhat hibát (Exception)
        assertDoesNotThrow(() -> db.mentes("TesztBela"));

        // Megpróbáljuk listázni
        assertDoesNotThrow(() -> db.listazas());
    }
}