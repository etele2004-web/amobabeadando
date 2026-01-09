package hu.uni.amoba.Modell;

public enum Jel {
    URES, X, O;

    // Sima toString, nem kell bonyolítani
    public String toString() {
        if (this == X) return "X";
        if (this == O) return "O";
        return ".";
    }
}