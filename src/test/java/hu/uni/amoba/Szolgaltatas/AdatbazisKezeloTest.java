package hu.uni.amoba.Szolgaltatas;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AdatbazisKezeloTest {

    @Test
    void tesztAdatbazisMukodes() {
        AdatbazisKezelo db = new AdatbazisKezelo();

        assertDoesNotThrow(() -> db.mentes("TesztBela"));

        assertDoesNotThrow(() -> db.listazas());
    }
}