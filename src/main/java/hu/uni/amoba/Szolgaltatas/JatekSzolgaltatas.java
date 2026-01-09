package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.*;
import java.util.Random;

public class JatekSzolgaltatas {
    private Palya palya;
    private final AdatbazisKezelo db;
    private final Random rand = new Random();

    public JatekSzolgaltatas(int n, int m) {
        this.palya = new Palya(n, m);
        this.db = new AdatbazisKezelo();
    }

    public Palya getPalya() { return palya; }
    public void setPalya(Palya p) { this.palya = p; }
    public AdatbazisKezelo getDb() { return db; }

    public boolean lepes(Koordinata k, Jel jel) {
        if (!szabalyosE(k)) return false;
        palya.jelElhelyezese(k, jel);
        return true;
    }

    public boolean szabalyosE(Koordinata k) {
        if (palya.getMezoErtek(k.sor(), k.oszlop()) == null) return false; // pályán kívül
        if (palya.getMezoErtek(k.sor(), k.oszlop()) != Jel.URES) return false; // foglalt

        // Ha üres a pálya, bárhova léphet (első lépés)
        boolean uresPalya = true;
        cimke: for(int i=0; i<palya.getSorokSzama(); i++)
            for(int j=0; j<palya.getOszlopokSzama(); j++)
                if(palya.getMezoErtek(i, j) != Jel.URES) { uresPalya = false; break cimke; }

        if (uresPalya) return true;

        // Szomszéd vizsgálat
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i==0 && j==0) continue;
                Jel szomszed = palya.getMezoErtek(k.sor()+i, k.oszlop()+j);
                if (szomszed != null && szomszed != Jel.URES) return true;
            }
        }
        return false;
    }

    public void gepiLepes() {
        for(int i=0; i<1000; i++) {
            Koordinata k = new Koordinata(rand.nextInt(palya.getSorokSzama()), rand.nextInt(palya.getOszlopokSzama()));
            if (szabalyosE(k)) {
                palya.jelElhelyezese(k, Jel.O);
                System.out.println("Gép lépése: " + (k.sor()+1) + " " + (char)('A'+k.oszlop()));
                return;
            }
        }
    }

    public boolean nyertE(Jel jel) {
        for (int i = 0; i < palya.getSorokSzama(); i++) {
            for (int j = 0; j < palya.getOszlopokSzama(); j++) {
                if (palya.getMezoErtek(i, j) == jel) {
                    if (check(i, j, 0, 1, jel) || check(i, j, 1, 0, jel) ||
                            check(i, j, 1, 1, jel) || check(i, j, 1, -1, jel)) return true;
                }
            }
        }
        return false;
    }

    private boolean check(int r, int c, int dr, int dc, Jel jel) {
        for (int k = 1; k < 4; k++) { // +3 további mezőt nézünk (összesen 4)
            if (palya.getMezoErtek(r + k*dr, c + k*dc) != jel) return false;
        }
        return true;
    }
}