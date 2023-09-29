package todo;

import java.util.ArrayList;
import java.time.LocalDate;

public class TodoList {
    private ArrayList<Item> listContents;

    public TodoList() {
        listContents = new ArrayList();
    }

    public void addItem(Item newItem) {
        listContents.add(newItem);
    }

    public void addItem(String name, String desc, LocalDate dueDate) {
        Item newItem = new Item(name, desc, dueDate);
        addItem(newItem);
    }

    public void removeItem(Item targetItem) {
        listContents.remove(targetItem);
    }

    public Item removeItemByIndex(int index) throws Exception {
        if (index<0 || index>=listContents.size()) {
            throw new Exception("index out of bounds");
        }
        return listContents.remove(index);
    }

    public void moveItemToPosition(int oldIndex, int newIndex) throws Exception {
        Item itemToMove;

        try {
            itemToMove = removeItemByIndex(oldIndex);
        } catch (Exception err) {
            throw new Exception("old index out of bounds");
        }

        if (newIndex<0 || newIndex>listContents.size()) {
            throw new Exception("new index out of bounds");
        }

        listContents.add(newIndex, itemToMove);
    }

    public int getItemPositionByName(String targetName) throws Exception {
        for (Item item : listContents) {
            if (item.getName().equals(targetName)) {
                return listContents.indexOf(item);
            }
        }
        throw new Exception("could not find that name in the list");
    }

    public String getItemStringByIndex(int index) throws Exception {
        if (index<0 || index>=listContents.size()) {
            throw new Exception("index out of bounds");
        }
        return listContents.get(index).toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(" === My Todo List === \n");
        for (Item item : listContents) {
            sb.append(item.toString());
            sb.append("\n");
        }
        return sb.toString();
    }
}
