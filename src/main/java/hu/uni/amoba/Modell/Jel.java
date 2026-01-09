package hu.uni.amoba.Modell;

public enum Jel {
    URES, X, O;

    public String toString() {
        if (this == X) return "X";
        if (this == O) return "O";
        return ".";
    }
}