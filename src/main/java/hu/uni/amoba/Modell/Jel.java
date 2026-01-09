package hu.uni.amoba.Modell;

public enum Jel {
    URES('.'),
    X('X'),
    O('O');

    private final char karakter;

    Jel(char karakter) {
        this.karakter = karakter;
    }

    @Override
    public String toString() {
        return String.valueOf(karakter);
    }
}