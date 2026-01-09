package hu.uni.amoba.Modell;

public enum Jel {
    URES('.'),
    X('X'), // Ember
    O('O'); // Gép

    private final char karakterKod;

    Jel(char karakterKod) {
        this.karakterKod = karakterKod;
    }

    public char getKarakterKod() {
        return karakterKod;
    }

    @Override
    public String toString() {
        return String.valueOf(karakterKod);
    }
}