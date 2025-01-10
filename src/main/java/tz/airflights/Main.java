package tz.airflights;

import tz.airflights.service.AirFlightService;
import tz.airflights.service.AirFlightServiceImpl;

public class Main {
    private static AirFlightService service;

    public static void main(String[] args) {
        //todo argument file path
        service = new AirFlightServiceImpl();
        service.run();
    }
}