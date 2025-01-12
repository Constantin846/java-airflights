package tz.airflights.service.file;

public class FilePathCreator {
    private static final String BACKSLASH = "\\\\";
    private static final String DELIMITER = "\\";
    private static final int ONE = 1;
    private static final String DEFAULT_FILE_NAME = "stats-file.json";

    public String createFilePath(String loadFilePath) {
        String[] strings = loadFilePath.split(BACKSLASH);
        strings[strings.length - ONE] = DEFAULT_FILE_NAME;
        return String.join(DELIMITER, strings);
    }
}
