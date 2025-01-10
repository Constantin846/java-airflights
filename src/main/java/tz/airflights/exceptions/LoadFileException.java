package tz.airflights.exceptions;

public class LoadFileException extends RuntimeException {
    public LoadFileException() {
        super("Error during file load!");
    }
}
