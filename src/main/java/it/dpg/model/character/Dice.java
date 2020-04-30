package it.dpg.model;

public enum Dice {
    D4(4), D6(6), D8(8), D10(10);

    private final int faces;
    Dice(int faces) {
        this.faces = faces;
    }
    public int getFaces() {
        return this.faces;
    }
}
