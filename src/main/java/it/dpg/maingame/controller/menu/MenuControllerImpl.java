package it.dpg.maingame.controller.menu;

import it.dpg.maingame.model.character.Difficulty;

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
    private Map<String, Difficulty> mapAI = new HashMap<>();
    private Map<Integer, String> mapPlayer = new HashMap<>();

    /**
     * Empty Constructor
     */
    public MenuControllerImpl() {
    }

    /**
     * @return mapPlayer, map of the selected options for the Players
     */
    @Override
    public Map<Integer, String> getOptionsPlayer() {
        return mapPlayer;
    }

    /**
     * Used for save Players' settings from GUI
     *
     * @param numPlayer number of PLayer that are in game
     */
    @Override
    public void setOptionsPlayer(Integer numPlayer) {
        mapPlayer.clear();
        for (int i = numPlayer; i >= 1; i--) {
            mapPlayer.put(i, "Giocatore" + i);
        }

    }

    /**
     * @return mapAI, map of the selected options for the AI
     */
    @Override
    public Map<String, Difficulty> getOptionsAI() {
        return mapAI;
    }

    /**
     * Used for save AI's settings from GUI
     *
     * @param numAI number of AI that are in game
     */
    @Override
    public void setOptionsAI(Integer numAI) {
        mapAI.clear();
        for (int i = numAI; i >= 1; i--) {
            mapAI.putIfAbsent("AI" + i, Difficulty.EASY);
        }

    }

    /**
     * @param whichAI AI to set Difficulty
     * @param dif     which Difficulty to set
     */
    @Override
    public void setAIDifficulty(Integer whichAI, Difficulty dif) {
        mapAI.replace("AI" + (whichAI + 1), dif);
        System.out.println(mapAI.toString());
    }
}
