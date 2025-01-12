package tz.airflights.models.stat;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class MonthStat {
    private String month;
    private int monthNumber;
    private int year;
    private Map<Integer, DayStat> dayStatMap;
    private Duration flightTime;
    private boolean isFlightTimeMore80HoursForMonth;
    private boolean isFlightTimeMore36HoursForSomeWeek;
    private boolean isFlightTimeMore8HoursForSomeDay;

    public MonthStat() {
        this.dayStatMap = new HashMap<>();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getMonthNumber() {
        return monthNumber;
    }

    public void setMonthNumber(int monthNumber) {
        this.monthNumber = monthNumber;
        determineMonth();
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Map<Integer, DayStat> getDayStatMap() {
        return dayStatMap;
    }

    public void setDayStatMap(Map<Integer, DayStat> dayStatMap) {
        this.dayStatMap = dayStatMap;
    }

    public Duration getFlightTime() {
        return flightTime;
    }

    public void setFlightTime(Duration flightTime) {
        this.flightTime = flightTime;
    }

    public boolean isFlightTimeMore80HoursForMonth() {
        return isFlightTimeMore80HoursForMonth;
    }

    public void setFlightTimeMore80HoursForMonth(boolean flightTimeMore80HoursForMonth) {
        isFlightTimeMore80HoursForMonth = flightTimeMore80HoursForMonth;
    }

    public boolean isFlightTimeMore36HoursForSomeWeek() {
        return isFlightTimeMore36HoursForSomeWeek;
    }

    public void setFlightTimeMore36HoursForSomeWeek(boolean flightTimeMore36HoursForSomeWeek) {
        isFlightTimeMore36HoursForSomeWeek = flightTimeMore36HoursForSomeWeek;
    }

    public boolean isFlightTimeMore8HoursForSomeDay() {
        return isFlightTimeMore8HoursForSomeDay;
    }

    public void setFlightTimeMore8HoursForSomeDay(boolean flightTimeMore8HoursForSomeDay) {
        isFlightTimeMore8HoursForSomeDay = flightTimeMore8HoursForSomeDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MonthStat monthStat = (MonthStat) o;
        return year == monthStat.year && monthNumber == monthStat.monthNumber;
    }

    @Override
    public int hashCode() {
        return Objects.hash(monthNumber, year);
    }

    private void determineMonth() {
        switch (this.monthNumber) {
            case 1:
                this.month = "Январь";
                break;
            case 2:
                this.month = "Февраль";
                break;
            case 3:
                this.month = "Март";
                break;
            case 4:
                this.month = "Апрель";
                break;
            case 5:
                this.month = "Май";
                break;
            case 6:
                this.month = "Июнь";
                break;
            case 7:
                this.month = "Июль";
                break;
            case 8:
                this.month = "Август";
                break;
            case 9:
                this.month = "Сентябрь";
                break;
            case 10:
                this.month = "Октябрь";
                break;
            case 11:
                this.month = "Ноябрь";
                break;
            case 12:
                this.month = "Декабрь";
                break;
        }
    }
}
