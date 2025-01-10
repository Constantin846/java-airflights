package tz.airflights.service;

import tz.airflights.service.loader.FileLoader;
import tz.airflights.service.loader.FileLoaderImpl;

public class AirFlightServiceImpl implements AirFlightService {
    private final FileLoader fileLoader;

    public AirFlightServiceImpl() {
        this.fileLoader = new FileLoaderImpl();
    }

    @Override
    public void run() {
        loadFile();

    }

    private void loadFile() {
        fileLoader.loadFile();
    }
}
