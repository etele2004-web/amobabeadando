package hu.uni.amoba.Modell;

public record Koordinata(int sor, int oszlop) {

    public Koordinata {
        if (sor < 0 || oszlop < 0) {
            throw new IllegalArgumentException("A koordináták nem lehetnek negatívak!");
        }
    }
}
