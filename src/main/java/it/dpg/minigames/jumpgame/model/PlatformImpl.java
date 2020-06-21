package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

public class PlatformImpl implements Platform {

    private int x;
    private int y;
    private final int height;
    private final int width;

    public PlatformImpl(final int x, final int y, final int width, final int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
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
}
