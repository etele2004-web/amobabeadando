package hu.uni.amoba;

import hu.uni.amoba.Modell.*;
import hu.uni.amoba.Szolgaltatas.*;
import java.util.Scanner;

public class JatekIndito {
    public static void main(String[] args) {
        System.out.println("=== AMŐBA JÁTÉK (MAGYAR) ===");
        Scanner sc = new Scanner(System.in);
        JatekSzolgaltatas jatek = new JatekSzolgaltatas(10, 10);

        jatek.getDb().ranglistaKiirasa();

        System.out.print("Játékos neve: ");
        String nev = sc.nextLine();

        System.out.println("Betöltés fájlból? (i/n)");
        if (sc.nextLine().equalsIgnoreCase("i")) {
            try {
                jatek.setPalya(XmlKezelo.betoltes("mentes.xml"));
                System.out.println("Sikeres betöltés!");
            } catch (Exception e) {
                System.out.println("Hiba a betöltésnél.");
            }
        }

        while (true) {
            System.out.println(jatek.getPalya());
            System.out.println(nev + " (X) lépése (pl: 5 C) vagy 'mentes':");
            String in = sc.nextLine().trim();

            if (in.equalsIgnoreCase("mentes")) {
                try {
                    XmlKezelo.mentes(jatek.getPalya(), nev, "mentes.xml");
                    System.out.println("Mentve!"); break;
                } catch (Exception e) { System.out.println("Mentési hiba."); }
            }

            try {
                String[] s = in.split(" ");
                Koordinata k = new Koordinata(Integer.parseInt(s[0])-1, s[1].toUpperCase().charAt(0)-'A');

                if (jatek.lepes(k, Jel.X)) {
                    if (jatek.nyertE(Jel.X)) {
                        System.out.println(jatek.getPalya());
                        System.out.println("NYERTÉL! ");
                        jatek.getDb().gyozelemMentese(nev);
                        jatek.getDb().ranglistaKiirasa();
                        break;
                    }
                    System.out.println("Gép jön");
                    jatek.gepiLepes();
                    if (jatek.nyertE(Jel.O)) {
                        System.out.println(jatek.getPalya());
                        System.out.println("VESZTETTÉL!");
                        break;
                    }
                } else {
                    System.out.println("Hibás lépés!");
                }
            } catch (Exception e) {
                System.out.println("Formátum hiba! Pl: 5 C");
            }
        }
    }
}