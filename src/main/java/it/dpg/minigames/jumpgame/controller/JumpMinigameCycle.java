package it.dpg.minigames.jumpgame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;
import it.dpg.minigames.jumpgame.controller.input.Input;
import it.dpg.minigames.jumpgame.controller.input.InputObserver;
import it.dpg.minigames.jumpgame.model.Player;
import it.dpg.minigames.jumpgame.model.World;
import it.dpg.minigames.jumpgame.model.WorldImpl;
import it.dpg.minigames.jumpgame.view.JumpMinigameView;
import org.apache.commons.lang3.tuple.Pair;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class JumpMinigameCycle implements MinigameCycle, InputObserver {

    private JumpMinigameView view;
    private World world;
    private BlockingQueue<Input> inputBuffer;

    public JumpMinigameCycle(final JumpMinigameView view) {
        this.view = view;
        this.world = new WorldImpl();
        this.inputBuffer = new ArrayBlockingQueue<>(10);
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
                update();
                total = 0;
            }

            lastTime = currentTime;
        }
        return 0;
    }

    @Override
    public void notifyInput(final Input input) {
        inputBuffer.add(input);
    }

    private void setup() {
        view.setInputObserver(this);

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

    private void update() {
        processInput();
        world.update();
        render();
    }

    private void processInput() {
        Input i = inputBuffer.poll();
        if(i != null) {
            i.execute(world);
        }
    }
}
