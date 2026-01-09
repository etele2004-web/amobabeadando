package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.*;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class XmlKezelo {

    public static void mentes(Palya palya, String nev) {
        try {
            FileWriter iro = new FileWriter("mentes.xml");
            iro.write("<amoba>\n");
            iro.write("<jatekos>" + nev + "</jatekos>\n");

            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    Jel jel = palya.getMezo(i, j);
                    if (jel != Jel.URES) {
                        iro.write("<lepes sor='" + i + "' oszlop='" + j + "'>" + jel + "</lepes>\n");
                    }
                }
            }
            iro.write("</amoba>");
            iro.close();
        } catch (Exception e) {
            System.out.println("Hiba mentéskor.");
        }
    }

    public static Palya betoltes() {
        Palya ujPalya = new Palya(10, 10);
        try {
            List<String> sorok = Files.readAllLines(Path.of("mentes.xml"));

            for (String sor : sorok) {
                if (sor.contains("<lepes")) {
                    String[] darabok = sor.split("'");
                    int s = Integer.parseInt(darabok[1]);
                    int o = Integer.parseInt(darabok[3]);

                    String jelSzoveg = sor.substring(sor.indexOf(">") + 1, sor.indexOf("</"));

                    Jel jel = Jel.X;
                    if (jelSzoveg.equals("O")) jel = Jel.O;

                    ujPalya.lerak(s, o, jel);
                }
            }
        } catch (Exception e) {
            System.out.println("Hiba betöltéskor.");
        }
        return ujPalya;
    }
}