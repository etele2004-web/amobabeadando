package hu.uni.amoba;

import hu.uni.amoba.Modell.Koordinata;
import hu.uni.amoba.Modell.Jel;
import hu.uni.amoba.Szolgaltatas.JatekSzolgaltatas;

import java.io.IOException;
import java.util.Scanner;

public class JatekIndito {

    public static void main(String[] args) {
        System.out.println("=== MAGYAR AMŐBA JÁTÉK ===");

        Scanner olvaso = new Scanner(System.in);
        JatekSzolgaltatas jatek = new JatekSzolgaltatas(10, 10);

        System.out.print("Játékos neve: ");
        String jatekosNev = olvaso.nextLine();
        System.out.println("Üdv " + jatekosNev + "! Te vagy az X.");

        System.out.println("Betöltés fájlból? (i/n)");
        if (olvaso.nextLine().equalsIgnoreCase("i")) {
            try {
                jatek.jatekBetoltese("mentes.txt");
            } catch (IOException e) {
                System.out.println("Nincs mentés, új játék indul.");
            }
        }

        boolean jatekFut = true;

        while (jatekFut) {
            System.out.println(jatek.getPalya());

            System.out.println("Add meg a lépést (pl. 5 C): ");
            try {
                String sorSzoveg = olvaso.nextLine();
                String[] darabok = sorSzoveg.trim().split("\\s+");

                int sorIndex = Integer.parseInt(darabok[0]) - 1;
                int oszlopIndex = oszlopBetubolSzamma(darabok[1]);

                Koordinata koord = new Koordinata(sorIndex, oszlopIndex);

                if (jatek.lepesTetele(koord, Jel.X)) {
                    System.out.println("Sikeres lépés.");

                    System.out.println("Gép gondolkodik...");
                    jatek.gepiLepes();

                    try {
                        jatek.jatekMentese("automatikus_mentes.txt");
                    } catch (IOException e) {
                        System.out.println("Mentési hiba.");
                    }
                }
            } catch (Exception e) {
                System.out.println("Hibás formátum! Helyes példa: 5 C");
            }
        }
    }

    private static int oszlopBetubolSzamma(String betu) {
        return betu.toUpperCase().charAt(0) - 'A';
    }
}