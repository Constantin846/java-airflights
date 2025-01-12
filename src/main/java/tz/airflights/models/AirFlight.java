package tz.airflights.models;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class AirFlight {
    private String aircraftType;
    private Long aircraftNumber;
    private LocalDateTime startTime;
    private LocalDateTime finishTime;
    private String startAirportName;
    private String finishAirportName;
    private Set<CrewMember> crew;

    public AirFlight() {
        this.crew = new HashSet<>();
    }

    public String getAircraftType() {
        return aircraftType;
    }

    public void setAircraftType(String aircraftType) {
        this.aircraftType = aircraftType;
    }

    public Long getAircraftNumber() {
        return aircraftNumber;
    }

    public void setAircraftNumber(Long aircraftNumber) {
        this.aircraftNumber = aircraftNumber;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(LocalDateTime finishTime) {
        this.finishTime = finishTime;
    }

    public String getStartAirportName() {
        return startAirportName;
    }

    public void setStartAirportName(String startAirportName) {
        this.startAirportName = startAirportName;
    }

    public String getFinishAirportName() {
        return finishAirportName;
    }

    public void setFinishAirportName(String finishAirportName) {
        this.finishAirportName = finishAirportName;
    }

    public Set<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(Set<CrewMember> crewSet) {
        this.crew = crewSet;
    }
}
