package it.dpg.minigames.molegame.controller;

import it.dpg.minigames.base.controller.MinigameCycle;

public interface HitTheMoleCycle extends MinigameCycle {

    /**
     * check if the mole press is out or in
     */
    void pressOnAMole(int whichMole);

    /**
     * push out a new mole
     */
    void moleOutOrIn();

    /**
     * update the view
     */
    void updateView();

}
