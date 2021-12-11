package game.controller;

import java.util.Timer;

/**
 * This is the TimerController class.
 */
public class TimerController {
    private TimerTaskController timerTaskController;
    private Timer timer;


    /**
     * Class constructor.
     * Schedule the task 1 minutes after the timer start.
     * Execute the task every 1 minute.
     * @param timerTaskController The TimerTaskController object.
     * @param timer The Timer object.
     */
    public TimerController(TimerTaskController timerTaskController, Timer timer) {
        this.timerTaskController = timerTaskController;
        this.timer = timer;

        timer.scheduleAtFixedRate(timerTaskController, 60000, 60000);
    }
}
