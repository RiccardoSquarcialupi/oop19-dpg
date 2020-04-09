package it.dpg.view;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class MenuGUI extends Application implements MenuView{

    private final Button startBtn = new Button("Start");
    private final Button creditBtn = new Button("Credits");
    private final Button optionsBtn = new Button("Options");
    private final Button exitBtn = new Button("Exit");

    /**
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        initializeGUI(stage);
    }

    /**
     * @param stage
     */
    private void initializeGUI(Stage stage) {
        startBtn.setPrefSize(100,60);
        startBtn.setFont(Font.font(15));
        creditBtn.setPrefSize(100,60);
        creditBtn.setFont(Font.font(15));
        optionsBtn.setPrefSize(100,60);
        optionsBtn.setFont(Font.font(15));
        exitBtn.setPrefSize(100,60);
        exitBtn.setFont(Font.font(15));

        var rootBox= new VBox();
        rootBox.setSpacing(7);
        rootBox.setAlignment(Pos.BASELINE_CENTER);
        var mainScene = new Scene(rootBox, 300, 350);
        rootBox.getChildren().addAll(startBtn, creditBtn, optionsBtn, exitBtn);

        stage.setTitle("Dope Game Party-Menu");
        stage.setScene(mainScene);
        stage.show();

    }

    @Override
    public void displayCredit() {

    }

    @Override
    public void displayOptions() {

    }

    @Override
    public void exitGUI() {

    }
}
