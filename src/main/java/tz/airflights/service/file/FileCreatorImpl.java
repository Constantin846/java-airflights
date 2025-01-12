package tz.airflights.service.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tz.airflights.adapters.LocalDateTimeAdapter;
import tz.airflights.adapters.MonthStatAdaptor;
import tz.airflights.exceptions.SaveFileException;
import tz.airflights.models.stat.MonthStat;
import tz.airflights.models.stat.Stat;
import tz.airflights.models.stat.StatDto;
import tz.airflights.models.stat.StatDtoMapper;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

public class FileCreatorImpl implements FileCreator {
    private static final String DEFAULT_FILE_PATH = "src\\main\\resources\\stats-file.json";
    private final String filePath;
    private final Gson gson;
    private final StatDtoMapper mapper;

    public FileCreatorImpl() {
        this(DEFAULT_FILE_PATH);
    }


    public FileCreatorImpl(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
                .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
                .registerTypeAdapter(MonthStat.class, new MonthStatAdaptor())
                .create();
        this.mapper = new StatDtoMapper();
    }

    @Override
    public void createFile(List<Stat> stats) {
        List<StatDto> statDtoList = mapper.toStatDto(stats);
        String content = gson.toJson(statDtoList);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(this.filePath);
            fileWriter.write(content);
            fileWriter.close();

        } catch (IOException e) {
            throw new SaveFileException();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    throw new SaveFileException();
                }
            }
        }
    }
}
