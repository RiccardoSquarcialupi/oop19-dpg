package it.dpg.minigames.jumpgame.model;

import java.util.ArrayList;
import java.util.List;

public class WorldImpl implements World {
    private static final int HEIGHT = 900;
    private static final int WIDTH = 600;
    private static final int UNIT = WIDTH/12;

    private static final int PLAYER_SIZE = WIDTH/6;

    private Player player;
    private List<Platform> platforms;

    public WorldImpl() {
        player = new PlayerImpl(PLAYER_SIZE, WIDTH/2 - UNIT, HEIGHT - PLAYER_SIZE);
        platforms = new ArrayList<>();
        platforms.add(new PlatformImpl(WIDTH/2 - UNIT), );
    }

    @Override
    public void update() {

    }
}
