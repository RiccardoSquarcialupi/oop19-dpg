package it.dpg.controller;

import java.util.Map;

public interface MenuController {

    //set option from Map
    void setOptions(Map<String,Boolean> mapOption);

    //return the Map of options
    Map<String,Boolean> getOptions();

}
