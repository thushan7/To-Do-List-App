package files;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.NoSuchElementException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class FileReader {

    private Path filepath;
    private Scanner fileScanner;
    private int lineNumber;

    public FileReader(String filename) throws Exception {
        filepath = Path.of("./saves", filename);

        if(Files.notExists(filepath)) {
            throw new Exception("Could not open file '" + filepath + "'");
        }

        lineNumber = 0;
        fileScanner = new Scanner(Files.newInputStream(filepath));
    }

    public List<String> getFileContents() throws IOException {
        return Files.readAllLines(filepath);
    }

    private String cleanString(String rawString) {
        return rawString.trim();
    }

    public String readLine() throws FileFormatException {
        try {
            lineNumber++;
            return cleanString(fileScanner.nextLine());
        } catch (NoSuchElementException err) {
            throw new FileFormatException("End of file reached unexpectedly");
        }
    }

    public int readInt() throws FileFormatException {
        String intLine = readLine();

        try {
            return Integer.parseInt(intLine);
        }
        catch(NumberFormatException err) {
            throw new FileFormatException(filepath, lineNumber, intLine + " could not be parsed as an integer");
        }
    }

    public boolean readBoolean() throws FileFormatException {
        String boolLine = readLine();

        if(boolLine.equalsIgnoreCase("false")) {
            return false;
        }
        if(boolLine.equalsIgnoreCase("true")) {
            return true;
        }

        throw new FileFormatException(filepath, lineNumber, boolLine + " could not be parsed as a boolean");
    }

    public LocalDateTime readDateTime() throws FileFormatException {
        String dateTimeString = readLine();

        try {
            return LocalDateTime.parse(dateTimeString);
        } catch (DateTimeParseException err) {
            throw new FileFormatException(filepath, lineNumber, dateTimeString + " could not be parsed as a date time");
        }
    }

    public String getFilePath() {
        return filepath.toString();
    }

}
