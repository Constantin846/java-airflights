package tz.airflights.adapters;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import tz.airflights.models.AirFlight;
import tz.airflights.models.CrewMember;

import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;

public class AirFlightAdapter extends TypeAdapter<AirFlight> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy | HH:mm");
    private final DateTimeFormatter dateTimeFormatter;
    private final Gson gson;

    public AirFlightAdapter() {
        this(DATE_TIME_FORMATTER);
    }

    public AirFlightAdapter(String dateTimeFormat) {
        this(DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    private AirFlightAdapter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
        this.gson = new GsonBuilder().serializeNulls().create();
    }

    @Override
    public void write(JsonWriter jsonWriter, AirFlight airFlight) throws IOException {
    }

    @Override
    public AirFlight read(JsonReader jsonReader) throws IOException {
        AirFlight airFlight = new AirFlight();
        jsonReader.beginObject();

        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "aircraftType":
                    airFlight.setAircraftType(jsonReader.nextString());
                    break;
                case "aircraftNumber":
                    airFlight.setAircraftNumber(Long.parseLong(jsonReader.nextString()));
                    break;
                case "startTime":
                    airFlight.setStartTime(LocalDateTime.parse(jsonReader.nextString(), dateTimeFormatter));
                    break;
                case "finishTime":
                    airFlight.setFinishTime(LocalDateTime.parse(jsonReader.nextString(), dateTimeFormatter));
                    break;
                case "startAirportName":
                    airFlight.setStartAirportName(jsonReader.nextString());
                    break;
                case "finishAirportName":
                    airFlight.setFinishAirportName(jsonReader.nextString());
                    break;
                case "crewSet":
                    Type listCrewMember = new TypeToken<List<CrewMember>>(){}.getType();
                    List<CrewMember> crewMembers = gson.fromJson(jsonReader.nextString(), listCrewMember);
                    airFlight.setCrewSet(new HashSet<>(crewMembers));
                    break;
                default:
                    jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        return airFlight;
    }
}
