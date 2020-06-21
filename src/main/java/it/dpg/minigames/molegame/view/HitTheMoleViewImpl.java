package it.dpg.minigames.molegame.view;

import it.dpg.minigames.base.view.AbstractMinigameView;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Pair;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class HitTheMoleViewImpl extends AbstractMinigameView implements HitTheMoleView {

    private static final double WIDTH = 800;
    private static final double HEIGHT = 800;
    private static final int NMOLE = 25;
    private static final Color BG_COLOR = Color.GREEN;
    private Label scoreLbl;
    private Label timerLbl;
    private List<Pair<Integer, Label>> listMole = new ArrayList<>();
    private String sep = File.separator;
    private String holeWithoutMole = "images" + sep + "molegame" + sep + "holewithoutmole.png";
    private String holeWithMole = "images" + sep + "molegame" + sep + "holewithmole.png";


    @Override
    public Scene createScene() {

        BorderPane bp = new BorderPane();
        HBox hb = new HBox();
        Scene scene = new Scene(bp, WIDTH, HEIGHT, BG_COLOR);

        Text score = new Text("Score");
        scoreLbl = new Label("0");
        Text timer = new Text("Timer");
        timerLbl = new Label("20");

        bp.getChildren().add(hb);
        bp.setTop(hb);

        hb.getChildren().add(score);
        hb.getChildren().add(scoreLbl);
        hb.getChildren().add(timer);
        hb.getChildren().add(timerLbl);


        for (int i = 0; i < NMOLE; i++) {
            Label l = new Label();
            bp.getChildren().add(l);
            bp.setCenter(l);
            l.setGraphic(new ImageView(new Image(holeWithoutMole)));
            l.setOnMouseClicked(mouseEvent -> {

            });
            listMole.add(new Pair<>(i, l));

        }


        return scene;
    }


    /**
     * update the score of the view
     */
    @Override
    public void updateScore() {
        scoreLbl.setText(String.valueOf(Integer.parseInt(scoreLbl.getText()) + 1));
    }

    /**
     * decrease the timer in the view
     */
    @Override
    public void updateTimer() {
        timerLbl.setText(String.valueOf(Integer.parseInt(timerLbl.getText()) - 1));
    }

    /**
     * update the view of out moles
     *
     * @param moleOut list of the out mole
     */
    @Override
    public void updateMole(List<Integer> moleOut) {
        for (var p : listMole) {
            for (var i : moleOut) {
                if (p.getKey().equals(i)) {
                    p.getValue().setGraphic(new ImageView(new Image(holeWithMole)));
                }
            }
        }
    }


}
