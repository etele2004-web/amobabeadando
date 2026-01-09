package hu.uni.amoba.Modell;

import java.util.Arrays;

public class Palya {
    private final int sorokSzama;
    private final int oszlopokSzama;
    private final Jel[][] racs;

    public Palya(int sorokSzama, int oszlopokSzama) {
        this.sorokSzama = sorokSzama;
        this.oszlopokSzama = oszlopokSzama;
        this.racs = new Jel[sorokSzama][oszlopokSzama];
        for (int i = 0; i < sorokSzama; i++) {
            Arrays.fill(racs[i], Jel.URES);
        }
    }

    public void jelElhelyezese(Koordinata k, Jel jel) {
        racs[k.sor()][k.oszlop()] = jel;
    }

    public Jel getMezoErtek(int sor, int oszlop) {
        if (sor < 0 || sor >= sorokSzama || oszlop < 0 || oszlop >= oszlopokSzama) {
            return null;
        }
        return racs[sor][oszlop];
    }

    public int getSorokSzama() { return sorokSzama; }
    public int getOszlopokSzama() { return oszlopokSzama; }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("  ");
        for (int j = 0; j < oszlopokSzama; j++) {
            sb.append((char)('A' + j)).append(" ");
        }
        sb.append("\n");
        for (int i = 0; i < sorokSzama; i++) {
            sb.append(i + 1).append(i < 9 ? " " : "");
            for (int j = 0; j < oszlopokSzama; j++) {
                sb.append(racs[i][j]).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}