package it.dpg.minigames.ballMinigameTests.model;

import it.dpg.minigames.ballgame.model.*;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class BallEnvironmentTests {

    @Test
    public void envTest1() {
        Boundary b = new VerticalBoundary(60, 0, 100, CollisionType.RESET);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15, 999);
        env.nextFrame(false, false, false, true);
        int i;
        for(i = 0; i < 30 ; i++) {
            System.out.println(env.getX() + " " + env.getY());
            env.nextFrame(false, false, false, true);
            if(env.getX() == 50 && env.getY() == 50) {
                break;
            }
        }
        assertTrue(i < 30);
    }

    @Test
    public void envTest2() {
        Boundary b = new VerticalBoundary(60, 0, 100, CollisionType.RESET);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15, 999);
        env.nextFrame(true, true, false, false);
        env.nextFrame(false, false, true, true);
        assertTrue(env.getX() == 50 && env.getY() == 50);
    }

    @Test
    public void envTest3() {
        Boundary b = new VerticalBoundary(70, 0, 100, CollisionType.BOUNCE);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15, 999);
        for(int i = 0; i < 100; i++) {
            System.out.println(env.getX() + " " + env.getY());
            env.nextFrame(false, false, false, true);
            assertTrue(env.getX() > 50 && env.getX() < 70 && env.getY() == 50);
        }
    }

    @Test
    public void envTest4() {
        Boundary b = new HorizontalBoundary(50, 85, 75, CollisionType.BOUNCE);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15, 999);
        double prevX;
        for(int i = 0; i < 100; i++) {
            prevX = env.getX();
            System.out.println(env.getX() + " " + env.getY());
            env.nextFrame(true, false, false, true);
            assertTrue(prevX <= env.getX());
            assertTrue(env.getX() > 50 && env.getY() > 50);
        }
    }

    @Test
    public void envTest5() {
        Boundary b = new HorizontalBoundary(50, 85, 75, CollisionType.GOAL);
        BallEnvironment env = new BallEnvironmentImpl(50, 50, 5, Set.of(b), 15, 999);
        int i;
        for(i = 0; i < 100; i++) {
            System.out.println(env.getX() + " " + env.getY());
            env.nextFrame(true, false, false, true);
            if(env.goalReached()) {
                break;
            }
        }
        assertTrue(i < 100);
    }
}
