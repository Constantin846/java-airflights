package tz.airflights.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import tz.airflights.models.stat.MonthStat;

import java.io.IOException;
import java.time.Duration;

public class MonthStatAdaptor extends TypeAdapter<MonthStat> {
    @Override
    public void write(JsonWriter jsonWriter, MonthStat monthStat) throws IOException {
        jsonWriter.beginObject()
                .name("month").value(monthStat.getMonth())
                .name("year").value(monthStat.getYear())
                .name("flightTimeInHour").value(monthStat.getFlightTime().toHours())
                .name("isFlightTimeMore80HoursForMonth").value(monthStat.isFlightTimeMore80HoursForMonth())
                .name("isFlightTimeMore36HoursForSomeWeek").value(monthStat.isFlightTimeMore36HoursForSomeWeek())
                .name("isFlightTimeMore8HoursForSomeDay").value(monthStat.isFlightTimeMore8HoursForSomeDay())
                .endObject();
    }

    @Override
    public MonthStat read(JsonReader jsonReader) throws IOException {
        MonthStat monthStat = new MonthStat();
        jsonReader.beginObject();

        while (jsonReader.hasNext()) {
            switch (jsonReader.nextName()) {
                case "month":
                    monthStat.setMonth(jsonReader.nextString());
                    break;
                case "year":
                    monthStat.setYear(jsonReader.nextInt());
                    break;
                case "flightTimeInHour":
                    monthStat.setFlightTime(Duration.ofHours(jsonReader.nextLong()));
                    break;
                case "isFlightTimeMore80HoursForMonth":
                    monthStat.setFlightTimeMore80HoursForMonth(jsonReader.nextBoolean());
                    break;
                case "isFlightTimeMore36HoursForSomeWeek":
                    monthStat.setFlightTimeMore36HoursForSomeWeek(jsonReader.nextBoolean());
                    break;
                case "isFlightTimeMore8HoursForSomeDay":
                    monthStat.setFlightTimeMore8HoursForSomeDay(jsonReader.nextBoolean());
                    break;
                default:
                    jsonReader.nextString();
            }
        }
        jsonReader.endObject();
        return monthStat;
    }
}
