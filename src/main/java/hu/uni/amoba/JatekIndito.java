package hu.uni.amoba;

import hu.uni.amoba.Modell.*;
import hu.uni.amoba.Szolgaltatas.*;
import java.util.Scanner;

public class JatekIndito {
    public static void main(String[] args) {
        System.out.println("=== AMOBA JATEK ===");

        JatekSzolgaltatas jatek = new JatekSzolgaltatas();
        Scanner beolvaso = new Scanner(System.in);

        jatek.getAdatbazis().listazas();

        System.out.print("Neved: ");
        String nev = beolvaso.nextLine();

        System.out.println("Betöltés? (i/n)");
        String valasz = beolvaso.nextLine();
        if (valasz.equals("i")) {
            Palya betoltott = XmlKezelo.betoltes();
            jatek.setPalya(betoltott);
            System.out.println("Betoltve!");
        }

        while (true) {
            System.out.println(jatek.getPalya());

            System.out.println("Te jössz (pl: 5 C) vagy 'mentes':");
            String bemenet = beolvaso.nextLine();

            if (bemenet.equals("mentes")) {
                XmlKezelo.mentes(jatek.getPalya(), nev);
                System.out.println("Elmentve.");
                break;
            }

            try {
                String[] reszek = bemenet.split(" ");
                int sor = Integer.parseInt(reszek[0]) - 1;
                int oszlop = reszek[1].charAt(0) - 'A';

                boolean siker = jatek.lepes(sor, oszlop, Jel.X);

                if (siker) {
                    if (jatek.nyertE(Jel.X)) {
                        System.out.println(jatek.getPalya());
                        System.out.println("NYERTEL!");
                        jatek.getAdatbazis().mentes(nev);
                        break;
                    }

                    jatek.gepiLepes();
                    if (jatek.nyertE(Jel.O)) {
                        System.out.println(jatek.getPalya());
                        System.out.println("VESZTETTEL!");
                        break;
                    }
                } else {
                    System.out.println("Nem jo lepes!");
                }
            } catch (Exception e) {
                System.out.println("Rossz formatum!");
            }
        }
    }
}