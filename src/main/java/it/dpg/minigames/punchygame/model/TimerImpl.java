package it.dpg.minigames.punchygame.model;

public class TimerImpl implements Timer {

    private float timeLeft;
    private static final float MAX_TIME = 10L;

    public TimerImpl() {
        timeLeft = MAX_TIME;
    }

    @Override
    public void timerDecrease(final float elapsed) {
        timeLeft -= elapsed;
    }

    @Override
    public void timerIncrease() {
        timeLeft += 0.10;
        if(timeLeft > MAX_TIME) {
            timeLeft = MAX_TIME;
        }
    }

    @Override
    public float getTimeLeft() {
        return timeLeft;
    }
}
