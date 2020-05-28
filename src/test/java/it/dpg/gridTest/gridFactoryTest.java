package it.dpg.gridTest;

import it.dpg.maingame.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class gridFactoryTest {

    @Test
    public void createGrid(){
        GridFactory gridFact = new GridFactoryImpl(GridType.GRID_ONE);
        gridFact.makeGrid();
        Grid grid = new GridImpl(gridFact.getFirst(), gridFact.getLast(), gridFact.getGrid());

        Set<Cell> cell = grid.getCellByCoordinates(0,0).getNext();

        Assertions.assertEquals(CellType.START, grid.getCellByCoordinates(0, 0).getType());
        Assertions.assertTrue(cell.contains(grid.getCellByCoordinates(0, 1)));
    }

}
