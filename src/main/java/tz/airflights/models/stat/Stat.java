package tz.airflights.models.stat;

import tz.airflights.models.CrewMember;

import java.time.YearMonth;
import java.util.HashMap;
import java.util.Map;

public class Stat {
    private CrewMember crewMember;
    private Map<YearMonth, MonthStat> yearMonthStatMap;

    public Stat() {
        this.yearMonthStatMap = new HashMap<>();
    }

    public CrewMember getCrewMember() {
        return crewMember;
    }

    public void setCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }

    public Map<YearMonth, MonthStat> getYearMonthStatMap() {
        return yearMonthStatMap;
    }

    public void setYearMonthStatMap(Map<YearMonth, MonthStat> yearMonthStatMap) {
        this.yearMonthStatMap = yearMonthStatMap;
    }
}
