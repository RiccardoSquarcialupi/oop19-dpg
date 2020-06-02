package it.dpg.gridTest;

import it.dpg.maingame.model.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Set;

public class gridInitializerTest {

    @Test
    public void createGrid(){
        GridInitializer gridFact = new GridInitializerImpl();

        Assertions.assertThrows( IllegalStateException.class, gridFact::getGrid);

        Grid grid = gridFact.makeGrid(GridType.GRID_ONE);

        Set<Cell> cell = grid.getCellByCoordinates(0,0).getNext();

        Assertions.assertEquals(CellType.START, grid.getCellByCoordinates(0, 0).getType());
        Assertions.assertTrue(cell.contains(grid.getCellByCoordinates(0, 1)));
    }


}
