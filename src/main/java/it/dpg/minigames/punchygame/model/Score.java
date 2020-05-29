package it.dpg.minigames.punchygame.model;

public class Score {
    private int score;
    private int multiplier;
    private int combo;

    public Score() {
        score = 0;
        multiplier = 1;
        combo = 0;
    }

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

    public int getScore() {
        return score;
    }
}
