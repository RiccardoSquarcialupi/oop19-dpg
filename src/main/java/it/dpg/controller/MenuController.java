package it.dpg.controller;

import java.util.Map;

public interface MenuController {

    void setOptions(Map<String,Boolean> mapOption);
    Map<String,Boolean> getOptions();

}
