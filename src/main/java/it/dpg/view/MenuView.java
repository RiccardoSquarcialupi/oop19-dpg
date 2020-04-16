package it.dpg.view;

public interface MenuView {

    enum Difficulty {
        EASY,MEDIUM,HARD;
    }

    void displayCredit();
    void displayOptions();
    void exitGUI();
    void startGame();
    
}
