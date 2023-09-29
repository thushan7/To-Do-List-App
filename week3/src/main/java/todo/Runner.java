package todo;

import java.time.LocalDate;

public class Runner{
    public static void main(String[] args){
        TodoList myList = new TodoList();

        System.out.println(myList);

        Item i1;
        Item i2;
        Item i3;
        i1 = new Item();
        i2 = new Item("Finish A1", "complete and submit assignment 1", LocalDate.of(2023, 10, 1));
        //i3 = new Item("Finish Lab", "write up lab", LocalDate.now());

        myList.addItem(i1);
        myList.addItem(i2);
        myList.addItem("Finish Lab 3", "write up lab", LocalDate.now());

        System.out.println(myList);

        myList.removeItem(i1);

        System.out.println(myList);

        try {
            myList.removeItemByIndex(0);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        System.out.println(myList);

        try {
            myList.moveItemToPosition(1, 0);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        System.out.println(myList);

        try {
            int indexFound = myList.getItemPositionByName(new String("Finish A1"));
            System.out.println("index: " + indexFound);
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        try {
            System.out.println(myList.getItemStringByIndex(0));
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }
    }
}
