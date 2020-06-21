package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

public class PlayerImpl implements Player {

    private static final int COLLISION_VERTICAL_TOLERANCE = 15;

    private int size;
    private int x;
    private int y;
    private int speedX;
    private int speedY;
    private int gravity;

    public PlayerImpl(final int size, final int x, final int y) {
        this.size = size;
        this.x = x;
        this.y = y;
        this.speedX = 0;
        this.speedY = 20;
        this.gravity = -1;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return Pair.of(x, y);
    }

    @Override
    public void setPosition(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public int getSpeedX() {
        return speedX;
    }

    @Override
    public int getSpeedY() {
        return speedY;
    }

    @Override
    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    @Override
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    @Override
    public void updatePosition() {
        x += speedX;
        y += speedY;
        speedY += gravity;
    }

    @Override
    public void checkCollisionWithPlatform(final int platformX, final int platformY, final int platformLength) {
        if(speedY < 0 &&
                (x+size > platformX && x < platformX+platformLength) &&
                (y-size <= platformY && y-size >= platformY-COLLISION_VERTICAL_TOLERANCE))
        {
            speedY = 25;
        }
    }
}
