package tz.airflights.adapters;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd.MM.yy | HH:mm");
    private final DateTimeFormatter dateTimeFormatter;

    public LocalDateTimeAdapter() {
        this(DATE_TIME_FORMATTER);
    }

    public LocalDateTimeAdapter(String dateTimeFormat) {
        this(DateTimeFormatter.ofPattern(dateTimeFormat));
    }

    public LocalDateTimeAdapter(DateTimeFormatter dateTimeFormatter) {
        this.dateTimeFormatter = dateTimeFormatter;
    }
    @Override
    public void write(JsonWriter out, LocalDateTime value) throws IOException {
        out.value(value.format(dateTimeFormatter));
    }

    @Override
    public LocalDateTime read(JsonReader in) throws IOException {
        return LocalDateTime.parse(in.nextString(), dateTimeFormatter);
    }
}
