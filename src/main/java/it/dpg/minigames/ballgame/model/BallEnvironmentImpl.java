package it.dpg.minigames.ballgame.model;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class BallEnvironmentImpl implements BallEnvironment {
    private final double radius;
    private double centerX;
    private double centerY;
    private final double startX;
    private final double startY;
    private final Set<Boundary> boundaries;
    private Boundary lastCollision;
    private boolean lastFrameCollision = false;
    private final double deltaT;
    private boolean wasGoalReached = false;
    private double timePassed = 0;
    private final double ballAcceleration = 15;
    private final double ballDeceleration = 7;
    private final double maxSpeed = 20;
    private final int maxScore;
    private double xSpeed = 0;
    private double ySpeed = 0;

    public BallEnvironmentImpl(double startX, double startY, double radius, Set<Boundary> boundaries, int expectedFPS, int maxScore) {
        this.radius = radius;
        this.startX = startX;
        this.startY = startY;
        this.centerX = startX;
        this.centerY = startY;
        this.boundaries = boundaries;
        this.deltaT = 1d / expectedFPS;
        this.maxScore = maxScore;
    }

    @Override
    public double getX() {
        return this.centerX;
    }

    @Override
    public double getY() {
        return this.centerY;
    }

    @Override
    public int getScore() {
        int score = maxScore - ((int)(timePassed * 20));
        return Math.max(score, 0);
    }

    @Override
    public void nextFrame(boolean isGoingUp, boolean isGoingDown, boolean isGoingLeft, boolean isGoingRight) {
        timePassed += deltaT;
        calculateSpeed(isGoingUp, isGoingDown, isGoingLeft, isGoingRight);
        calculatePosition();
        Optional<Boundary> collision = detectCollision();
        if(collision.isEmpty()) {
            return;
        }
        Boundary boundary = collision.get();
        handleCollision(boundary);
    }

    private void calculatePosition() {
        this.centerX = centerX + xSpeed * deltaT;
        this.centerY = centerY + ySpeed * deltaT;
        centerX = limitVal(centerX, 0, 100);
        centerY = limitVal(centerY, 0, 100);
    }

    private void calculateSpeed(boolean isGoingUp, boolean isGoingDown, boolean isGoingLeft, boolean isGoingRight) {
        double xAcc;
        double yAcc;
        if((isGoingUp && isGoingDown) || (!isGoingUp && !isGoingDown)) {
            if (ySpeed > 0) {
                yAcc = -ballDeceleration;
            } else if (ySpeed < 0) {
                yAcc = ballDeceleration;
            } else {
                yAcc = 0;
            }
        } else if(isGoingUp) {
            yAcc = ballAcceleration;
            if(ySpeed < 0) {
                yAcc += ballDeceleration;
            }
        } else {
            yAcc = -ballAcceleration;
            if(ySpeed > 0) {
                yAcc -= ballDeceleration;
            }
        }
        if((isGoingLeft && isGoingRight) || (!isGoingLeft && !isGoingRight)) {
            if (xSpeed > 0) {
                xAcc = -ballDeceleration;
            } else if (xSpeed < 0) {
                xAcc = ballDeceleration;
            } else {
                xAcc = 0;
            }
        } else if(isGoingRight) {
            xAcc = ballAcceleration;
            if (xSpeed < 0) {
                xAcc += ballDeceleration;
            }
        } else {
            xAcc = -ballAcceleration;
            if (xSpeed > 0) {
                xAcc -= ballDeceleration;
            }
        }
        double xSpeedPrev = this.xSpeed;
        double ySpeedPrev = this.ySpeed;
        xSpeed = xSpeed + (xAcc * deltaT);
        ySpeed = ySpeed + (yAcc * deltaT);
        if((xSpeed > 0 && xSpeedPrev < 0) || (xSpeed < 0 && xSpeedPrev > 0)) {
            xSpeed = 0;
        }
        if((ySpeed > 0 && ySpeedPrev < 0) || (ySpeed < 0 && ySpeedPrev > 0)) {
            ySpeed = 0;
        }
        xSpeed = limitVal(xSpeed, -maxSpeed, maxSpeed);
        ySpeed = limitVal(ySpeed, -maxSpeed, maxSpeed);
    }

    private double limitVal(double val, double lowerBound, double upperBound) {
        val = Math.min(val, upperBound);
        return Math.max(val, lowerBound);
    }

    private Optional<Boundary> detectCollision() {
        for(Boundary b : boundaries) {
            if(!(lastFrameCollision && b.equals(lastCollision)) && b.isColliding(centerX, centerY, radius)) {
                lastCollision = b;
                lastFrameCollision = true;
                return Optional.of(b);
            }
        }
        lastFrameCollision = false;
        return Optional.empty();
    }

    private void handleCollision(Boundary collision) {
        if(collision.getCollisionType().equals(CollisionType.RESET)) {
            centerY = startY;
            centerX = startX;
            xSpeed = 0;
            ySpeed = 0;
        } else if(collision.getCollisionType().equals(CollisionType.GOAL)) {
            xSpeed = 0;
            ySpeed = 0;
            this.wasGoalReached = true;
        } else {
            if(collision.isHorizontal()) {
                ySpeed = -ySpeed;
                double minX = Math.min(collision.getEndX(), collision.getStartX());
                double maxX = Math.max(collision.getEndX(), collision.getStartX());
                if(minX > centerX || maxX < centerX) {
                    xSpeed = -xSpeed;
                }
            } else {
                xSpeed = -xSpeed;
                double minY = Math.min(collision.getEndY(), collision.getStartY());
                double maxY = Math.max(collision.getEndY(), collision.getStartY());
                if(minY > centerY || maxY < centerY) {
                    ySpeed = -ySpeed;
                }
            }
        }
    }

    @Override
    public boolean goalReached() {
        return wasGoalReached;
    }
}
