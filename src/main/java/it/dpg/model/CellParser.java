package it.dpg.model;

public class CellParser {

    int id;
    int x_coordinate;
    int y_coordinate;
    int[] next;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getX_coordinate() {
        return x_coordinate;
    }

    public void setX_coordinate(int x_coordinate) {
        this.x_coordinate = x_coordinate;
    }

    public int getY_coordinate() {
        return y_coordinate;
    }

    public void setY_coordinate(int y_coordinate) {
        this.y_coordinate = y_coordinate;
    }

    public int[] getNext() {
        return next;
    }

    public void setNext(int[] next) {
        this.next = next;
    }
}
