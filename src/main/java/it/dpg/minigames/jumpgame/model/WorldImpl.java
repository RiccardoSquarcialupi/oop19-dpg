package it.dpg.minigames.jumpgame.model;

import java.util.ArrayList;
import java.util.List;

public class WorldImpl implements World {
    private static final int HEIGHT = 900;
    private static final int WIDTH = 600;
    private static final int UNIT = WIDTH/24;

    private static final int PLAYER_SIZE = WIDTH/6;
    private static final int PLATFORM_WIDTH = WIDTH/4;
    private static final int PLATFORM_HEIGHT = UNIT;

    private Player player;
    private List<Platform> platforms;
    private boolean gameOver;

    public WorldImpl() {
        gameOver = false;
        player = new PlayerImpl(PLAYER_SIZE, WIDTH/2 - 2*UNIT, PLAYER_SIZE);
        platforms = new ArrayList<>();
        platforms.add(new PlatformImpl(WIDTH/2 - 3*UNIT,8*UNIT, PLATFORM_WIDTH, PLATFORM_HEIGHT, 0));
    }

    @Override
    public void update() {
        player.updatePosition();
        platforms.forEach(p -> {
            player.checkCollisionWithPlatform(p.getX(), p.getY(), p.getWidth());
            p.setSpeedY(-player.getSpeedY()/2);
            p.updatePosition();
        });
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public Player getPlayer() {
        return player;
    }

    @Override
    public List<Platform> getPlatforms() {
        return platforms;
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }
}
