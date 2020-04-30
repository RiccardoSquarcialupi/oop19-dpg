package it.dpg.model;

public enum Difficulty {
    EASY(0.5f), NORMAL(1.0f), HARD(1.20f), CRAZY(1.50f);

    private final float multiplier;
    Difficulty(final float multiplier) {
        this.multiplier = multiplier;
    }
    public float getMultiplier() {
        return this.multiplier;
    }
}
