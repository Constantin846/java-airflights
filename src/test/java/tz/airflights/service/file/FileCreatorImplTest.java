package tz.airflights.service.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tz.airflights.adapters.LocalDateTimeAdapter;
import tz.airflights.adapters.MonthStatAdaptor;
import tz.airflights.exceptions.LoadFileException;
import tz.airflights.models.CrewMember;
import tz.airflights.models.stat.MonthStat;
import tz.airflights.models.stat.Stat;
import tz.airflights.models.stat.StatDtoMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileCreatorImplTest {
    private FileCreatorImpl fileCreator;
    private Path tempFile;
    private Gson gson;
    private StatDtoMapper mapper;

    @BeforeEach
    void beforeEach() throws IOException {
        this.tempFile = Files.createTempFile("test-stats-file", ".json");
        this.fileCreator = new FileCreatorImpl(tempFile.toString());
        this.gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(MonthStat.class, new MonthStatAdaptor())
                .create();
        this.mapper = new StatDtoMapper();
    }

    @Test
    void createFile() {
        List<Stat> stats = createStats();
        String expected = gson.toJson(mapper.toStatDto(stats));

        fileCreator.createFile(stats);

        String actual = loadFile();
        assertEquals(expected, actual);
    }

    private String loadFile() {
        String content;
        try {
            content = Files.readString(tempFile);
        } catch (IOException e) {
            throw new LoadFileException();
        }
        return content;
    }

    private List<Stat> createStats() {
        Stat firstStat = new Stat();
        CrewMember firstMember = new CrewMember();
        firstMember.setId(1);
        firstMember.setName("Владимир");
        firstMember.setSurname("Владимиров");
        firstStat.setCrewMember(firstMember);
        MonthStat firstMonthStat = new MonthStat();
        firstMonthStat.setMonth("Март");
        firstMonthStat.setYear(2024);
        firstMonthStat.setFlightTime(Duration.ofHours(42));
        firstStat.setYearMonthStatMap(Map.of(YearMonth.of(2024, 3), firstMonthStat));

        Stat secondStat = new Stat();
        CrewMember secondMember = new CrewMember();
        secondMember.setId(2);
        secondMember.setName("Владимир");
        secondMember.setSurname("Владимиров");
        secondStat.setCrewMember(secondMember);
        MonthStat secondMonthStat = new MonthStat();
        secondMonthStat.setMonth("Март");
        secondMonthStat.setYear(2024);
        secondMonthStat.setFlightTime(Duration.ofHours(42));
        secondStat.setYearMonthStatMap(Map.of(YearMonth.of(2024, 3), secondMonthStat));

        return List.of(firstStat, secondStat);
    }
}