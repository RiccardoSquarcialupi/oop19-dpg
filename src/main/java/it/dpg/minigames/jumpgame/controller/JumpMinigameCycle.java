package it.dpg.minigames.jumpgame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.jumpgame.model.Platform;
import it.dpg.minigames.jumpgame.model.Player;
import it.dpg.minigames.jumpgame.model.World;
import it.dpg.minigames.jumpgame.model.WorldImpl;
import it.dpg.minigames.jumpgame.view.JumpMinigameView;
import org.apache.commons.lang3.tuple.Pair;

import java.util.List;

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
        view.setGameSize(600, 900);
        Player p = world.getPlayer();

        view.createPlayer(p.getPosition().getLeft(), p.getPosition().getRight(), p.getSize());
        world.getPlatforms().forEach(
                plat -> view.createPlatform(plat.getX(), plat.getY(), plat.getWidth(), plat.getHeight(), plat.getId())
        );
    }

    private void render() {
        Pair<Integer, Integer> positions = world.getPlayer().getPosition();
        view.updatePlayer(positions.getLeft(), positions.getRight());
        world.getPlatforms().forEach(
                plat -> view.updatePlatform(plat.getX(), plat.getY(), plat.getId())
        );
    }
}
