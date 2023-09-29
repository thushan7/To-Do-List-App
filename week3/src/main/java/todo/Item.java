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
    public void setName(String newName) {
        this.name = newName;
    }

    public void setDescription(String newDescription) {
        this.description = newDescription;
    }

    public void setDueDate(LocalDate newDueDate) {
        this.dueDate = newDueDate;
    }

    // Overridden toString method to print item information
    @Override
    public String toString() {
        return "Item Name: " + name + "\nDescription: " + description + "\nDue Date: " + dueDate;
    }
}
