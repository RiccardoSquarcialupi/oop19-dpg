package it.dpg.maingame.model;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class GridFactoryImpl implements GridFactory {

    private final GridType grid_type;

    private Cell first;
    private Cell last;
    private Map<Cell, ImmutablePair<Integer, Integer>> grid = new HashMap<>();
    private String jsonString;

    public GridFactoryImpl (GridType grid_type) {
        this.grid_type = grid_type;
    }

    @Override
    public void setJson() {

        String path;

        if (grid_type.equals(GridType.GRID_ONE)) {
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
    public void makeGrid() {

        this.setJson();

        List<Cell> tempList = new ArrayList<>();   //temporary List of Cells
        Map<Integer, int[]> tempNext = new HashMap<>();  //temporary list of references to next cells

        ObjectMapper mapper = new ObjectMapper();

        CellParser[] mp;    //Cell Parser array

        {
            try {

                mp = mapper.readValue(jsonString, CellParser[].class);  //fills Cell Parser array with the elements in the json file

                for (var i : mp) {  //generates a temporary Array of Cells
                    boolean isFork;
                    isFork = i.getNext().length > 1;
                    tempList.add(new CellImpl(isFork, new ImmutablePair<>(i.getX_coordinate(), i.getY_coordinate()), CellType.valueOf(i.getCell_type())));
                    tempNext.put(i.getId(), i.getNext());
                }

                for (var i : tempList) {        //this cycle sets the next Cells linked to a Cell and puts the Cells in the Grid
                    Set<Cell> next = new HashSet<>();
                    int cellId = tempList.indexOf(i);   //gets index of Cell inside tempList

                    if (tempNext.get(cellId).length > 0) {
                        for ( var j : tempNext.get(cellId)) {
                        next.add(tempList.get(j));
                        }
                    }
                    i.setNext(next);
                    grid.put(i, i.getCoordinates());
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public Map<Cell, ImmutablePair<Integer, Integer>> getGrid() {
        return this.grid;
    }

    @Override
    public Cell getFirst() {
        for (var i : grid.entrySet()) {
            if (i.getKey().getType().equals(CellType.START)) {
                return i.getKey();
            }
        }
        return null;
    }

    @Override
    public Cell getLast() {
        for (var i : grid.entrySet()) {
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