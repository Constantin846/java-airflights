package tz.airflights.service;

import tz.airflights.models.AirFlight;
import tz.airflights.models.CrewMember;
import tz.airflights.models.LoadedContent;
import tz.airflights.models.stat.Stat;
import tz.airflights.service.file.FileCreator;
import tz.airflights.service.file.FileCreatorImpl;
import tz.airflights.service.file.FileLoader;
import tz.airflights.service.file.FileLoaderImpl;
import tz.airflights.service.stat.StatService;
import tz.airflights.service.stat.StatServiceImpl;

import java.util.List;

public class AirFlightServiceImpl implements AirFlightService {
    private final FileLoader fileLoader;
    private final StatService statService;
    private final FileCreator fileCreator;
    private List<CrewMember> crew;
    private List<AirFlight> airFlights;
    private List<Stat> stats;

    public AirFlightServiceImpl() {
        this.fileLoader = new FileLoaderImpl();
        this.statService = new StatServiceImpl();
        this.fileCreator = new FileCreatorImpl();
    }

    public AirFlightServiceImpl(String loadFilePath) {
        this.fileLoader = new FileLoaderImpl(loadFilePath);
        this.statService = new StatServiceImpl();
        this.fileCreator = new FileCreatorImpl(); // todo create path
    }

    @Override
    public void run() {
        loadFile();
        formStat();
        createFile();
    }

    private void loadFile() {
        LoadedContent loadedContent = fileLoader.loadFile();
        this.crew = loadedContent.getCrew();
        this.airFlights = loadedContent.getAirFlights();
    }

    private void formStat() {
        stats = statService.formStat(crew, airFlights);
    }

    private void createFile() {
        fileCreator.createFile(stats);
    }
}
