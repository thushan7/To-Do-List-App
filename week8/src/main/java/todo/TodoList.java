//javadoc -d docs --source-path src/main/java todo files ui
//CREATING JAVADOCS

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

/**
* Adds an Item to the List
*
* Takes in an item and appends the item to the List
*
* @param newItem the item to be added to the List
*
* @see Item
*/
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

/**
* Get the position of an item by title
*
* Checks the list for a matching title. If found it returns the index. If not {@Link ItemNotFoundException} is thrown.
*
* @param targetTitle the title to look for
* @return the index of the item if found
*
* @throws NoElementsException if the list is empty
* @throws ItemNotFoundException if an item with targetTitle does not exist in the list
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

    public void completeItem(int index) throws IndexOutOfBoundsException, NoElementsException {

        validateIndex(index);

        Item item = items.get(index);
        item.setCompleted(true);

    }

    public Item removeItem(int index) throws IndexOutOfBoundsException, NoElementsException {

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
