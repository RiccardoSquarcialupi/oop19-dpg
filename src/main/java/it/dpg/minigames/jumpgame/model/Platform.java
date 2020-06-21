package it.dpg.minigames.jumpgame.model;

public class Platform extends AbstractGameObject {

    private final int id;

    public Platform(final int x, final int y, final int width, final int height, final int id) {
        super(x, y, width, height);
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
