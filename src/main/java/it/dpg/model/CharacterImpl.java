package it.dpg.model;

import java.util.Objects;
import java.util.Random;

public class CharacterImpl implements Character {

    private final String name;

    private Integer turn;
    private Cell position;
    private Dice dice;
    private Integer lastMinigameScore;

    private Integer remainingMoves;

    public CharacterImpl(final String name, final Cell position) {
        this.name = Objects.requireNonNull(name);
        this.position = Objects.requireNonNull(position);
        this.turn = 0;
        this.lastMinigameScore = 0;
        this.dice = Dice.D6;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setTurn(final Integer turn) {
        this.turn =  Objects.requireNonNull(turn);
    }

    @Override
    public Integer getTurn() {
        return this.turn;
    }

    @Override
    public Cell getPosition() {
        return this.position;
    }

    @Override
    public boolean stepInDirection(final Direction direction) {
        Objects.requireNonNull(direction);

        if(direction == Direction.FORWARD && position.getForward() != null) {
            this.remainingMoves--;
            this.position = position.getForward();
        } else if(direction == Direction.BACK && position.getPrevious() != null) {
            this.remainingMoves--;
            this.position = position.getPrevious();
        } else if(direction == Direction.LEFT && position.getLeft() != null) {
            this.remainingMoves--;
            this.position = position.getLeft();
        } else if(direction == Direction.RIGHT && position.getRight() != null) {
            this.remainingMoves--;
            this.position = position.getRight();
        } else {
            throw new UnsupportedOperationException("No Cell found ad Direction: " + direction.name());
        }

        return this.remainingMoves > 0;
    }

    @Override
    public void setDice(final Dice dice) {
        this.dice = Objects.requireNonNull(dice);
    }

    @Override
    public Dice getDice() {
        return this.dice;
    }

    @Override
    public Integer throwDice() {
        this.remainingMoves = new Random().nextInt(dice.getFaces()) + 1;
        return this.remainingMoves;
    }

    @Override
    public Integer getMinigameScore() {
        return lastMinigameScore;
    }
}
