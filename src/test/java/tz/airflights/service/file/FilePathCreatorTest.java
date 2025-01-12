package tz.airflights.service.file;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FilePathCreatorTest {
    private FilePathCreator filePathCreator;

    @BeforeEach
    void beforeEach() {
        filePathCreator = new FilePathCreator();
    }

    @Test
    void createFilePath() {
        String loadFilePath = "src\\main\\resources\\load-file.json";
        String expectedPath = "src\\main\\resources\\stats-file.json";

        String actualPath = filePathCreator.createFilePath(loadFilePath);

        assertEquals(expectedPath, actualPath);
    }
}