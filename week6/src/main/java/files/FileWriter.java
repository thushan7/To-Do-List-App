package files;

import java.nio.file.Path;
import java.nio.file.Files;
import java.io.IOException;
import java.util.ArrayList;

public class FileWriter {
	private Path filePath;
	private ArrayList<String> fileContents;

	public FileWriter(String filename) {
		filePath = Path.of(".", "saves", filename);
		fileContents = new ArrayList<>();
	}

	private String cleanString(String rawString) {
		return rawString.trim();
	}

	public void write(String text) {
		fileContents.add(text);
	}

	public void write(ArrayList<String> lines) {
		for (String line : lines) {
			write(line);
		}
	}

	public void save() throws IOException {
		Files.write(filePath, fileContents);
	}

	public String getFilePathString() {
		return filePath.toString();
	}
}
