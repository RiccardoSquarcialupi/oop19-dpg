package it.dpg.model;

public interface Cpu {
    void setCharacterToControl(Character character);
    Character getCharacter();
    void setDifficulty(Difficulty difficulty);
    Difficulty getDifficulty();
    Direction getRandomDirection();
}
