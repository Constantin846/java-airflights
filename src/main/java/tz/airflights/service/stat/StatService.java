package tz.airflights.service.stat;

import tz.airflights.models.AirFlight;
import tz.airflights.models.CrewMember;
import tz.airflights.models.stat.Stat;

import java.util.List;

public interface StatService {
    List<Stat> formStat(List<CrewMember> crew, List<AirFlight> airFlights);
}
