package files;

import java.util.ArrayList;

public class CsvFileWriter extends FileWriter {
    
    public CsvFileWriter(String filename, ArrayList<String> headers) {
        super(filename);

        contents.add(String.join(",", headers));
    }

    @Override
    public void write(Saveable saveObj) {
        contents.add(saveObj.toCsvSaveString());
    }
}
