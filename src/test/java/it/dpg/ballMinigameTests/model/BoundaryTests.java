package it.dpg.ballMinigameTests.model;

import it.dpg.minigames.ballgame.model.Boundary;
import it.dpg.minigames.ballgame.model.CollisionType;
import it.dpg.minigames.ballgame.model.HorizontalBoundary;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoundaryTests {
    @Test
    public void testExeptions() {
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(90, 110, 2, CollisionType.BOUNCE));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(200, 1, 56, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(200, 1, 56, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(-1, 45, 3, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(4, -78, 56, CollisionType.GOAL));
        assertThrows(IllegalArgumentException.class, () -> new HorizontalBoundary(5, 10, -10, CollisionType.GOAL));
    }

    @Test
    public void testBoundaries() {
        Boundary b1 = new HorizontalBoundary(40, 60, 50, CollisionType.BOUNCE);
        assertTrue(b1.isColliding(38, 52, 8));
        assertTrue(b1.isColliding(45, 49, 5));
        assertFalse(b1.isColliding(51, 30, 5));
        assertFalse(b1.isColliding(30, 20, 15));
        assertTrue(b1.isColliding(45, 51, 5));
        assertFalse(b1.isColliding(51, 70, 5));
        assertTrue(b1.isColliding(62, 48, 5));
        assertFalse(b1.isColliding(70, 80, 15));
    }
}
