package it.dpg.minigames.ballgame.model;

import java.util.Optional;
import java.util.Set;

public class BallEnvironmentImpl implements BallEnvironment {
    private final Set<Boundary> boundaries;
    private final double deltaT;
    private final int maxScore;
    private final Ball ball;
    private Boundary lastCollision;
    private boolean lastFrameCollision = false;
    private boolean wasGoalReached = false;
    private double timePassed = 0;

    public BallEnvironmentImpl(double startX, double startY, double radius, Set<Boundary> boundaries, int expectedFPS, int maxScore) {
        this.ball = new Ball(radius, startX, startY, 15, 7, 20);
        this.boundaries = boundaries;
        this.deltaT = 1d / expectedFPS;
        this.maxScore = maxScore;
    }

    @Override
    public double getX() {
        return this.ball.getCenterX();
    }

    @Override
    public double getY() {
        return this.ball.getCenterY();
    }

    @Override
    public int getScore() {
        int score = maxScore - ((int) (timePassed * 20));
        return Math.max(score, 0);
    }

    @Override
    public void nextFrame(boolean isGoingUp, boolean isGoingDown, boolean isGoingLeft, boolean isGoingRight) {
        timePassed += deltaT;
        ball.calculateNextPosition(isGoingUp, isGoingDown, isGoingLeft, isGoingRight, deltaT);
        Optional<Boundary> collision = detectCollision();
        if (collision.isEmpty()) {
            return;
        }
        Boundary boundary = collision.get();
        handleCollision(boundary);
    }

    private Optional<Boundary> detectCollision() {
        for (Boundary b : boundaries) {
            if (!(lastFrameCollision && b.equals(lastCollision)) && b.isBallColliding(ball.getCenterX(), ball.getCenterY(), ball.getRadius())) {
                lastCollision = b;
                lastFrameCollision = true;
                return Optional.of(b);
            }
        }
        lastFrameCollision = false;
        return Optional.empty();
    }

    private void handleCollision(Boundary collision) {
        if (collision.getCollisionType().equals(CollisionType.RESET)) {
            ball.reset();
        } else if (collision.getCollisionType().equals(CollisionType.GOAL)) {
            this.wasGoalReached = true;
        } else {
            if (collision.isHorizontal()) {
                ball.setySpeed(-ball.getySpeed());
                double minX = Math.min(collision.getEndX(), collision.getStartX());
                double maxX = Math.max(collision.getEndX(), collision.getStartX());
                if (minX > ball.getCenterX() || maxX < ball.getCenterX()) {
                    ball.setxSpeed(-ball.getxSpeed());
                }
            } else {
                ball.setxSpeed(-ball.getxSpeed());
                double minY = Math.min(collision.getEndY(), collision.getStartY());
                double maxY = Math.max(collision.getEndY(), collision.getStartY());
                if (minY > ball.getCenterY() || maxY < ball.getCenterY()) {
                    ball.setySpeed(-ball.getySpeed());
                }
            }
        }
    }

    @Override
    public boolean goalReached() {
        return wasGoalReached;
    }
}
