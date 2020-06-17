package it.dpg.minigames.punchygame.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class WorldImpl implements World {
    private Random r = new Random();

    private Stack<Direction> sacks;
    private Score score;
    private Timer timer;

    private static final int MAX_SACKS = 6;

    public WorldImpl() {
        score = new ScoreImpl();
        timer = new TimerImpl();
        generateSacks();
    }

    @Override
    public Direction getNextSack() {
        sacks.push(randomDirection());
        return sacks.pop();
    }

    @Override
    public List<Direction> getSacks() {
        return new ArrayList<>(sacks);
    }

    @Override
    public Score getScore() {
        return null;
    }

    @Override
    public Timer getTimer() {
        return null;
    }

    private void generateSacks() {
        sacks = Stream
                .generate(this::randomDirection)
                .limit(MAX_SACKS)
                .collect(Collectors.toCollection(Stack::new));
    }

    private Direction randomDirection() {
        Direction[] directions = Direction.values();
        return directions[r.nextInt(directions.length)];
    }
}
