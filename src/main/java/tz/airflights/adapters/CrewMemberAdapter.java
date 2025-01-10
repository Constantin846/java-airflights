package tz.airflights.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import tz.airflights.models.CrewMember;

import java.io.IOException;

public class CrewMemberAdapter extends TypeAdapter<CrewMember> {
    @Override
    public void write(JsonWriter jsonWriter, CrewMember crewMember) throws IOException {
        jsonWriter.beginObject()
                .name("id").value(crewMember.getId())
                .name("name").value(crewMember.getName())
                .name("surname").value(crewMember.getSurname())
                .endObject();
    }

    @Override
    public CrewMember read(JsonReader jsonReader) throws IOException {
        CrewMember crewMember = new CrewMember();
        jsonReader.beginObject();

        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "id":
                    crewMember.setId(Long.parseLong(jsonReader.nextString()));
                    break;
                case "name":
                    crewMember.setName(jsonReader.nextString());
                    break;
                case "surname":
                    crewMember.setSurname(jsonReader.nextString());
                    break;
                default:
                    jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        return crewMember;
    }
}
