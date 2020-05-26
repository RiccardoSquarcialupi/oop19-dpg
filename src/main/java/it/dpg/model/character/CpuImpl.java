package it.dpg.model.character;

import org.apache.commons.lang3.tuple.ImmutablePair;

public class CpuImpl implements Cpu {

    private final Character controlledCharacter;
    private final Difficulty difficulty;

    public CpuImpl(final Character controlledCharacter, final Difficulty difficulty) {
        this.controlledCharacter = controlledCharacter;
        this.difficulty = difficulty;
    }

    @Override
    public Character getControlledCharacter() {
        return this.controlledCharacter;
    }

    @Override
    public Difficulty getDifficulty() {
        return this.difficulty;
    }

    @Override
    public ImmutablePair<Integer, Integer> getRandomDirection() {
        return this.controlledCharacter.getAdjacentPositions().stream()
                .findAny()
                .orElseThrow();
    }
}
