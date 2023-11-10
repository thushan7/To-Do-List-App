package todo;

import files.Saveable;

import java.time.LocalDateTime;

import java.util.ArrayList;

public class Item implements Comparable<Item>, Saveable {

    private String title;
    private String description;
    private LocalDateTime dueDateTime;
    private int priority;
    private boolean completed;

    /**
     * Create an Item
     * @param title The title of the item
     * @param description The description of the item
     * @param dueDateTime The datetime the item is due
     * @param priority The priority of the item
     * @param completed If the item is completed or not
     */
    public Item(String title, String description, LocalDateTime dueDateTime, int priority, boolean completed) {
        this.title = title;
        this.description = description;
        this.dueDateTime = dueDateTime;
        this.priority = priority;
        this.completed = completed;
    }

    /**
     * Create an Item with default properties
     */
    public Item() {
        this("[no title]", "[no description]", LocalDateTime.now(), 0, false);
    }

    /**
     * Get the title of the item
     * @return The item title
     */
    public String getTitle() {
        if (title == null) {
            return "[no title]";
        }
        return title;
    }

    /**
     * Set a new tile for the item
     * @param title The new title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Get the description of the item
     * @return The item description
     */
    public String getDescription() {
        if (description == null) {
            return "no description provided";
        }
        return description;
    }

    /**
     * Set a new description of the item
     * @param description The new description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Get the due datetime of the item
     * @return The item due datetime
     */
    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    /**
     * Set a new due datetime of the item
     * @param dueDateTime The new due datetime
     */
    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    /**
     * Get the priority of the item
     * @return The item priority
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Set a new priority for the item
     * @param priority The new priority
     */
    public void setPriority(int priority) {
        this.priority = priority;
    }

    /**
     * Check if the item is completed
     * @return true if the item is completed
     */
    public boolean isCompleted() {
        return completed;
    }

    /**
     * Set the completed status of the item
     * @param completed The new completed status
     */
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    /**
     * Get the completed status as a displayable string
     * @return A String with the completed status of the item
     */
    public String getCompletedString() {
        if(completed) {
            return "DONE";
        }
        return "INCOMPLETE";
    }

    /**
     * Compare an item to this item.
     * 
     * @param other The item to compare to
     * @return Less than 0 if the other item comes after, greater than 0 if the other item comes before, 0 if they are equal
     */
    @Override
    public int compareTo(Item other) {
        if (this.getPriority() != other.getPriority()) {
            return this.getPriority() - other.getPriority();
        }

        if (!this.getDueDateTime().equals(other.getDueDateTime())) {
            return this.getDueDateTime().compareTo(other.getDueDateTime());
        }

        return this.getTitle().compareTo(other.getTitle());
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format(" --- %s (%s) --- \n", title, getCompletedString()));
        sb.append(String.format("Due: %s %s\n", dueDateTime.toLocalDate().toString(), dueDateTime.toLocalTime().toString()));
        sb.append(String.format("Priority: %d\n", priority));
        sb.append(description + "\n");
        return sb.toString();
    }

    private ArrayList<String> getSave() {
        ArrayList<String> saveState = new ArrayList<>();
        saveState.add(title);
        saveState.add(description);
        saveState.add(dueDateTime.toString());
        saveState.add(Integer.toString(priority));
        saveState.add(Boolean.toString(completed));

        return saveState;
    }

	public String toSaveString() {
		return String.join("\n", getSave());
	}
	public String toCsvSaveString() {
		return String.join(",", getSave());
	}

}
