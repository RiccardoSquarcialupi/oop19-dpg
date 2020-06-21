package it.dpg.minigames.jumpgame.view;

import it.dpg.minigames.base.view.AbstractMinigameView;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.List;

public class JumpMinigameViewImpl extends AbstractMinigameView implements JumpMinigameView {

    private static final double HEIGHT = 900;
    private static final double WIDTH = 600;
    private static final double UNIT = WIDTH/12;

    private static final double PLAYER_WIDTH = WIDTH/6;
    private static final double PLAYER_HEIGHT = PLAYER_WIDTH;

    private static final Color BG_COLOR = Color.WHITE;
    private static final Color PLAYER_COLOR = Color.BLUEVIOLET;

    private Rectangle player;
    private List<Rectangle> platforms;

    @Override
    public Scene createScene() {
        Scene scene = new Scene(createGroup(), WIDTH, HEIGHT, BG_COLOR);

        scene.setOnKeyPressed(k -> {
            if(k.getCode() == KeyCode.LEFT) {

            } else if(k.getCode() == KeyCode.RIGHT) {

            }
        });

        scene.setOnKeyReleased(k -> {
            if(k.getCode() == KeyCode.LEFT || k.getCode() == KeyCode.RIGHT) {

            }
        });

        return scene;
    }

    @Override
    public void updatePlayer(final int x, final int y) {
        Platform.runLater(() -> {
            player.setX(x);
            player.setY(y);
        });
    }

    private Group createGroup() {
        Group g = new Group();

        player = new Rectangle(PLAYER_WIDTH, PLAYER_HEIGHT, PLAYER_COLOR);
        player.setX(WIDTH/2 - UNIT);
        player.setY(HEIGHT - PLAYER_HEIGHT);
        player.setArcWidth(40);
        player.setArcHeight(40);
        g.getChildren().add(player);

        return g;
    }
}
