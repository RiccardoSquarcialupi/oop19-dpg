package it.dpg.controller;

import it.dpg.view.MenuView;

import java.util.HashMap;
import java.util.Map;

public class MenuControllerImpl implements MenuController {

    private Map<String, MenuView.Difficulty> mapAI = new HashMap<>();
    private Map<Integer, String> mapPlayer = new HashMap<>();

    public MenuControllerImpl(){}


    @Override
    public void setOptionsPlayer(Map<Integer, String> optionPlayer) {
        mapPlayer.clear();
        mapPlayer.putAll(optionPlayer);
    }
    @Override
    public void setOptionsAI(Map<String, MenuView.Difficulty> optionsAI) {
        mapAI.clear();
        mapAI.putAll(optionsAI);
    }

    @Override
    public Map<Integer, String> getOptionsPlayer() {
        return mapPlayer;
    }

    @Override
    public Map<String, MenuView.Difficulty> getOptionsAI() {
        return mapAI;
    }
}
