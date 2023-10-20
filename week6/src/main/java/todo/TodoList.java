package todo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

public class TodoList implements Iterable<Item> {

    private String title;
    private ArrayList<Item> items;

    public TodoList(String title) {
        this.title = title;
        this.items = new ArrayList<>();
    }

    public void addItem(Item newItem) {
        items.add(newItem);
    }

    public void addItem(String title, String desc, LocalDateTime dueDateTime, int priority) {
        Item newItem = new Item(title, desc, dueDateTime, priority, false);
        addItem(newItem);
    }

    public int getNumItems() {
        return items.size();
    }

    public int getIndexByTitle(String targetTitle) throws Exception {
        for (int i = 0; i < getNumItems(); i++) {
            if (items.get(i).getTitle().equals(targetTitle)) {
                return i;
            }
        }

        throw new Exception("Item with title '" + targetTitle + "' does not exist");
    }

    private void validateIndex(int index) throws Exception {
        if (getNumItems() == 0) {
            throw new Exception("The list is empty");
        }

        if (index < 0 || index >= getNumItems()) {
            throw new Exception(String.format("Index '%d' is out of bounds for range 0-%d", index, getNumItems() - 1));
        }
    }

    public void completeItem(int index) throws Exception {

        validateIndex(index);

        Item item = items.get(index);
        item.setCompleted(true);

    }

    public Item removeItem(int index) throws Exception {

        validateIndex(index);

        return items.remove(index);

    }

    public ArrayList<Item> sorted() {
        ArrayList<Item> itemsCopy = new ArrayList<>(items);
        Collections.sort(itemsCopy);
        return itemsCopy;
    }

    public String getTitle() {
        return title;
    }

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

    public String getSave() {
        return title;
    }
}
