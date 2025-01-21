package tz.airflights;

import tz.airflights.service.AirFlightService;
import tz.airflights.service.AirFlightServiceImpl;

import java.util.Objects;

public class Main {
    private static final String PARAM_NAME_LOAD_FILE_PATH = "load-file-path";
    private static final String PARAM_NAME_CREATE_FILE_PATH = "create-file-path";
    private static AirFlightService service;

    public static void main(String[] args) {
        String loadFilePath = null;
        String createFilePath = null;
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals(PARAM_NAME_LOAD_FILE_PATH)) {
                loadFilePath = args[i + 1];
            }
            if (args[i].equals(PARAM_NAME_CREATE_FILE_PATH)) {
                createFilePath = args[i + 1];
            }
        }

        if(Objects.nonNull(createFilePath)) {
            service = new AirFlightServiceImpl(loadFilePath, createFilePath);
        } else if (Objects.nonNull(loadFilePath)) {
            service = new AirFlightServiceImpl(loadFilePath);
        } else {
            service = new AirFlightServiceImpl();
        }
        service.start();
        System.out.println("Completed successfully");
    }
}