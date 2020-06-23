package it.dpg.maingame.model.character;

import org.apache.commons.lang3.tuple.Pair;

public interface Cpu {
    /**
     * get the character controlled by this cpu
     * */
    Character getControlledCharacter();

    /**
     * get the difficulty of this cpu
     * */
    Difficulty getDifficulty();

    /**
     * get a random direction adjacent to the character controlled by this cpu
     * */
    Pair<Integer, Integer> getRandomDirection();
}
