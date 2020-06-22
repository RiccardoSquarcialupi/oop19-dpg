package it.dpg.minigames.jumpgame.model;

import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WorldImpl implements World {
    private static final int HEIGHT = 900;
    private static final int WIDTH = 600;
    private static final int UNIT = WIDTH/24;

    private static final int PLAYER_SIZE = WIDTH/6;
    private static final int PLATFORM_WIDTH = WIDTH/4;
    private static final int PLATFORM_HEIGHT = UNIT;

    private static final int GRAVITY = 1;

    private Player player;
    private List<Platform> platforms;
    private boolean gameOver;

    public WorldImpl() {
        gameOver = false;
        player = new Player(PLAYER_SIZE, WIDTH/2 - 2*UNIT, PLAYER_SIZE, GRAVITY);
        platforms = new ArrayList<>();
        platforms.add(new Platform(WIDTH/2 - 3*UNIT,8*UNIT, PLATFORM_WIDTH, PLATFORM_HEIGHT, 0));
    }

    @Override
    public void update() {
        player.updatePosition();
        platforms.forEach(p -> {
            checkCollisionWithPlatform(p);
            p.setSpeedY(-player.getSpeedY()/2);
            p.updatePosition();
        });
    }

    @Override
    public boolean isGameOver() {
        return gameOver;
    }

    @Override
    public Pair<Integer, Integer> getPlayerPosition() {
        return player.getPosition();
    }

    @Override
    public int getPlayerSize() {
        return player.getWidth();
    }

    @Override
    public Map<Integer, Pair<Integer, Integer>> getPlatformsPositions() {
        return platforms.stream()
                .collect(Collectors.toMap(Platform::getId, Platform::getPosition));
    }

    @Override
    public Map<Integer, Integer> getPlatformsWidth() {
        return platforms.stream()
                .collect(Collectors.toMap(Platform::getId, Platform::getWidth));
    }

    @Override
    public Map<Integer, Integer> getPlatformsHeight() {
        return platforms.stream()
                .collect(Collectors.toMap(Platform::getId, Platform::getHeight));
    }

    @Override
    public void setPlayerSpeedX(final int speedX) {
        player.setSpeedX(speedX);
    }

    @Override
    public int getWidth() {
        return WIDTH;
    }

    @Override
    public int getHeight() {
        return HEIGHT;
    }

    private void checkCollisionWithPlatform(final Platform p) {
        int playerLeftSide = player.getPosition().getLeft();
        int playerRightSide = player.getPosition().getLeft() + player.getWidth();
        int playerBottomSide = player.getPosition().getRight() - player.getHeight();

        int platformLeftSide = p.getPosition().getLeft();
        int platformRightSide = p.getPosition().getLeft() + p.getWidth();
        int platformTopSide = p.getPosition().getRight();
        int platformBottomSide = p.getPosition().getRight() - p.getHeight();

        if(player.getSpeedY() < 0 &&
                playerRightSide > platformLeftSide && playerLeftSide < platformRightSide &&
                playerBottomSide <= platformTopSide && playerBottomSide >= platformBottomSide
        )
        {
            player.setSpeedY(25);
        }
    }
}
