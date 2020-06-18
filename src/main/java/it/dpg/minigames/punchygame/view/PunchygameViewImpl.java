package it.dpg.minigames.punchygame.view;

import it.dpg.minigames.base.view.AbstractMinigameView;
import it.dpg.minigames.punchygame.controller.InputObserver;
import it.dpg.minigames.punchygame.controller.input.PunchLeft;
import it.dpg.minigames.punchygame.controller.input.PunchRight;
import it.dpg.minigames.punchygame.model.Direction;
import it.dpg.minigames.punchygame.model.Timer;
import javafx.application.Platform;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PunchygameViewImpl extends AbstractMinigameView implements PunchygameView {

    private Scene scene;

    private static final double WIDTH = 1200;
    private static final double HEIGHT = 800;
    private static final Color BG_COLOR = Color.WHITE;
    private static final int SACKS_SLOT = 4;

    private static final double SACK_WIDTH = WIDTH/8;
    private static final double SACK_HEIGHT = HEIGHT/2;
    private static final double UNIT = WIDTH/16;

    private static final double CHAR_WIDTH = WIDTH/8;
    private static final double CHAR_HEIGHT = HEIGHT/2;

    private Text scoreText;
    private Text timerText;
    private InputObserver observer;

    @Override
    public Scene createScene() {
        scene = new Scene(createGroup(), WIDTH, HEIGHT, BG_COLOR);

        scene.setOnKeyPressed(k -> {
            
            if(k.getCode() == KeyCode.LEFT) {
                observer.notifyInput(new PunchLeft());
            } else if(k.getCode() == KeyCode.RIGHT) {
                observer.notifyInput(new PunchRight());
            }
        });

        return scene;
    }

    @Override
    public void updateSacks(List<Direction> sacks) {

    }

    @Override
    public void updateScore(int score) {
        Platform.runLater(
                () -> scoreText.setText("SCORE: ".concat(String.valueOf(score)))
        );
    }

    @Override
    public void updateTimer(int timer) {
        Platform.runLater(
                () -> timerText.setText("TIMER: ".concat(String.valueOf(timer)))
        );
    }

    @Override
    public void setInputObserver(InputObserver observer) {
        this.observer = observer;
    }

    private Group createGroup() {
        Group g = new Group();

        scoreText = new Text(0, 20, "SCORE: ");
        scoreText.setFont(new Font(20));
        g.getChildren().add(scoreText);

        timerText = new Text(WIDTH - 2*UNIT, 20,"TIMER: ");
        timerText.setFont(new Font(20));
        g.getChildren().add(timerText);

        List<Rectangle> sacks = Stream
                .generate(() -> new Rectangle(SACK_WIDTH, SACK_HEIGHT, Color.BLUE))
                .limit(SACKS_SLOT)
                .collect(Collectors.toList());
        double startX = UNIT;
        for(int i = 1; i <= SACKS_SLOT; i++) {
            sacks.get(i-1).setX(startX);
            sacks.get(i-1).setY(SACK_HEIGHT - UNIT);
            if(i == SACKS_SLOT/2) {
                startX += (SACK_WIDTH + CHAR_WIDTH + 2*(UNIT));
            } else {
                startX += SACK_WIDTH + UNIT;
            }
        }
        g.getChildren().addAll(sacks);

        Image charImage = new Image("images/punchygame/punch.png", CHAR_WIDTH, CHAR_HEIGHT, false, false);
        ImageView charView = new ImageView(charImage);
        charView.setX(WIDTH/2 - UNIT);
        charView.setY(CHAR_HEIGHT - UNIT);
        g.getChildren().add(charView);

        return g;
    }
}
