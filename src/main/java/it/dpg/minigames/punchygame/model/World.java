package it.dpg.minigames.punchygame.model;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class World {
    private List<Boolean> leftSacks;
    private List<Boolean> rightSacks;
    private Score score;
    private Timer timer;

    private static final int MAX_SACKS = 10;

    public World() {
        generateSacks();
        score = new Score();
        timer = new Timer();
    }

    private void generateSacks() {
        Random r = new Random();

        leftSacks = Stream.generate(r::nextBoolean)
                .limit(MAX_SACKS)
                .collect(
                        Collectors.toCollection(LinkedList::new)
                );

        rightSacks = new LinkedList<>();
        leftSacks.forEach(b -> rightSacks.add(!b));
    }
}
