package files;

import java.nio.file.Path;

public class FileFormatException extends Exception {

    public FileFormatException() {
        super("The file is incorrectly formatted");
    }

    public FileFormatException(String message) {
        super(message);
    }

    public FileFormatException(Path filePath, int lineNumber, String errorMessage) {
        super(filePath.toAbsolutePath().toString() + ":Line " + Integer.toString(lineNumber) + " " + errorMessage);
    }

}