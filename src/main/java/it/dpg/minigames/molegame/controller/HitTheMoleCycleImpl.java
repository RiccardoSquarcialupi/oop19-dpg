package it.dpg.minigames.molegame.controller;

import it.dpg.minigames.molegame.model.*;
import it.dpg.minigames.molegame.view.HitTheMoleView;
import it.dpg.minigames.molegame.view.HitTheMoleViewImpl;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HitTheMoleCycleImpl implements  HitTheMoleCycle {

    private List<Pair<Integer, Mole>> moleList = new ArrayList<>();
    private List<Integer> moleOut = new ArrayList<>();
    private Score score = new ScoreImpl();
    private Timer timer = new TimerImpl();
    HitTheMoleView moleView = new HitTheMoleViewImpl();
    private final static int NMOLES= 25;

    public HitTheMoleCycleImpl(){
        for(int i=0;i<NMOLES;i++){
            moleList.add(new Pair<>(i,new MoleImpl()));
        }
    }

    @Override
    public int startCycle(){
        moleView.setView();
        timer.timeStart();

        while(!timer.checkTimeIsUp()){

            moleOutOrIn();
            updateView();

            moleOutOrIn();
            updateView();

        }

        return score.finalScore();
    }

    /**
     *  check if the mole press is out or in
     */
    @Override
    public void pressOnAMole(int whichMole) {
        if(moleList.get(whichMole).getValue().isOut()){
            score.addPoint();
            moleView.updateScore();
            moleList.get(whichMole).getValue().setMoleIn();
            moleOut.remove(whichMole);

        }

    }

    /**
     * push out or in a new mole random
     */
    @Override
    public void moleOutOrIn() {
        Random r = new Random();
        int i=r.nextInt(15);
        if(!moleList.get(i).getValue().isOut()){
            moleList.get(i).getValue().setMoleOut();
            moleOut.add(i);
        }
        else{
            moleList.get(i).getValue().setMoleIn();
            moleOut.remove(i);
        }
    }

    /**
     * update the view
     */
    @Override
    public void updateView() {
        moleView.updateMole(moleOut);
    }

}
