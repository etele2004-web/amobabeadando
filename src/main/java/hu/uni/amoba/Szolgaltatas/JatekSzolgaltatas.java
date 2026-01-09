package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.*;
import java.util.Random;

public class JatekSzolgaltatas {
    private Palya palya;
    private AdatbazisKezelo adatbazis;
    private Random random;

    public JatekSzolgaltatas() {
        this.palya = new Palya(10, 10);
        this.adatbazis = new AdatbazisKezelo();
        this.random = new Random();
    }

    public Palya getPalya() { return palya; }
    public void setPalya(Palya p) { this.palya = p; }
    public AdatbazisKezelo getAdatbazis() { return adatbazis; }

    public boolean lepes(int sor, int oszlop, Jel jel) {
        if (szabalyosE(sor, oszlop) == false) {
            return false;
        }
        palya.lerak(sor, oszlop, jel);
        return true;
    }

    public boolean szabalyosE(int sor, int oszlop) {
        if (palya.getMezo(sor, oszlop) == null) return false;
        if (palya.getMezo(sor, oszlop) != Jel.URES) return false;

        boolean ures = true;
        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (palya.getMezo(i, j) != Jel.URES) {
                    ures = false;
                }
            }
        }
        if (ures) return true;

        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if (i == 0 && j == 0) continue;

                Jel szomszed = palya.getMezo(sor + i, oszlop + j);
                if (szomszed != null && szomszed != Jel.URES) {
                    return true;
                }
            }
        }
        return false;
    }

    public void gepiLepes() {
        for (int i = 0; i < 1000; i++) {
            int sor = random.nextInt(10);
            int oszlop = random.nextInt(10);

            if (szabalyosE(sor, oszlop)) {
                palya.lerak(sor, oszlop, Jel.O);
                System.out.println("Gép lépése: " + (sor + 1) + " " + (char)('A' + oszlop));
                return;
            }
        }
    }

    public boolean nyertE(Jel kinek) {
        for (int sor = 0; sor < 10; sor++) {
            for (int oszlop = 0; oszlop < 10; oszlop++) {

                if (palya.getMezo(sor, oszlop) == kinek) {
                    if (ellenoriz(sor, oszlop, 0, 1, kinek)) return true;
                    if (ellenoriz(sor, oszlop, 1, 0, kinek)) return true;
                    if (ellenoriz(sor, oszlop, 1, 1, kinek)) return true;
                    if (ellenoriz(sor, oszlop, 1, -1, kinek)) return true;
                }
            }
        }
        return false;
    }

    private boolean ellenoriz(int sor, int oszlop, int dSor, int dOszlop, Jel kinek) {
        int talalat = 0;
        for (int k = 0; k < 4; k++) {
            int r = sor + k * dSor;
            int c = oszlop + k * dOszlop;
            if (palya.getMezo(r, c) == kinek) {
                talalat++;
            }
        }
        return talalat == 4;
    }
}