package ds.util;

public class LogicalClock {

    private int clock;

    public LogicalClock() {
        this.clock = 0;
    }

    public synchronized void increaseClock() {
        this.clock++;
    }

    public synchronized void updateClock(int received_clock) {
        this.clock = Math.max(received_clock, this.clock) + 1;
    }

    public synchronized int getClock() {
        return clock;
    }

}
