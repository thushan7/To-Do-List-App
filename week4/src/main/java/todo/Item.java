package todo;

import java.time.LocalDate; // Import the LocalDate class for handling due dates

public class Item {
    private String name;
    private String description;
    private LocalDate dueDate;

    // Constructor
    public Item(String shortName, String desc, LocalDate dateDue) {
        this.name = shortName;
        this.description = desc;
        this.dueDate = dateDue;
    }

    public Item(){
        this("a new todo","add your description here",LocalDate.now());
    }

    // Accessors (getters)
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    // Mutators (setters)
    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    // Overridden toString method to print item information
    @Override
    public String toString() {
        return "Item Name: " + name + "\nDescription: " + description + "\nDue Date: " + dueDate;
    }
}
