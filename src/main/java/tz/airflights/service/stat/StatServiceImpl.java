package tz.airflights.service.stat;

import tz.airflights.models.AirFlight;
import tz.airflights.models.CrewMember;
import tz.airflights.models.stat.DayStat;
import tz.airflights.models.stat.MonthStat;
import tz.airflights.models.stat.Stat;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatServiceImpl implements StatService {
    private Map<Long, Stat> memberIdStatMap;

    @Override
    public List<Stat> formStat(List<CrewMember> crew, List<AirFlight> airFlights) {
        memberIdStatMap = crew.stream()
                .collect(Collectors.toMap(CrewMember::getId, member -> {
                    Stat stat = new Stat();
                    stat.setMember(member);
                    return stat;
                }));

        calculateStat(airFlights);
        return memberIdStatMap.values().stream().toList();
    }

    private void calculateStat(List<AirFlight> airFlights) {
        formDayStats(airFlights);
        formMonthStats();
    }

    private void formDayStats(List<AirFlight> airFlights) {
        for (AirFlight airFlight : airFlights) {
            LocalDateTime dateTime = airFlight.getStartTime();
            Duration flightTime = Duration.between(dateTime, airFlight.getFinishTime());
            int year = dateTime.getYear();
            int month = dateTime.getMonthValue();
            int day = dateTime.getDayOfMonth();

            for (CrewMember member : airFlight.getCrew()) {
                Stat stat = memberIdStatMap.get(member.getId());
                Map<Integer, Map<Integer, MonthStat>> yearMonthStatMap = stat.getYearMonthStatMap();

                if (!yearMonthStatMap.containsKey(year)) {
                    yearMonthStatMap.put(year, new HashMap<>());
                }
                Map<Integer, MonthStat> monthStatMap = yearMonthStatMap.get(year);

                if (!monthStatMap.containsKey(month)) {
                    MonthStat newMonthStat = new MonthStat();
                    newMonthStat.setYear(year);
                    newMonthStat.setMonthNumber(month);
                    monthStatMap.put(month, newMonthStat);
                }
                MonthStat monthStat = monthStatMap.get(month);
                Map<Integer, DayStat> dayStatMap = monthStat.getDayStatMap();

                if (dayStatMap.containsKey(day)) {
                    DayStat dayStat = dayStatMap.get(day);
                    dayStat.setFlightTime(dayStat.getFlightTime().plus(flightTime));
                } else {
                    DayStat dayStat = new DayStat();
                    dayStat.setDay(day);
                    dayStat.setFlightTime(flightTime);
                    dayStatMap.put(day, dayStat);
                }
            }
        }
    }

    private void formMonthStats() {
        for (Stat stat : memberIdStatMap.values()) {
            Map<Integer, Map<Integer, MonthStat>> yearMonthStatMap = stat.getYearMonthStatMap();

            for (Map<Integer, MonthStat> monthStatMap : yearMonthStatMap.values()) {
                for (MonthStat monthStat : monthStatMap.values()) {
                    monthStat.setFlightTimeMore80HoursForMonth(checkFlightTimeMore80HoursForMonth(monthStat));
                    monthStat.setFlightTimeMore36HoursForSomeWeek(checkFlightTimeMore36HoursForSomeWeek(monthStat));
                    monthStat.setFlightTimeMore8HoursForSomeDay(checkFlightTimeMore8HoursForSomeDay(monthStat));
                }
            }
        }
    }

    private static final int EIGHTY_HOURS = 80;

    private boolean checkFlightTimeMore80HoursForMonth(MonthStat monthStat) {
        Duration flightTime = monthStat.getDayStatMap().values().stream()
                .map(DayStat::getFlightTime)
                .reduce(Duration.ZERO, Duration::plus);
        monthStat.setFlightTime(flightTime);
        return flightTime.toHours() > EIGHTY_HOURS;
    }

    private static final int THIRTY_SIX_HOURS = 36;
    private static final int COUNT_DAYS_IN_WEEK = 7;

    private boolean checkFlightTimeMore36HoursForSomeWeek(MonthStat monthStat) {
        Map<Integer, DayStat> dayStatMap = monthStat.getDayStatMap();


        return false;
        // todo method
        /*List<DayStat> dayStats = monthStat.getDayStatMap().values().stream()
                .sorted(Comparator.comparingInt(DayStat::getDay))
                .toList();

        Duration flightTime = dayStats.stream()
                .limit(COUNT_DAYS_IN_WEEK)
                .map(DayStat::getFlightTime)
                .reduce(Duration.ZERO, Duration::plus);

        for (int i = 0; i < dayStats.size() - COUNT_DAYS_IN_WEEK; i++) {
            flightTime.minus(dayStats.get())
        }*/

//        Duration flightTime = monthStat.getDayStatMap().values().stream()
//                .map(DayStat::getFlightTime)
//                .reduce(Duration.ZERO, Duration::plus);
//        monthStat.setFlightTime(flightTime);
//        return flightTime.toHours() > 80;
    }

    private static final int EIGHT_HOURS = 8;

    private boolean checkFlightTimeMore8HoursForSomeDay(MonthStat monthStat) {
        return monthStat.getDayStatMap().values().stream()
                .map(DayStat::getFlightTime)
                .anyMatch(flightTime -> flightTime.toHours() > EIGHT_HOURS);
    }
}
