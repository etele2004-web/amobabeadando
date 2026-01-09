package hu.uni.amoba.Modell;

public class Palya {
    private int sorok;
    private int oszlopok;
    private Jel[][] tabla; // Sima 2D tömb

    public Palya(int sorok, int oszlopok) {
        this.sorok = sorok;
        this.oszlopok = oszlopok;
        this.tabla = new Jel[sorok][oszlopok];

        // Két for ciklussal feltöltjük üreessel
        for (int i = 0; i < sorok; i++) {
            for (int j = 0; j < oszlopok; j++) {
                tabla[i][j] = Jel.URES;
            }
        }
    }

    public void lerak(int sor, int oszlop, Jel jel) {
        tabla[sor][oszlop] = jel;
    }

    public Jel getMezo(int sor, int oszlop) {
        // Egyszerű ellenőrzés: benne vagyunk-e a keretben?
        if (sor < 0 || sor >= sorok) return null;
        if (oszlop < 0 || oszlop >= oszlopok) return null;

        return tabla[sor][oszlop];
    }

    public int getSorok() { return sorok; }
    public int getOszlopok() { return oszlopok; }

    // Így írja ki a pályát egy kezdő:
    public String toString() {
        String kimenet = "  A B C D E F G H I J\n"; // Fejléc fixen

        for (int i = 0; i < sorok; i++) {
            kimenet = kimenet + (i + 1) + " "; // Sor száma
            if (i < 9) kimenet = kimenet + " "; // Hogy szépen álljon a 10-es

            for (int j = 0; j < oszlopok; j++) {
                kimenet = kimenet + tabla[i][j] + " ";
            }
            kimenet = kimenet + "\n"; // Új sor
        }
        return kimenet;
    }
}