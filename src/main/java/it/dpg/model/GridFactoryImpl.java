package it.dpg.model;

import org.apache.commons.lang3.tuple.ImmutablePair;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;

public class GridFactoryImpl implements GridFactory {

    private final GridType grid_type;

    private Cell first;
    private Cell last;
    private Map<Cell, ImmutablePair<Integer, Integer>> grid;
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
            jsonString = Files.readString(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getJsonString() {
        return this.jsonString;
    }

    @Override
    public void makeGrid() {
        this.setJson();
    }

    @Override
    public Map<Cell, ImmutablePair<Integer, Integer>> getGrid() {
        return this.grid;
    }

    @Override
    public Cell getFirst() {
        return this.first;
    }

    @Override
    public Cell getLast() {
        return this.last;
    }
}
