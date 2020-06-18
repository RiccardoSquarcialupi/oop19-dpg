package it.dpg.minigames.punchygame.model;

public class BoxerImpl implements Boxer {

    private Direction direction;

    public BoxerImpl() {
        direction = Direction.RIGHT;
    }

    @Override
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public Direction getDirection() {
        return direction;
    }
}
