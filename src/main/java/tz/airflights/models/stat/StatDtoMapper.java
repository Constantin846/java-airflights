package tz.airflights.models.stat;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class StatDtoMapper {
    public StatDto toStatDto(Stat stat) {
        StatDto statDto = new StatDto();
        statDto.setCrewMember(stat.getCrewMember());
        Set<MonthStat> monthStats = new HashSet<>(stat.getYearMonthStatMap().values());
        statDto.setMonthStats(monthStats);
        return statDto;
    }

    public List<StatDto> toStatDto(List<Stat> stats) {
        return stats.stream()
                .map(this::toStatDto)
                .toList();
    }
}
