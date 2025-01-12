package tz.airflights.models.stat;

import java.time.Duration;

public class DayStat {
    private int day;
    private Duration flightTime;

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public Duration getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Duration flightTime) {
        this.flightTime = flightTime;
    }
}
