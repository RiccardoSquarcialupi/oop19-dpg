package it.dpg.gridTest;

import javafx.application.Application;
import javafx.stage.Stage;

public class GridViewTest extends Application {

    private static Stage pStage;

    public static void main (String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {

        /*
         * This test runs when a different stage is set. Now the stage is the same of Main.
         */

        /*
        GridView view = new GridViewGeneratorImpl(GridType.GRID_ONE).generate().getRight();
        stage.setScene(view.getScene());
        stage.show();

        view.setCurrentPlayerName("Giovanna");
        view.setRemainingMoves(3);

        view.setCurrentPlayerName("Marco");
        view.setRemainingMoves(2);

        view.setCurrentPlayerName("Davide");
        view.setRemainingMoves(1);

        Set<ImmutablePair<Integer, Integer>> cellSet = new HashSet<>();
        cellSet.add(new ImmutablePair<>(1,5));
        cellSet.add(new ImmutablePair<>(2,4));
        view.enableDirectionChoice(cellSet);
        view.disableDirectionChoice();

        view.showText("hello");
        view.removeText();

        Map<Integer, ImmutablePair<Integer, Integer>> players = new HashMap<>();
        players.put(1, new ImmutablePair<>(1,0));
        players.put(2, new ImmutablePair<>(1,0));
        players.put(3, new ImmutablePair<>(1,0));
        view.updatePlayers(players);

        view.enableDiceThrow(Dice.D8);
        view.disableDiceThrow();


         */
    }

}
