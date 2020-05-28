package it.dpg.maingame.controller.gamecycle.player;

import it.dpg.maingame.controller.gamecycle.playercontroller.HumanPlayerController;
import it.dpg.maingame.controller.gamecycle.playercontroller.PlayerController;
import it.dpg.maingame.controller.gamecycle.turnmanagement.TurnState;
import it.dpg.maingame.model.Grid;
import it.dpg.maingame.model.character.CharacterImpl;
import it.dpg.maingame.model.character.Cpu;
import it.dpg.maingame.model.character.Difficulty;
import it.dpg.maingame.view.GridView;
import it.dpg.maingame.model.character.Character;

import java.util.Set;

public class PlayerFactoryImpl implements PlayerFactory{

    private final TurnState turnstate;
    private final GridView gridView;
    private final Grid grid;
    private int nextId = 1;

    public PlayerFactoryImpl(TurnState turnState, GridView view, Grid grid, Set<Cpu> cpuSet) {
        this.turnstate = turnState;
        this.gridView = view;
        this.grid = grid;
    }

    @Override
    public Player createHumanPlayer(String name) {
        Character character = new CharacterImpl(nextId, name, grid);
        PlayerController controller = new HumanPlayerController(turnstate, gridView);
        nextId++;
        return new PlayerImpl(character, controller);
    }

    @Override
    public Player createCpu(String name, Difficulty difficulty) {
        //TODO implement once CpuImpl is available
        /*Character character = new CharacterImpl(nextId, name, grid);
        PlayerController controller = new CpuPlayerController(turnstate, gridView, );*/
        throw new UnsupportedOperationException();
    }
}
