package tz.airflights.models.stat;

import tz.airflights.models.CrewMember;

import java.util.HashMap;
import java.util.Map;

public class Stat {
    private CrewMember member;
    private Map<Integer,Map<Integer, MonthStat>> yearMonthStatMap;

    public Stat() {
        this.yearMonthStatMap = new HashMap<>();
    }

    public CrewMember getMember() {
        return member;
    }

    public void setMember(CrewMember member) {
        this.member = member;
    }

    public Map<Integer, Map<Integer, MonthStat>> getYearMonthStatMap() {
        return yearMonthStatMap;
    }

    public void setYearMonthStatMap(Map<Integer, Map<Integer, MonthStat>> yearMonthStatMap) {
        this.yearMonthStatMap = yearMonthStatMap;
    }
}
