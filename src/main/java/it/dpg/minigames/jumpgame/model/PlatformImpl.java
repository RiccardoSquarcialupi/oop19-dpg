package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

public class PlatformImpl implements Platform {

    private int x;
    private int y;
    private final int id;
    private final int height;
    private final int width;
    private int speedY = 0;

    public PlatformImpl(final int x, final int y, final int width, final int height, final int id) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.id = id;
    }

    @Override
    public Pair<Integer, Integer> getPosition() {
        return Pair.of(x, y);
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    @Override
    public void updatePosition() {
        y += speedY;
    }
}
