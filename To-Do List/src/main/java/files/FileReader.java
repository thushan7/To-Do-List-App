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

/**
 * Read a file with line separated records
 * 
 * @see FileWriter
 */
public class FileReader {

    private Path filepath;
    private Scanner fileScanner;
    private int lineNumber;

    /**
     * Create the reader.
     * 
     * The file read from will be located in the 'saves' directory
     * @param filename The name of the file to read
     * @throws Exception Thrown if the file does not exist or is not a file
     */
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

    /**
     * Read a single line from the file.
     * @return The next line in the file
     * @throws FileFormatException If there are no more lines to read
     */
    public String readLine() throws FileFormatException {
        try {
            lineNumber++;
            return cleanString(fileScanner.nextLine());
        } catch (NoSuchElementException err) {
            throw new FileFormatException("End of file reached unexpectedly");
        }
    }

    /**
     * Read an integer from the file.
     * @return The integer read from the file
     * @throws FileFormatException If the next line is not an integer or if there are no more lines to read
     */
    public int readInt() throws FileFormatException {
        String intLine = readLine();

        try {
            return Integer.parseInt(intLine);
        }
        catch(NumberFormatException err) {
            throw new FileFormatException(filepath, lineNumber, intLine + " could not be parsed as an integer");
        }
    }

    /**
     * Read a boolean from the file
     * @return The boolean read from the file
     * @throws FileFormatException If the next line is not a boolean or if there are no more lines to read
     */
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

    /**
     * Read a DateTime from the file
     * @return The datetime read from the file
     * @throws FileFormatException If the next line is not a datetime string or if there are no more lines to read
     * 
     * @see LocalDateTime
     */
    public LocalDateTime readDateTime() throws FileFormatException {
        String dateTimeString = readLine();

        try {
            return LocalDateTime.parse(dateTimeString);
        } catch (DateTimeParseException err) {
            throw new FileFormatException(filepath, lineNumber, dateTimeString + " could not be parsed as a date time");
        }
    }

    /**
     * Get the relative filepath of this Reader
     * @return The relative filepath of the reader
     */
    public String getFilePath() {
        return filepath.toString();
    }

}
