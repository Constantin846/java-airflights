package tz.airflights.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import tz.airflights.models.AirFlight;
import tz.airflights.models.CrewMember;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class AirFlightAdapter extends TypeAdapter<AirFlight> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy | HH:mm");
    private final DateTimeFormatter dateTimeFormatter;
    private final CrewMemberAdapter crewMemberAdapter;

    public AirFlightAdapter() {
        this(DATE_TIME_FORMATTER);
    }

    public AirFlightAdapter(String dateTimeFormat) {
        this(DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    public AirFlightAdapter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
        this.crewMemberAdapter = new CrewMemberAdapter();
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
                case "crew":
                    Set<CrewMember> crew = new HashSet<>();
                    jsonReader.beginArray();

                    while (jsonReader.hasNext()) {
                        crew.add(crewMemberAdapter.read(jsonReader));
                    }
                    jsonReader.endArray();
                    airFlight.setCrew(crew);
                    //todo delete
//                    Type listCrewMember = new TypeToken<List<CrewMember>>(){}.getType();
//                    List<CrewMember> crewMembers = gson.fromJson(jsonReader.nextString(), listCrewMember);
//                    airFlight.setCrew(new HashSet<>(crewMembers));
                    break;
                default:
                    jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        return airFlight;
    }
}
