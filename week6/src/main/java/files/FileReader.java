package files;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.Scanner;
import java.util.NoSuchElementException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

public class FileReader {
	private Path filePath;
	private Scanner fileScanner;

	public fileReader(String filename) throws IOException {
		filePath = Path.of(".", "saves", filename);
		if (Files.isDirectory(filePath)) {
			throw new IOException(getFilePathString() + "is a directory");
		}
		fileScanner = new Scanner(Files.newInputStream(filePath));
	}

	private String cleanString(String rawString) {
		return rawString.trim();
	}

	public String readLine() throws NoSuchElementException {
		return cleanString(fileScanner.nextLine());
	}

	public int readInt() throws NoSuchElementException, Exception {
		String intString = readLine();
		try {
			return Integer.parseInt(intString);
		} catch (NumberFormatException err) {
			throw new Exception("Expected integer");
		}
	}

	public LocalDateTime readDateTime() throws NoSuchElementException, Exception {
		String datetimeString = readLine();
		try {
			return LocalDateTime.parse(datetimeString);
		} catch (DateTimeParseException err) {
			throw new Exception("Expected date time");
		}
	}

	public boolean readBoolean() throws NoSuchElementException {
		String booleanString = readLine();
		return Boolean.parseBoolean(booleanString);
	}

	public String getFilePathString() {
		return filePath.toString();
	}
}
