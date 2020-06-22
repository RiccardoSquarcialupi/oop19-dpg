package it.dpg.minigames.jumpgame.view;

import it.dpg.minigames.base.view.AbstractMinigameView;
import it.dpg.minigames.jumpgame.controller.input.InputObserver;
import it.dpg.minigames.jumpgame.controller.input.MoveLeft;
import it.dpg.minigames.jumpgame.controller.input.MoveRight;
import it.dpg.minigames.jumpgame.controller.input.StopMovement;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.HashMap;
import java.util.Map;

public class JumpMinigameViewImpl extends AbstractMinigameView implements JumpMinigameView {

    private static final Color BG_COLOR = Color.WHITE;
    private static final Color PLAYER_COLOR = Color.BLUEVIOLET;

    private Pane pane;
    private Rectangle player;
    private Map<Integer, Rectangle> platforms = new HashMap<>();

    private InputObserver observer;

    @Override
    public Scene createScene() {
        pane = new Pane();
        Scene scene = new Scene(pane);

        scene.setOnKeyPressed(k -> {
            if(k.getCode() == KeyCode.LEFT) {
                observer.notifyInput(new MoveLeft());
            } else if(k.getCode() == KeyCode.RIGHT) {
                observer.notifyInput(new MoveRight());
            }
        });

        scene.setOnKeyReleased(k -> {
            if(k.getCode() == KeyCode.LEFT || k.getCode() == KeyCode.RIGHT) {
                observer.notifyInput(new StopMovement());
            }
        });

        return scene;
    }

    @Override
    public void setGameSize(int width, int height) {
        pane.setMinSize(width, height);
    }

    @Override
    public void createPlayer(final int x, final int y, final int size) {
        Platform.runLater(() -> {
            player = new Rectangle(size, size, PLAYER_COLOR);
            player.setX(x);
            player.setY(mapY(y));
            player.setArcWidth(40);
            player.setArcHeight(40);
            pane.getChildren().add(player);
        });
    }

    @Override
    public void createPlatform(int x, int y, int width, int height, int id) {
        Platform.runLater(() -> {
            Rectangle r = new Rectangle(width, height, Color.BLACK);
            r.setX(x);
            r.setY(mapY(y));
            platforms.put(id, r);
            pane.getChildren().addAll(platforms.values());
        });
    }

    @Override
    public void updatePlayer(final int x, final int y) {
        Platform.runLater(() -> {
            if(player != null) {
                player.setX(x);
                player.setY(mapY(y));
            } else {
                throw new UnsupportedOperationException("You must create a player before updating it");
            }
        });
    }

    @Override
    public void updatePlatform(int x, int y, int id) {
        Platform.runLater(() -> {
            Rectangle r = platforms.get(id);
            if(r != null) {
                r.setX(x);
                r.setY(mapY(y));
            }
        });
    }

    @Override
    public void setInputObserver(final InputObserver observer) {
        this.observer = observer;
    }

    private double mapY(final int y) {
        return pane.getHeight() - y;
    }
}
