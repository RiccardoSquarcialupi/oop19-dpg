package it.dpg.minigames.ballgame.model;

import it.dpg.minigames.ballgame.controller.BallMinigameLevel;

import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;

public class BallEnvironmentFactoryImpl implements BallEnvironmentFactory {

    private final int expectedFPS;

    BallEnvironmentFactoryImpl(int expectedFPS) {
        this.expectedFPS = expectedFPS;
    }

    @Override
    public BallEnvironment createEnvironment(BallMinigameLevel level) {
        Set<Boundary> temp = new HashSet<>();
        temp.add(new HorizontalBoundary(0, 100, 100, CollisionType.BOUNCE));
        temp.add(new HorizontalBoundary(0, 100, 0, CollisionType.BOUNCE));
        temp.add(new VerticalBoundary(0, 0, 100, CollisionType.BOUNCE));
        temp.add(new VerticalBoundary(100, 0, 100, CollisionType.BOUNCE));
        return new BallEnvironmentImpl(50, 50, 5, temp, expectedFPS);
    }
}
