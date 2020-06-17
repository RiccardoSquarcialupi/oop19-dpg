package it.dpg.maingame.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GridInitializerImpl implements GridInitializer {

    private Map<Cell, ImmutablePair<Integer, Integer>> gridMap = new HashMap<>();
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

        List<Cell> tempList = new ArrayList<>();         //temporary List of Cells
        Map<Integer, int[]> tempNext = new HashMap<>();  //temporary list of references to next cells

        ObjectMapper mapper = new ObjectMapper();        //mapper class from jackson is used to extract elements from the json

        CellParser[] mp;    //Cell Parser array

        {
            try {

                mp = mapper.readValue(jsonString, CellParser[].class);  //fills Cell Parser array with the elements in the json file

                for (var i : mp) {      //generates a temporary Array of Cells; it's missing the connections between cells.
                    boolean isFork;
                    isFork = i.getNext().length > 1;            //checks if the cell leads to a fork
                    tempList.add(new CellImpl(isFork, new ImmutablePair<>(i.getX_coordinate(), i.getY_coordinate()), CellType.valueOf(i.getCell_type())));
                    tempNext.put(i.getId(), i.getNext());
                }

                for (var i : tempList) {                        //this cycle sets the next Cells linked to a Cell and puts the Cells in the Grid
                    Set<Cell> next = new HashSet<>();
                    int cellId = tempList.indexOf(i);           //gets index of Cell inside tempList

                    if (tempNext.get(cellId).length > 0) {
                        for ( var j : tempNext.get(cellId)) {   //every linked Cell is put in the "next" field of cell
                        next.add(tempList.get(j));              //finds the next Cell in the temporary list created and saves it
                        }
                    }
                    i.setNext(next);
                    gridMap.put(i, i.getCoordinates());
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