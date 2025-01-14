package tz.airflights.models;

import java.util.List;

public class LoadedContent {
    private List<CrewMember> crew;
    private List<AirFlight> airFlights;

    public List<CrewMember> getCrew() {
        return crew;
    }

    public void setCrew(List<CrewMember> crew) {
        this.crew = crew;
    }

    public List<AirFlight> getAirFlights() {
        return airFlights;
    }

    public void setAirFlights(List<AirFlight> airFlights) {
        this.airFlights = airFlights;
    }
}
