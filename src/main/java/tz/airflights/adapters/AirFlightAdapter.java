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
//        throw new AdapterException("AirFlight class must not be output data.\n" +
//                "Write method of AirFlightAdapter class is not defined");
        // todo delete;


        jsonWriter.beginObject()
                .name("aircraftType").value(airFlight.getAircraftType())
                .name("aircraftNumber").value(airFlight.getAircraftNumber())
                .name("startTime").value(airFlight.getStartTime().format(dateTimeFormatter))
                .name("finishTime").value(airFlight.getFinishTime().format(dateTimeFormatter))
                .name("startAirportName").value(airFlight.getStartAirportName())
                .name("finishAirportName").value(airFlight.getFinishAirportName())
                .beginArray()
                .endArray()
                .endObject();
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
                    break;
                default:
                    jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        return airFlight;
    }
}
