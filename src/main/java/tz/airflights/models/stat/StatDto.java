package tz.airflights.models.stat;

import tz.airflights.models.CrewMember;

import java.util.HashSet;
import java.util.Set;

public class StatDto {
    private CrewMember member;
    private Set<MonthStat> monthStats;

    public StatDto() {
        this.monthStats = new HashSet<>();
    }

    public CrewMember getMember() {
        return member;
    }

    public void setMember(CrewMember member) {
        this.member = member;
    }

    public Set<MonthStat> getMonthStats() {
        return monthStats;
    }

    public void setMonthStats(Set<MonthStat> monthStats) {
        this.monthStats = monthStats;
    }
}
