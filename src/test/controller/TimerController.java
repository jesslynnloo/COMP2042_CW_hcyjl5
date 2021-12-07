package test.controller;

import java.util.Timer;

public class TimerController {
    private TimerTaskController timerTaskController;
    private Timer timer;


    public TimerController(TimerTaskController timerTaskController, Timer timer) {
        this.timerTaskController = timerTaskController;
        this.timer = timer;

        timer.scheduleAtFixedRate(timerTaskController, 60000, 60000);
    }
}
