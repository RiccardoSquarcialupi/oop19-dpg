package it.dpg.minigames.jumpgame.model;

public class Player extends AbstractGameObject {

    private final int gravity;

    public Player(final int size, final int x, final int y, final int gravity, final int startingSpeed) {
        super(x, y, size, size);
        this.gravity = gravity;
        this.setSpeedY(startingSpeed);
    }

    @Override
    public void updatePosition() {
        super.updatePosition();
        super.setSpeedY(super.getSpeedY() - gravity);
    }
}
