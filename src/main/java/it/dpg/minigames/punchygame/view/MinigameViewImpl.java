package it.dpg.minigames.punchygame.view;

import it.dpg.minigames.base.view.AbstractMinigameView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MinigameViewImpl extends AbstractMinigameView {

    private Scene scene;

    private static final double WIDTH = 800;
    private static final double HEIGHT = 600;
    private static final Color BG_COLOR = Color.WHITE;
    private static final int SACKS_SLOT = 4;

    private static final double SACK_WIDTH = WIDTH/8;
    private static final double SACK_HEIGHT = HEIGHT/2;
    private static final double UNIT = WIDTH/16;

    private static final double CHAR_WIDTH = WIDTH/8;
    private static final double CHAR_HEIGHT = HEIGHT/2;

    @Override
    public Scene createScene() {

        Group g = new Group();

        Text scoreText = new Text("Score: 0");
        g.getChildren().add(scoreText);

        Text timerText = new Text("Timer: 60");
        g.getChildren().add(timerText);

        List<Rectangle> sacks = Stream
                .generate(() -> new Rectangle(SACK_WIDTH, SACK_HEIGHT, BG_COLOR))
                .limit(SACKS_SLOT)
                .collect(Collectors.toList());
        double startX = UNIT;
        for(int i = 1; i <= SACKS_SLOT; i++) {
            sacks.get(i-1).setX(startX);
            sacks.get(i-1).setY(SACK_HEIGHT - UNIT);
            if(i == SACKS_SLOT/2) {
                startX += CHAR_WIDTH + 2*(UNIT);
            }

            startX += SACK_WIDTH + UNIT;
        }
        g.getChildren().addAll(sacks);

        Image charImage = new Image("resources/images/punchygame/punch.png", CHAR_WIDTH, CHAR_HEIGHT, false, false);
        ImageView charView = new ImageView(charImage);
        charView.setX(WIDTH/2 - UNIT);
        charView.setY(CHAR_HEIGHT - UNIT);
        g.getChildren().add(charView);

        scene = new Scene(g, WIDTH, HEIGHT, BG_COLOR);
        return scene;
    }
}
