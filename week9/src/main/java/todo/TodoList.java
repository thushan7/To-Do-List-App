package todo;

import files.Saveable;

import java.time.LocalDateTime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class TodoList implements Iterable<Item>, Saveable {

    private String title;
    private ArrayList<Item> items;

    public TodoList(String title) {
        this.title = title;
        this.items = new ArrayList<>();
    }

    /**
     * Add an item to the list.
     * 
     * Appends the item to the list.
     * @param newItem The item to add to the list
     * 
     * @see Item
     */
    public void addItem(Item newItem) {
        items.add(newItem);
    }

    /**
     * Add an item to the list based on the data provided.
     * 
     * Appends the item to the list. The item will always be not completed
     * @param title The title of the item
     * @param desc The description of the item
     * @param dueDateTime The due date and time of the item
     * @param priority The priority of the item
     */
    public void addItem(String title, String desc, LocalDateTime dueDateTime, int priority) {
        Item newItem = new Item(title, desc, dueDateTime, priority, false);
        addItem(newItem);
    }

    /**
     * Get the number of items in the list.
     * @return The number of items in the list
     */
    public int getNumItems() {
        return items.size();
    }

    /**
     * Search for an item in the list by title.
     * 
     * Get the index of an item with a matching title.
     * The list is linearly searched until an exact title match is found.
     * @param targetTitle The title to search for
     * @return The first index of an item with a matching title
     * @throws NoElementsException If the list is currently empty
     * @throws ItemNotFoundException If no item in the list matches the title
     */
    public int getIndexByTitle(String targetTitle) throws NoElementsException, ItemNotFoundException {

        if(getNumItems() <= 0) {
            throw new NoElementsException("Can not find an item title in an empty list");
        }

        for (int i = 0; i < getNumItems(); i++) {
            if (items.get(i).getTitle().equals(targetTitle)) {
                return i;
            }
        }

        throw new ItemNotFoundException("title", targetTitle);
    }

    private void validateIndex(int index) throws IndexOutOfBoundsException, NoElementsException {
        if (getNumItems() == 0) {
            throw new NoElementsException();
        }

        if (index < 0 || index >= getNumItems()) {
            throw new IndexOutOfBoundsException(index);
        }
    }

    /**
     * Mark an item as complete.
     * 
     * Sets the completed state of the item at the specified to true
     * @param index The position of the item to complete
     * @throws IndexOutOfBoundsException If the index is less than 0 or larger than or equal to the list size
     * @throws NoElementsException If the list is currently empty
     */
    public void completeItem(int index) throws IndexOutOfBoundsException, NoElementsException {

        validateIndex(index);

        Item item = items.get(index);
        item.setCompleted(true);

    }

    /**
     * Remove an item from the list
     * @param index The position of the item to remove
     * @return The item removed from the list
     * @throws IndexOutOfBoundsException If the index is less than 0 or larger than or equal to the list size
     * @throws NoElementsException If the list is currently empty
     */
    public Item removeItem(int index) throws IndexOutOfBoundsException, NoElementsException {

        validateIndex(index);

        return items.remove(index);

    }

    /**
     * Get a sorted version of the current list.
     * @return The sorted list
     * 
     * @see Comparable
     * @see Item#compareTo(Item)
     */
    public ArrayList<Item> sorted() {
        ArrayList<Item> itemsCopy = new ArrayList<>(items);
        Collections.sort(itemsCopy);
        return itemsCopy;
    }

    /**
     * Get the title of the list
     * @return The list title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Set a new title for the list
     * @param title The new title for the list
     */
    public void setTitle(String title) {
        this.title = title;
    }

    public Iterator<Item> iterator() {
        return items.iterator();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(String.format(" === %s === \n\n", title));
        for(Item item : sorted()) {
            sb.append(item.toString());
            sb.append("\n");
        }

        return sb.toString();
    }

    public String toSaveString() {
        return title + "\n" + getNumItems();
    }
	public String toCsvSaveString() {
		ArrayList<String> lines = new ArrayList<>();
		for (Item item : this) {
			lines.add(item.toCsvSaveString());
		}
		return String.join("\n", lines);
	}
}
