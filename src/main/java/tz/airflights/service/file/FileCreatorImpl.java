package tz.airflights.service.file;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tz.airflights.adapters.CrewMemberAdapter;
import tz.airflights.adapters.MonthStatAdaptor;
import tz.airflights.exceptions.SaveFileException;
import tz.airflights.models.CrewMember;
import tz.airflights.models.stat.MonthStat;
import tz.airflights.models.stat.Stat;
import tz.airflights.models.stat.StatDto;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class FileCreatorImpl implements FileCreator {
    private static final String DEFAULT_FILE_PATH = "src\\main\\resources\\stats-file.json"; //todo path
    private final String filePath;
    private final Gson gson;

    public FileCreatorImpl() {
        this(DEFAULT_FILE_PATH);
    }


    public FileCreatorImpl(String filePath) {
        this.filePath = filePath;
        this.gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
                .registerTypeAdapter(CrewMember.class, new CrewMemberAdapter())
                .registerTypeAdapter(MonthStat.class, new MonthStatAdaptor())
                .create();
    }

    @Override
    public void createFile(List<Stat> stats) {
        List<StatDto> statDtoList = mapToStatDtoList(stats);
        String content = gson.toJson(statDtoList);
        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter(this.filePath);
            fileWriter.write(content);
            fileWriter.close();

        } catch (IOException e) {
            System.out.println("Error during file save!"); // todo delete
            throw new SaveFileException();

        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    System.out.println("Error during file save!"); // todo delete
                    throw new SaveFileException();
                }
            }
        }
    }

    private List<StatDto> mapToStatDtoList(List<Stat> stats) {
        return stats.stream()
                .map(stat -> {
                    StatDto statDto = new StatDto();
                    statDto.setMember(stat.getMember());

                    Set<MonthStat> monthStats = stat.getYearMonthStatMap().values().stream()
                            .flatMap(integerMonthStatMap -> integerMonthStatMap.values().stream())
                            .collect(Collectors.toSet());
                    statDto.setMonthStats(monthStats);
                    return statDto;
                }).toList();
    }
}
