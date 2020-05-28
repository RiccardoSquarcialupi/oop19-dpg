package it.dpg.maingame.view.menu;

/**
 * Interface for the MenuGUI
 *
 * @author Riccardo Squarcialupi
 */
public interface MenuView {

    /**
     * display credit in a new scene
     */
    void displayCredit();

    /**
     * display options in a new scene
     */
    void displayOptions();

    /**
     * exit the GUI
     */
    void exitGUI();

    /**
     * initialize the game with input parameters from option
     */
    void startGame();

}
