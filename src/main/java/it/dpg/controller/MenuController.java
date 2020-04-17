package it.dpg.controller;

import it.dpg.view.MenuView;

import java.util.Map;

/**
 * Interface for MenuControllerImpl
 * @author Riccardo Squarcialupi
 */
public interface MenuController {

    /**
     * Save player's option from Map
     * @param optionPlayer Map used for pass at the method the settings of the Player to save
     */
    void setOptionsPlayer(Map<Integer, String> optionPlayer);

    /**
     * Save AI's option from Map
     * @param optionsAI Map used for pass at the method the settings of the AI to save
     */
    void setOptionsAI(Map<String, MenuView.Difficulty> optionsAI);

    /**
     * return the Map of Player options
     */
    Map<Integer, String> getOptionsPlayer();

    /**
     * return the Map of AI options
     */
    Map<String, MenuView.Difficulty> getOptionsAI();


}
