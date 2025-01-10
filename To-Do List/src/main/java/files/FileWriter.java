package files;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;

/**
 * Create and write to files on the user's systems in line separated records format
 */
public class FileWriter {

    private Path filepath;
    /**
     * The buffer for the file contents
     */
    protected ArrayList<String> contents;

    /**
     * Create a new writer for saving line separated records to a file.
     * 
     * The file written will be saved in the 'saves' directory from where the program is run from.
     * @param filename The name of the file to write to
     */
    public FileWriter(String filename) {
        filepath = Path.of("./saves", filename);
        contents = new ArrayList<>();
    }

    /**
     * 
     * Write an ArrayList to a file.
     * 
     * Writes the contents of the arraylist to the contents buffer as individual lines
     * @param lines The arraylist to write to the file
     */
	@Deprecated
    public void writeLines(ArrayList<String> lines) {
        contents.addAll(lines);
    }

	public  void write(Saveable saveObj) {
		contents.add(saveObj.toSaveString());
	}

    /**
     * Write a line of text.
     * 
     * Writes a line of text to the contents buffer.
     * @param text The line to write
     */
	@Deprecated
    public void write(String text) {
        contents.add(text);
    }

    /**
     * Write the contents to the user's file system
     * 
     * Outputs the contents buffer to the file
     * @throws IOException If an IOException occurs
     */
    public void save() throws IOException {
        Files.write(filepath, contents);
    }

    /**
     * Get the relative filepath of this writer
     * @return The relative filepath of this writer
     */
    public String getFilePath() {
        return filepath.toString();
    }

}
