package tz.airflights.service.loader;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import tz.airflights.adapters.AirFlightAdapter;
import tz.airflights.adapters.CrewMemberAdapter;
import tz.airflights.exceptions.LoadFileException;
import tz.airflights.models.AirFlight;
import tz.airflights.models.CrewMember;
import tz.airflights.models.LoadedContent;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileLoaderImpl implements FileLoader {
    private static final String DEFAULT_FILE_PATH = "src\\main\\resources\\load-file.json"; //todo path
    private final String filePath;
    private final Gson gson;

    public FileLoaderImpl() {
        this(DEFAULT_FILE_PATH);
    }

    public FileLoaderImpl(String filePath) {
        //todo path from main
        this.filePath = filePath;
        this.gson = new GsonBuilder().serializeNulls().setPrettyPrinting()
                .registerTypeAdapter(CrewMember.class, new CrewMemberAdapter())
                .registerTypeAdapter(AirFlight.class, new AirFlightAdapter())
                .create();
    }

    @Override
    public void loadFile() {
        try {
            String content = Files.readString(Path.of(filePath));
            System.out.println(content); //todo delete

            LoadedContent loadedContent = gson.fromJson(content, LoadedContent.class);

            System.out.println(loadedContent); //todo delete

        } catch (IOException e) {
            throw new LoadFileException();
        }
    }
}