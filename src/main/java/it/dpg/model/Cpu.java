package it.dpg.model;

public interface Cpu {
    void setControlledCharacter(Character character);
    Character getControlledCharacter();
    void setDifficulty(Difficulty difficulty);
    Difficulty getDifficulty();
    Direction getRandomDirection();
}
