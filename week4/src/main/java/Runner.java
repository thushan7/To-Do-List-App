import ui.TextUI;
import todo.TodoList;
import todo.Item;
import java.time.LocalDate;
import java.time.DateTimeException;
import java.util.InputMismatchException;

public class Runner{
    private static void promptAndAddItemToList(TextUI ui, TodoList list) {
	try {
		String title = ui.getUserLine("Enter item title");
		String desc = ui.getUserLine("Enter item description");
		int year = ui.getUserInt("Enter item due year");
		int month = ui.getUserInt("Enter item due month");
		int day = ui.getUserInt("Enter item due day");

		LocalDate dueDate = LocalDate.of(year, month, day);

		Item newItem = new Item(title, desc, dueDate);

		list.addItem(newItem);
	} catch(InputMismatchException err) {
		System.out.println("Not an integer");
	} catch(DateTimeException err) {
		System.out.println("Invalid due date: " + err.getMessage());
	}
    }

    private static void promptAndRemoveItemFromList(TextUI ui, TodoList list) {
	try {
		String titleToRemove = ui.getUserLine("Enter title to remove");
		int indexToRemove = list.getItemPositionByName(titleToRemove);
		list.removeItemByIndex(indexToRemove);
	} catch(Exception err) {
		System.out.println(err.getMessage());
	}
    }

    private static void promptAndMoveItem(TextUI ui, TodoList list) {
	try {
                String titleToMove = ui.getUserLine("Enter title to move");
		int oldIndex = list.getItemPositionByName(titleToMove);
		int newIndex = ui.getUserInt("Enter new position") - 1;
		list.moveItemToPosition(oldIndex, newIndex);
        } catch(Exception err) {
                System.out.println(err.getMessage());
        }
    }

    public static void main(String[] args){

	TextUI ui = new TextUI();
	TodoList myList = new TodoList();

	ui.addMenuOption("View Todo List");
	ui.addMenuOption("Add Item to List");
	ui.addMenuOption("Remove Item from List");
	ui.addMenuOption("Move Item in List");
	ui.addMenuOption("Exit");

	int userChoice = 0;
    try {
	do {
		userChoice = ui.getUserChoice();
		switch(userChoice) {
			case 1:
				System.out.println(myList);
				break;
			case 2:
				promptAndAddItemToList(ui, myList);
				break;
			case 3:
				promptAndRemoveItemFromList(ui, myList);
				break;
			case 4:
				promptAndMoveItem(ui, myList);
				break;
		}
	} while (userChoice != ui.getOptionPositionByText("Exit"));
    } catch(Exception err) {
	System.out.println(err.getMessage());
    } finally {
	System.out.println("Goodbye");
    }
  }
}
