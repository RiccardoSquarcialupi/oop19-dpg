package it.dpg.controller;

import it.dpg.view.MenuView;

import java.util.HashMap;
import java.util.Map;

public interface MenuController {

    //set option from Map
    public void setOptionsPlayer(Map<Integer, String> optionPlayer);
    public void setOptionsAI(Map<String, MenuView.Difficulty> optionsAI);

    //return the Map of options
    Map<Integer,String> getOptionsPlayer();
    Map<String, MenuView.Difficulty> getOptionsAI();



}
