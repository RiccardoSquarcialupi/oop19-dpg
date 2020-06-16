package it.dpg.minigames.ballgame.model;


import java.awt.*;
import java.awt.geom.Point2D;

public abstract class AbstractBoundary implements Boundary {
    protected final Point2D start;
    protected final Point2D end;
    private final CollisionType collisionType;

    AbstractBoundary(final double startX, final double startY, final double endX, final double endY, final CollisionType type) {
        checkCoordinate(startX);
        checkCoordinate(startY);
        checkCoordinate(endX);
        checkCoordinate(endY);
        start = new Point2D.Double(startX, startY);
        end = new Point2D.Double(endX, endY);
        this.collisionType = type;
    }

    protected void checkCoordinate(double coordinate) {
        if(coordinate < 0 || coordinate > 100) {
            throw new IllegalArgumentException();
        }
    }

    @Override
    public double getStartX() {
        return this.start.getX();
    }

    @Override
    public double getStartY() {
        return this.start.getY();
    }

    @Override
    public double getEndX() {
        return this.end.getX();
    }

    @Override
    public double getEndY() {
        return this.end.getY();
    }

    @Override
    public CollisionType getCollisionType() {
        return this.collisionType;
    }
}
