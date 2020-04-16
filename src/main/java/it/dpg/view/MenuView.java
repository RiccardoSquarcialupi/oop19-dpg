package it.dpg.view;

public interface MenuView {
    //difficulty for AI settings
    enum Difficulty {
        EASY,MEDIUM,HARD;
    }

    //display credit in a new scene
    void displayCredit();

    //display options in a new scene
    void displayOptions();

    //exit the GUI
    void exitGUI();

    //initialize the game with input parameters from option
    void startGame();
    
}
