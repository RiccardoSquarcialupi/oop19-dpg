package it.dpg.minigames.punchygame.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldImpl implements World {
    private Random r = new Random();

    private Deque<Direction> sacks;
    private Score score;
    private Timer timer;
    private Boxer boxer;
    private boolean gameOver;

    private static final int MAX_SACKS = 3;

    public WorldImpl() {
        score = new ScoreImpl();
        timer = new TimerImpl();
        boxer = new BoxerImpl();
        generateSacks();
    }

    @Override
    public Direction getNextSack() {
        sacks.addLast(randomDirection());
        return sacks.pop();
    }

    @Override
    public List<Direction> getSacks() {
        return new ArrayList<>(sacks);
    }

    @Override
    public Score getScore() {
        return score;
    }

    @Override
    public Timer getTimer() {
        return timer;
    }

    @Override
    public Boxer getBoxer() {
        return boxer;
    }

    @Override
    public void triggerGameOver() {
        gameOver = true;
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    private void generateSacks() {
        sacks = Stream
                .generate(this::randomDirection)
                .limit(MAX_SACKS)
                .collect(Collectors.toCollection(ArrayDeque::new));
    }

    private Direction randomDirection() {
        Direction[] directions = Direction.values();
        return directions[r.nextInt(directions.length)];
    }
}
