package it.dpg.model;

public interface Cpu {
    Character getControlledCharacter();
    void setDifficulty(Difficulty difficulty);
    Difficulty getDifficulty();
    Direction getRandomDirection();
}
