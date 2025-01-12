package tz.airflights.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tz.airflights.exceptions.LoadFileException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AirFlightServiceImplTest {
    private AirFlightServiceImpl airFlightService;
    private String loadFilePath;
    private String statsFilePath;
    private Path tempFile;

    @BeforeEach
    void beforeEach() throws IOException {
        this.loadFilePath = "src\\test\\resources\\load-file.json";
        this.statsFilePath = "src\\test\\resources\\stats-file.json";
        this.tempFile = Files.createTempFile("test-stats-file", ".json");
        this.airFlightService = new AirFlightServiceImpl(loadFilePath, tempFile.toString());
    }

    @Test
    void start() {
        String expectedContent = loadFile(Path.of(statsFilePath));

        airFlightService.start();
        String actualContent = loadFile(tempFile);

        assertEquals(expectedContent, actualContent);
    }

    private String loadFile(Path path) {
        String content;
        try {
            content = Files.readString(path);
        } catch (IOException e) {
            throw new LoadFileException();
        }
        return content;
    }
}