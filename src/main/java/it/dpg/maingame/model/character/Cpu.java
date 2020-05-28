package it.dpg.maingame.model.character;

import org.apache.commons.lang3.tuple.ImmutablePair;

public interface Cpu {
    Character getControlledCharacter();
    Difficulty getDifficulty();
    ImmutablePair<Integer, Integer> getRandomDirection();
}
