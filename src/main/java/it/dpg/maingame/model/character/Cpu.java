package it.dpg.maingame.model.character;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

public interface Cpu {
    Character getControlledCharacter();
    Difficulty getDifficulty();
    Pair<Integer, Integer> getRandomDirection();
}
