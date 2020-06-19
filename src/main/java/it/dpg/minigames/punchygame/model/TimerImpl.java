package it.dpg.minigames.punchygame.model;

public class TimerImpl implements Timer {
    private int timeLeft;

    public TimerImpl() {
        timeLeft = 20;
    }

    public void timerDecrease() {
        timeLeft--;
    }

    public void timerIncrease() {
        timeLeft += 3;
        if(timeLeft > 20) {
            timeLeft = 20;
        }
    }

    public int getTimeLeft() {
        return timeLeft;
    }
}
