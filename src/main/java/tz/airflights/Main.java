package tz.airflights;

import tz.airflights.service.AirFlightService;
import tz.airflights.service.AirFlightServiceImpl;
// load-file-path C:\Users\Константин\Desktop\Новая-папка\load-file1.json todo delete

public class Main {
    private static final String PARAM_NAME_LOAD_FILE_PATH = "load-file-path";
    private static AirFlightService service;

    public static void main(String[] args) {
        String loadFilePath = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(PARAM_NAME_LOAD_FILE_PATH)) {
                loadFilePath = args[i + 1];
            }
        }

        if (loadFilePath == null) {
            service = new AirFlightServiceImpl();
        } else {
            service = new AirFlightServiceImpl(loadFilePath);
        }
        service.start();
    }
}