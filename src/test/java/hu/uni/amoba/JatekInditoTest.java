package hu.uni.amoba;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class JatekinditoTest {

    private final InputStream eredetiIn = System.in;
    private final PrintStream eredetiOut = System.out;
    private final ByteArrayOutputStream kimenetAblak = new ByteArrayOutputStream();

    @BeforeEach
    public void setup() {
        System.setOut(new PrintStream(kimenetAblak));
    }

    @AfterEach
    public void cleanup() {
        System.setIn(eredetiIn);
        System.setOut(eredetiOut);
    }

    @Test
    void tesztEgyRovidJatekMentesKilepessel() {
        String szimulaltBemenet =
                "TesztElek\n" +
                        "n\n" +
                        "5 C\n" +
                        "RosszFormatum\n" +
                        "mentes\n";

        System.setIn(new ByteArrayInputStream(szimulaltBemenet.getBytes()));
        JatekIndito.main(new String[]{});

        String konzolSzoveg = kimenetAblak.toString();

        assertTrue(konzolSzoveg.contains("Neved:"), "Kérnie kell a nevet");
        assertTrue(konzolSzoveg.contains("Nem jo lepes!") || konzolSzoveg.contains("Rossz formatum!"), "Kezelnie kell a hibás bemenetet");
        assertTrue(konzolSzoveg.contains("Elmentve."), "Sikeresen el kellett mentenie és kilépnie");
    }
}