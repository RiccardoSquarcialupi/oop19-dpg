package it.dpg.model;

public enum Dice {
    D4(4), D6(6), D8(8), D10(10);

    private final Integer faces;

    Dice(Integer faces) {
        this.faces = faces;
    }

    public Integer getFaces() {
        return this.faces;
    }
}
