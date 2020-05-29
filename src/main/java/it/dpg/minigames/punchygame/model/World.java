package it.dpg.minigames.punchygame.model;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {
    private Random r = new Random();

    private Stack<Direction> sacks;
    private Score score;
    private Timer timer;

    private static final int MAX_SACKS = 5;

    public World() {
        generateSacks();
        score = new Score();
        timer = new Timer();
    }

    public Direction getRightSack() {
        sacks.push(randomDirection());
        return sacks.pop();
    }

    private void generateSacks() {
        Direction[] directions = Direction.values();
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
