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
        palyaTorlese();
    }

    private void palyaTorlese() {
        for (int i = 0; i < sorokSzama; i++) {
            Arrays.fill(racs[i], Jel.URES);
        }
    }

    public void jelElhelyezese(Koordinata koord, Jel jel) {
        // Feltételezzük, hogy az ellenőrzés már megtörtént a Szolgáltatásban
        racs[koord.sor()][koord.oszlop()] = jel;
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
        StringBuilder kimenet = new StringBuilder();
        kimenet.append("  ");
        for (int j = 0; j < oszlopokSzama; j++) {
            kimenet.append((char)('A' + j)).append(" ");
        }
        kimenet.append("\n");
        for (int i = 0; i < sorokSzama; i++) {
            kimenet.append(i + 1).append(i < 9 ? " " : "");
            for (int j = 0; j < oszlopokSzama; j++) {
                kimenet.append(racs[i][j].getKarakterKod()).append(" ");
            }
            kimenet.append("\n");
        }
        return kimenet.toString();
    }
}