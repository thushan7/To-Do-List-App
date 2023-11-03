package files;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Path;

import java.util.ArrayList;

public class FileWriter {

    private Path filepath;
    private ArrayList<String> contents;

    public FileWriter(String filename) {
        filepath = Path.of("./saves", filename);
        contents = new ArrayList<>();
    }

    public void writeLines(ArrayList<String> lines) {
        contents.addAll(lines);
    }

    public void write(String text) {
        contents.add(text);
    }

    public void save() throws IOException {
        Files.write(filepath, contents);
    }

    public String getFilePath() {
        return filepath.toString();
    }

}
