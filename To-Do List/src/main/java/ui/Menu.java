package ui;

import java.util.ArrayList;

public class Menu {

    private String title;
    private ArrayList<String> options;

    /**
     * Create an menu with a title
     * @param title The menu title
     */
    public Menu(String title) {
        this.title = title;
        options = new ArrayList<>();
    }

    /**
     * Get the number of options in the list
     * @return The number of options
     */
    public int getNumOptions() {
        return options.size();
    }

    /**
     * Check if an option exists
     * @param targetOption The text to match
     * @return true if the item exists, false otherwise
     */
    public boolean hasOption(String targetOption) {
        for (String option : options) {
            if (option.equals(targetOption)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Add an option to the menu.
     * 
     * The option is added to the end of the menu
     * @param newOption The option text to add
     * @throws Exception Thrown if the option already exists in the menu
     */
    public void addOption(String newOption) throws Exception {
        addOption(newOption, getNumOptions() + 1);
    }

    /**
     * Add an option to the menu.
     * 
     * The option is added to the position specified
     * @param newOption
     * @param position
     * @throws Exception
     */
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

    /**
     * Get the option number of an option
     * @param text The option text to search for
     * @return The position of the option
     * @throws Exception If the option does not exist in the menu
     */
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
