package hu.uni.amoba.Szolgaltatas;

import java.sql.*;

public class AdatbazisKezelo {

    // Csak a kötelező dolgok
    String url = "jdbc:h2:./amoba_db";
    String user = "sa";
    String jelszo = "";

    public AdatbazisKezelo() {
        try {
            Connection kapcs = DriverManager.getConnection(url, user, jelszo);
            Statement parancs = kapcs.createStatement();

            // Tábla létrehozása
            String sql = "CREATE TABLE IF NOT EXISTS EREDMENYEK (nev VARCHAR(255), pont INT)";
            parancs.executeUpdate(sql);

            kapcs.close();
        } catch (Exception e) {
            System.out.println("Adatbázis hiba: " + e.getMessage());
        }
    }

    public void mentes(String nev) {
        try {
            Connection kapcs = DriverManager.getConnection(url, user, jelszo);

            // 1. Megnézzük, benne van-e már
            PreparedStatement kereses = kapcs.prepareStatement("SELECT pont FROM EREDMENYEK WHERE nev = ?");
            kereses.setString(1, nev);
            ResultSet eredmeny = kereses.executeQuery();

            if (eredmeny.next()) {
                // Ha benne van, növeljük a pontot
                int pont = eredmeny.getInt("pont");
                PreparedStatement frissites = kapcs.prepareStatement("UPDATE EREDMENYEK SET pont = ? WHERE nev = ?");
                frissites.setInt(1, pont + 1);
                frissites.setString(2, nev);
                frissites.executeUpdate();
            } else {
                // Ha nincs, beszúrjuk
                PreparedStatement beszuras = kapcs.prepareStatement("INSERT INTO EREDMENYEK VALUES (?, 1)");
                beszuras.setString(1, nev);
                beszuras.executeUpdate();
            }
            kapcs.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void listazas() {
        System.out.println("=== RANGLISTA ===");
        try {
            Connection kapcs = DriverManager.getConnection(url, user, jelszo);
            Statement parancs = kapcs.createStatement();
            ResultSet eredmeny = parancs.executeQuery("SELECT * FROM EREDMENYEK ORDER BY pont DESC LIMIT 10");

            while (eredmeny.next()) {
                String n = eredmeny.getString("nev");
                int p = eredmeny.getInt("pont");
                System.out.println(n + " - " + p + " győzelem");
            }
            kapcs.close();
        } catch (Exception e) {
            System.out.println("Nem sikerült a lista.");
        }
    }
}