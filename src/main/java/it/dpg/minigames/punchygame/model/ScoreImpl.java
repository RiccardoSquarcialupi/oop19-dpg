package it.dpg.minigames.punchygame.model;

public class ScoreImpl implements Score {
    private int score;
    private int multiplier;
    private int combo;

    public ScoreImpl() {
        score = 0;
        multiplier = 1;
        combo = 0;
    }

    @Override
    public void incrementScore() {
        score += multiplier;
        combo++;

        if(combo == 10) {
            multiplier = 2;
        } else if(combo == 20) {
            multiplier = 3;
        } else if(combo == 40) {
            multiplier = 4;
        }
    }

    @Override
    public int getScore() {
        return score;
    }
}
