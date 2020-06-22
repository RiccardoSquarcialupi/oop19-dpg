package it.dpg.maingame.model.character;

public enum Difficulty {
    EASY(0.2f), NORMAL(0.4f), HARD(0.7f);

    private final float multiplier;
    Difficulty(final float multiplier) {
        this.multiplier = multiplier;
    }
    public float getMultiplier() {
        return this.multiplier;
    }
}
