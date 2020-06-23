package it.dpg.minigames.jumpgame.model;

public class Platform extends AbstractGameObject {

    private final int id;
    private boolean exist = true;
    private boolean hit = false;

    public Platform(final int x, final int y, final int width, final int height, final int id) {
        super(x, y, width, height);
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public boolean doesExist() {
        return exist;
    }

    public void destroy() {
        exist = false;
    }

    public void hit() {
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }
}
