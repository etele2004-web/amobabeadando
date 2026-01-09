package hu.uni.amoba.Szolgaltatas;

import java.sql.*;

public class AdatbazisKezelo {

    // Helyi fájlba menti az adatbázist a projekt mappájába
    private static final String DB_URL = "jdbc:h2:./amoba_adatbazis";
    private static final String USER = "sa";
    private static final String PASS = "";

    public AdatbazisKezelo() {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement()) {

            String sql = "CREATE TABLE IF NOT EXISTS EREDMENYEK " +
                    "(id INTEGER AUTO_INCREMENT PRIMARY KEY, " +
                    " nev VARCHAR(255), " +
                    " gyozelmek INTEGER)";
            stmt.executeUpdate(sql);

        } catch (SQLException e) {
            System.err.println("Adatbázis hiba: " + e.getMessage());
        }
    }

    public void gyozelemMentese(String nev) {
        String selectSql = "SELECT gyozelmek FROM EREDMENYEK WHERE nev = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement pstmt = conn.prepareStatement(selectSql)) {

            pstmt.setString(1, nev);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int gyoz = rs.getInt("gyozelmek");
                try (PreparedStatement update = conn.prepareStatement("UPDATE EREDMENYEK SET gyozelmek = ? WHERE nev = ?")) {
                    update.setInt(1, gyoz + 1);
                    update.setString(2, nev);
                    update.executeUpdate();
                }
            } else {
                try (PreparedStatement insert = conn.prepareStatement("INSERT INTO EREDMENYEK (nev, gyozelmek) VALUES (?, 1)")) {
                    insert.setString(1, nev);
                    insert.executeUpdate();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void ranglistaKiirasa() {
        System.out.println("\n=== RANGLISTA (TOP 10) ===");
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT nev, gyozelmek FROM EREDMENYEK ORDER BY gyozelmek DESC LIMIT 10")) {

            int i = 1;
            while (rs.next()) {
                System.out.println(i++ + ". " + rs.getString("nev") + " - " + rs.getInt("gyozelmek") + " győzelem");
            }
        } catch (SQLException e) {
            System.out.println("Nincs elérhető adat.");
        }
        System.out.println("==========================\n");
    }
}