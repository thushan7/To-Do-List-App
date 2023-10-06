package todo;

import java.util.ArrayList;
import java.util.Collections;
import java.time.LocalDate;

public class TodoList {
    private ArrayList<Item> itemList;

    // Constructor to initialize the ArrayList
    public TodoList() {
        itemList = new ArrayList<>();
    }

    // Method to add an item to the todo list
    public void addItem(Item item) {
        itemList.add(item);
    }

    // Method to remove an item from the todo list
    public void removeItem(Item item) {
        itemList.remove(item);
    }

    // should this method be public?
    public ArrayList<Item> getItems() {
        return itemList;
    }

    // Method to check if the todo list is empty
    public boolean isEmpty() {
        return itemList.isEmpty();
    }

    // Override the toString method to represent items in a nicely formatted string
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Todo List:\n");
        for (Item item : itemList) {
            sb.append(item.toString()).append("\n");
        }
        return sb.toString();
    }

    // Method to get the position of an item by name, throws an exception if not
    // found
    public int getItemPositionByName(String itemName) throws Exception {
        for (int i = 0; i < itemList.size(); i++) {
            if (itemList.get(i).getName().equals(itemName)) {
                return i;
            }
        }
        throw new Exception("Item '" + itemName + "' not found in the todo list.");
    }

    public void moveItemUp(String itemName) {
        int currentIndex;

        try {
            currentIndex = getItemPositionByName(itemName);
        } catch(Exception err) {
            // Item not found! nothing to move
            System.out.println(err.getMessage());
            return;
        }

        if (currentIndex == 0) {
            // Item already at the top, nothing to move.
            return;
        }

        // Swap the item with the previous item
        Collections.swap(itemList, currentIndex, currentIndex - 1);
    }

    public void moveItemToPosition(int oldPosition, int newPosition) throws Exception {

        if (newPosition < 0 || newPosition >= itemList.size()) {
            throw new Exception("New position out of bounds");
        }
        if (oldPosition < 0 || oldPosition > itemList.size()) {
            throw new Exception("Old position out of bounds");
        }

        // Move the item to the specified position by removing it and then adding it at
        // the new position
        Item itemToMove = itemList.remove(oldPosition);
        itemList.add(newPosition, itemToMove);
    }

    // Method to get the string version of an item from a specific index
    public String getItemStringAtIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= itemList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Item item = itemList.get(index);
        return item.toString();
    }

    // Method to remove an item by index and return the removed item
    public Item removeItemByIndex(int index) throws IndexOutOfBoundsException {
        if (index < 0 || index >= itemList.size()) {
            throw new IndexOutOfBoundsException("Invalid index: " + index);
        }

        Item removedItem = itemList.remove(index);
        return removedItem;
    }

    // Method to add an item with a name, date, and generic description
    public void addItem(String name, LocalDate dueDate) {
        String description = "Generic description"; // Generic short phrase for the description
        Item newItem = new Item(name, description, dueDate);
        itemList.add(newItem);
    }

    // Overloaded method to insert an item at a specified position
    public void addItem(String name, LocalDate dueDate, int position) throws IndexOutOfBoundsException {
        if (position < 0 || position > itemList.size()) {
            throw new IndexOutOfBoundsException("Invalid position: " + position);
        }

        String description = "Generic description"; // Generic short phrase for the description
        Item newItem = new Item(name, description, dueDate);

        if (position == itemList.size()) {
            itemList.add(newItem); // If position is at the end, simply add the item
        } else {
            itemList.add(position, newItem); // Otherwise, insert the item at the specified position
        }
    }

}
