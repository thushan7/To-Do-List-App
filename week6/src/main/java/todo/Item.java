package todo;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class Item implements Comparable<Item> {

    private String title;
    private String description;
    private LocalDateTime dueDateTime;
    private int priority;
    private boolean completed;

    public Item(String title, String description, LocalDateTime dueDateTime, int priority, boolean completed) {
        this.title = title;
        this.description = description;
        this.dueDateTime = dueDateTime;
        this.priority = priority;
        this.completed = completed;
    }

    public Item() {
        this("[no title]", "[no description]", LocalDateTime.now(), 0, false);
    }

    // Getters and Setters
    public String getTitle() {
        if (title == null) {
            return "[no title]";
        }
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        if (description == null) {
            return "no description provided";
        }
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDueDateTime() {
        return dueDateTime;
    }

    public void setDueDateTime(LocalDateTime dueDateTime) {
        this.dueDateTime = dueDateTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public String getCompletedString() {
        if(completed) {
            return "DONE";
        }
        return "INCOMPLETE";
    }

    public ArrayList<String> getSave() {
        ArrayList<String> saveState = new ArrayList<>();
        saveState.add(title);
        saveState.add(description);
        saveState.add(dueDateTime.toString());
        saveState.add(Integer.toString(priority));
        saveState.add(Boolean.toString(completed));

        return saveState;
    }

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

}
