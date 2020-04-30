package it.dpg.model.character;

import org.apache.commons.lang3.tuple.ImmutablePair;

public interface Cpu {
    Character getControlledCharacter();
    void setDifficulty(Difficulty difficulty);
    Difficulty getDifficulty();
    ImmutablePair<Integer, Integer> getRandomDirection();
}
