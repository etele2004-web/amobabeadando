package hu.uni.amoba.Szolgaltatas;

import hu.uni.amoba.Modell.*;
import java.io.IOException;
import java.nio.file.*;
import java.util.regex.*;

public class XmlKezelo {

    public static void mentes(Palya palya, String nev, String fajlnev) throws IOException {
        StringBuilder xml = new StringBuilder();
        xml.append("<amoba>\n  <jatekos>").append(nev).append("</jatekos>\n");
        xml.append("  <palya sorok=\"").append(palya.getSorokSzama()).append("\" oszlopok=\"").append(palya.getOszlopokSzama()).append("\">\n");

        for (int i = 0; i < palya.getSorokSzama(); i++) {
            for (int j = 0; j < palya.getOszlopokSzama(); j++) {
                Jel jel = palya.getMezoErtek(i, j);
                if (jel != Jel.URES) {
                    xml.append("    <lepes sor=\"").append(i).append("\" oszlop=\"").append(j).append("\">").append(jel).append("</lepes>\n");
                }
            }
        }
        xml.append("  </palya>\n</amoba>");
        Files.writeString(Path.of(fajlnev), xml.toString());
    }

    public static Palya betoltes(String fajlnev) throws IOException {
        String tartalom = Files.readString(Path.of(fajlnev));

        Matcher mMeret = Pattern.compile("sorok=\"(\\d+)\" oszlopok=\"(\\d+)\"").matcher(tartalom);
        int sor = 10, osz = 10;
        if (mMeret.find()) {
            sor = Integer.parseInt(mMeret.group(1));
            osz = Integer.parseInt(mMeret.group(2));
        }

        Palya palya = new Palya(sor, osz);
        Matcher mLepes = Pattern.compile("lepes sor=\"(\\d+)\" oszlop=\"(\\d+)\">([XO])").matcher(tartalom);

        while (mLepes.find()) {
            palya.jelElhelyezese(new Koordinata(Integer.parseInt(mLepes.group(1)), Integer.parseInt(mLepes.group(2))),
                    Jel.valueOf(mLepes.group(3)));
        }
        return palya;
    }
}