package it.dpg.minigames.jumpgame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.jumpgame.model.Player;
import it.dpg.minigames.jumpgame.model.World;
import it.dpg.minigames.jumpgame.model.WorldImpl;
import it.dpg.minigames.jumpgame.view.JumpMinigameView;
import org.apache.commons.lang3.tuple.Pair;

public class JumpMinigameCycle implements MinigameCycle {

    private JumpMinigameView view;
    private World world;

    public JumpMinigameCycle(final JumpMinigameView view) {
        this.view = view;
        this.world = new WorldImpl();
    }

    @Override
    public int startCycle() {
        setup();

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long lastTime = System.currentTimeMillis();
        long currentTime;
        long total = 0;

        while(!world.isGameOver()) {
            currentTime = System.currentTimeMillis();

            total += currentTime - lastTime;
            if(total > 20) {
                world.update();
                render();
                total = 0;
            }

            lastTime = currentTime;
        }
        return 0;
    }

    private void setup() {
        view.setGameSize(world.getWidth(), world.getHeight());

        view.createPlayer(world.getPlayerPosition().getLeft(), world.getPlayerPosition().getRight(), world.getPlayerSize());
        world.getPlatformsPositions().forEach(
                (id, pos) -> view.createPlatform(pos.getLeft(), pos.getRight(), world.getPlatformsWidth().get(id), world.getPlatformsHeight().get(id), id)
        );
    }

    private void render() {
        Pair<Integer, Integer> positions = world.getPlayerPosition();
        view.updatePlayer(positions.getLeft(), positions.getRight());
        world.getPlatformsPositions().forEach(
                (id, pos) -> view.updatePlatform(pos.getLeft(), pos.getRight(), id)
        );
    }
}
