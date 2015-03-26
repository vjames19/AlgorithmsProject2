package com.github.vjames19;

/**
 * Created by vjames19 on 3/5/15.
 */
public class Timer {

    private long now;

    public Timer() {
    }

    public void start() {
        now = System.nanoTime();
    }

    public long stop() {
        return System.nanoTime() - now;
    }
}
