package tz.airflights.models.stat;

import tz.airflights.models.CrewMember;

import java.util.HashSet;
import java.util.Set;

public class StatDto {
    private CrewMember crewMember;
    private Set<MonthStat> monthStats;

    public StatDto() {
        this.monthStats = new HashSet<>();
    }

    public CrewMember getCrewMember() {
        return crewMember;
    }

    public void setCrewMember(CrewMember crewMember) {
        this.crewMember = crewMember;
    }

    public Set<MonthStat> getMonthStats() {
        return monthStats;
    }

    public void setMonthStats(Set<MonthStat> monthStats) {
        this.monthStats = monthStats;
    }
}
