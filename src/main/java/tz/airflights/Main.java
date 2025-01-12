package tz.airflights;

import tz.airflights.service.AirFlightService;
import tz.airflights.service.AirFlightServiceImpl;

public class Main {
    private static AirFlightService service;

    public static void main(String[] args) {
        String loadFilePath = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("load-file-path")) {
                loadFilePath = args[i + 1];
            }
        }

        if (loadFilePath == null) {
            service = new AirFlightServiceImpl();
        } else {
            service = new AirFlightServiceImpl(loadFilePath);
        }
        service.run();
    }
}