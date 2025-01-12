package tz.airflights.exceptions;

public class SaveFileException extends RuntimeException {
    public SaveFileException() {
        super("Error during file save!");
    }
}
