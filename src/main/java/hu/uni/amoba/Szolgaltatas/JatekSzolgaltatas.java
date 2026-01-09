package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.Palya;
import hu.uni.amoba.Modell.Koordinata;
import hu.uni.amoba.Modell.Jel;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Random;

public class JatekSzolgaltatas {

    private final Palya palya;
    private final Random veletlenGenerator = new Random();

    public JatekSzolgaltatas(int n, int m) {
        this.palya = new Palya(n, m);
    }

    public Palya getPalya() {
        return palya;
    }

    public boolean lepesTetele(Koordinata koord, Jel jel) {
        if (!szabalyosLepesE(koord)) {
            System.out.println("Hiba: Érvénytelen lépés (foglalt vagy szabálytalan).");
            return false;
        }
        palya.jelElhelyezese(koord, jel);
        return true;
    }


    public boolean szabalyosLepesE(Koordinata koord) {
        int sor = koord.sor();
        int oszlop = koord.oszlop();

        if (sor < 0 || sor >= palya.getSorokSzama() ||
                oszlop < 0 || oszlop >= palya.getOszlopokSzama()) {
            return false;
        }

        if (palya.getMezoErtek(sor, oszlop) != Jel.URES) {
            return false;
        }

        if (palyaUresE()) {
            return true;
        }

        return vanSzomszedja(sor, oszlop);
    }

    private boolean palyaUresE() {
        for(int i = 0; i < palya.getSorokSzama(); i++) {
            for(int j = 0; j < palya.getOszlopokSzama(); j++) {
                if(palya.getMezoErtek(i, j) != Jel.URES) return false;
            }
        }
        return true;
    }

    private boolean vanSzomszedja(int sor, int oszlop) {
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;
                Jel szomszed = palya.getMezoErtek(sor + i, oszlop + j);
                if (szomszed != null && szomszed != Jel.URES) {
                    return true;
                }
            }
        }
        return false;
    }

    public void gepiLepes() {
        int probalkozas = 0;
        while (probalkozas < 1000) {
            int sor = veletlenGenerator.nextInt(palya.getSorokSzama());
            int oszlop = veletlenGenerator.nextInt(palya.getOszlopokSzama());
            Koordinata koord = new Koordinata(sor, oszlop);

            if (szabalyosLepesE(koord)) {
                palya.jelElhelyezese(koord, Jel.O);
                System.out.println(">>> A gép lépése: " + (sor + 1) + " " + (char)('A' + oszlop));
                return;
            }
            probalkozas++;
        }
        System.out.println("A gép nem tudott lépni.");
    }

    public void jatekMentese(String fajlnev) throws IOException {
        String tartalom = palya.toString();
        Files.writeString(Path.of(fajlnev), tartalom);
    }

    public void jatekBetoltese(String fajlnev) throws IOException {
        List<String> sorok = Files.readAllLines(Path.of(fajlnev));
        System.out.println("DEBUG: Betöltés funkció meghívva: " + fajlnev);
    }
}