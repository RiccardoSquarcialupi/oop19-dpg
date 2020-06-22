package it.dpg.maingame.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GridInitializerImpl implements GridInitializer {

    private Map<Cell, Pair<Integer, Integer>> gridMap = new HashMap<>();
    private Grid grid;
    private String jsonString;

    /**
     * this methods is used by the GridInitializer to set the json file
     */
    private void setJson(GridType gridType) {

        String path;

        /*The json is set based on the grid type*/
        if (gridType.equals(GridType.GRID_ONE)) {
            path = "src/main/resources/json/grid1.json";
        } else {
            path = null;
        }

        try {
            if (path != null) {
                jsonString = Files.readString(Paths.get(path));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Grid makeGrid(GridType gridType) {

        setJson(gridType); //the json is set based on the grid Type

        Map<Integer, Cell> tempList = new HashMap<>();   //temporary List of Cells
        Map<Integer, int[]> tempNext = new HashMap<>();  //temporary list of references to next cells
        int prev = 0;                                       //reference to previous Cell

        ObjectMapper mapper = new ObjectMapper();        //mapper class from jackson is used to extract elements from the json

        CellParser[] mp;    //Cell Parser array

        {
            try {

                mp = mapper.readValue(jsonString, CellParser[].class);  //fills Cell Parser array with the elements in the json file

                for (var i : mp) {      //generates a temporary Array of Cells; it's missing the connections between cells.
                    boolean isFork;
                    isFork = i.getNext().length > 1;            //checks if the cell leads to a fork
                    tempList.put(i.getId(), new CellImpl(isFork, new ImmutablePair<>(i.getX_coordinate(), i.getY_coordinate()), CellType.valueOf(i.getCell_type())));
                    tempNext.put(i.getId(), i.getNext());
                    prev = i.getPrev();
                }

                for (var i : tempList.entrySet()) {             //this cycle sets the next Cells linked to a Cell and puts the Cells in the Grid
                    Set<Cell> next = new HashSet<>();
                    int cellId = i.getKey();
                    Cell previousCell;

                    if (tempNext.get(cellId).length > 0) {
                        for (var j : tempNext.get(cellId)) {   //every linked Cell is put in the "next" field of cell
                            next.add(tempList.get(j));         //finds the next Cell in the temporary list created and saves it
                        }
                    }
                    if (i.getKey() == prev) {
                        previousCell = i.getValue();
                    } else {
                        throw new IllegalStateException();
                    }
                    i.getValue().setNext(next);
                    i.getValue().setPrevious(previousCell);
                    gridMap.put(i.getValue(), i.getValue().getCoordinates());
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
        this.grid = new GridImpl(this.getFirst(), this.getLast(), this.gridMap);
        return this.grid;

    }

    @Override
    public Grid getGrid() {
        //this exception is thrown if the grid hasn't been created yet
        if (this.grid == null) {
            throw new IllegalStateException();
        }
        return this.grid;
    }

    @Override
    public Cell getFirst() {
        for (var i : gridMap.entrySet()) {      //searches for the cell of type "START" and returns it
            if (i.getKey().getType().equals(CellType.START)) {
                return i.getKey();
            }
        }
        return null;
    }

    @Override
    public Cell getLast() {
        for (var i : gridMap.entrySet()) {      //searches for the cell of type "END" and returns it
            if (i.getKey().getType().equals(CellType.END)) {
                return i.getKey();
            }
        }
        return null;
    }

    public String getJsonString() {
        return this.jsonString;
    }
}