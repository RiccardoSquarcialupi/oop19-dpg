package it.dpg.controller;

import it.dpg.view.MenuView;

import java.util.HashMap;
import java.util.Map;

/**
 * Implementation of MenuController Interface
 *
 * @author Riccardo Squarcialupi
 */
public class MenuControllerImpl implements MenuController {
    /**
     * mapAI, map used for saving AI settings from GUI
     * mapPlayer, map used for saving Player settings from GUI
     */
    private Map<String, MenuView.Difficulty> mapAI = new HashMap<>();
    private Map<Integer, String> mapPlayer = new HashMap<>();

    /**
     * Empty Constructor
     */
    public MenuControllerImpl() {
    }

    /**
     * Used for save Players' settings from GUI
     *
     * @param optionPlayer Map used for pass at the method the settings of the Player to save
     */
    @Override
    public void setOptionsPlayer(Map<Integer, String> optionPlayer) {
        mapPlayer.clear();
        if (!optionPlayer.isEmpty()) {
            mapPlayer.putAll(optionPlayer);
        }

    }

    /**
     * Used for save AI's settings from GUI
     *
     * @param optionsAI Map used for pass at the method the settings of the AI to save
     */
    @Override
    public void setOptionsAI(Map<String, MenuView.Difficulty> optionsAI) {
        mapAI.clear();
        if (!optionsAI.isEmpty()) {
            mapAI.putAll(optionsAI);
        }
    }

    /**
     * @return mapPlayer, map of the selected options for the Players
     */
    @Override
    public Map<Integer, String> getOptionsPlayer() {
        return mapPlayer;
    }

    /**
     * @return mapAI, map of the selected options for the AI
     */
    @Override
    public Map<String, MenuView.Difficulty> getOptionsAI() {
        return mapAI;
    }
}
