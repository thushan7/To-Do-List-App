package ui;

import java.util.ArrayList;

public class Menu {

    private String title;
    private ArrayList<String> options;

    public Menu(String title) {
        this.title = title;
        options = new ArrayList<>();
    }

    public int getNumOptions() {
        return options.size();
    }

    public boolean hasOption(String targetOption) {
        for (String option : options) {
            if (option.equals(targetOption)) {
                return true;
            }
        }

        return false;
    }

    public void addOption(String newOption) throws Exception {
        addOption(newOption, getNumOptions() + 1);
    }

    public void addOption(String newOption, int position) throws Exception {
        if (hasOption(newOption)) {
            throw new Exception("The option '" + newOption + "' already exists");
        }

        int index = position - 1;
        if (index < 0 || index > getNumOptions()) {
            throw new Exception(position + " is out of bounds");
        }

        options.add(index, newOption);
    }

    public int textToOptionInt(String text) throws Exception {
        for (int i = 0; i < getNumOptions(); i++) {
            if (options.get(i).equals(text)) {
                return i + 1;
            }
        }

        throw new Exception(String.format("No option '%s'", text));
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format(" === %s === \n", title));

        for (int i = 0; i < getNumOptions(); i++) {
            sb.append(String.format("%d: %s\n", i + 1, options.get(i)));
        }

        return sb.toString();
    }

}
