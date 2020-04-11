package it.dpg.view;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
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

        startBtn.setOnAction( (ActionEvent event) -> {
            startGame();
        });

        exitBtn.setOnAction((ActionEvent event) ->{
            exitGUI();
        });

        creditBtn.setOnAction( (ActionEvent event ) ->{
            displayCredit();
        });


        stage.setTitle("Dope Game Party-Menu");
        stage.setScene(mainScene);
        stage.show();

    }

    //disable all button
    private void disableAllBtn(){
        startBtn.setDisable(true);
        creditBtn.setDisable(true);
        optionsBtn.setDisable(true);
        exitBtn.setDisable(true);
    }

    //enable all button
    private void enableAllBtn(){
        startBtn.setDisable(false);
        creditBtn.setDisable(false);
        optionsBtn.setDisable(false);
        exitBtn.setDisable(false);
    }

    @Override
    public void displayCredit() {
        Stage displayStage = new Stage();

        final TextArea creditText = new TextArea();
        var displayScene = new Scene(creditText, 300, 350);

        creditText.setText("Dope Game Party by \n"+"Riccardo Squarcialupi");
        creditText.setEditable(false);

        displayStage.setScene(displayScene);
        displayStage.setTitle("Dope Game Party-Credits");
        displayStage.show();
        
    }

    @Override
    public void displayOptions() {

    }

    @Override
    public void exitGUI() {
        System.exit(1);
    }

    @Override
    public void startGame() {
        //startgame with variable from input
    }
}
