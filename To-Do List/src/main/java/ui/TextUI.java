package ui;

import todo.Item;
import todo.TodoList;
import todo.NoElementsException;
import todo.ItemNotFoundException;

import java.io.IOException;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import files.FileReader;
import files.FileWriter;
import files.FileFormatException;

public class TextUI {

    private Menu mainMenu;
    private Scanner input;

    private TodoList list;

    public TextUI() {
        input = new Scanner(System.in);

        mainMenu = new Menu("Main Menu");

        try {
            mainMenu.addOption("View list");
            mainMenu.addOption("Add item to list");
            mainMenu.addOption("Complete item in list");
            mainMenu.addOption("Delete item in list");
            mainMenu.addOption("Save list");
            mainMenu.addOption("Load list");
            mainMenu.addOption("Exit");
        } catch (Exception err) {
            System.out.println(err.getMessage());
        }

        list = new TodoList("Coursework Todo List");
    }

    private String cleanString(String rawString) {
        return rawString.trim();
    }

    public String getUserString() {
        return cleanString(input.next());
    }

    public String getUserLine() {
        return cleanString(input.nextLine());
    }

    public int getUserInt() throws Exception {
        try {
            int userChoice = input.nextInt();

            getUserLine();

            return userChoice;
        } catch (InputMismatchException err) {
            throw new Exception(getUserLine() + " is not an integer");
        }
    }

    public LocalDateTime getUserDateTime() throws Exception {
        String[] inputs = getUserLine().split(" ");
        String dateString = inputs[0];
        String timeString = inputs[1];

        try {
            LocalDateTime dateTime = LocalDateTime.parse(dateString + "T" + timeString);

            return dateTime;
        } catch (DateTimeParseException err) {
            throw new Exception("Datetime is not valid: " + err.getMessage());
        }
    }

    private void promptUser(String prompt) {
        System.out.print(prompt + ": ");
    }

    public String getUserString(String prompt) {
        promptUser(prompt);
        return getUserString();
    }

    public String getUserLine(String prompt) {
        promptUser(prompt);
        return getUserLine();
    }

    public int getUserInt(String prompt) {
        while (true) {
            promptUser(prompt);

            try {

                return getUserInt();

            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }
    }

    public LocalDateTime getUserDateTime(String prompt) throws Exception {
        promptUser(prompt + " (yyyy-mm-dd hh:mm:ss)");
        return getUserDateTime();
    }

    public int promptMenu(Menu m) throws Exception {

        if (m.getNumOptions() <= 0) {
            throw new Exception("Can not prompt an empty menu!");
        }

        while (true) {

            System.out.println(m);

            try {
                int userChoice = getUserInt("Enter option choice");

                if (userChoice < 1 || userChoice > m.getNumOptions()) {
                    throw new Exception(
                            String.format("Invalid value '%d' for range 1-%d", userChoice, m.getNumOptions()));
                }

                return userChoice;

            } catch (Exception err) {
                System.out.println(err.getMessage());
            }
        }
    }

    private void addItemToListOption(TodoList list) throws Exception {
        String title = getUserLine("Enter item title");
        String desc = getUserLine("Enter item description");
        LocalDateTime dueDateTime;
        try {
            dueDateTime = getUserDateTime("Enter item due date and time");
        } catch (Exception err) {
            System.out.println(err.getMessage());
            System.out.println("Defaulting to now + 1 hour");
            dueDateTime = LocalDateTime.now().plusHours(1);
        }
        int priority = getUserInt("Enter item priority");

        list.addItem(title, desc, dueDateTime, priority);
    }

    private void completeItemOption(TodoList list) throws NoElementsException, IndexOutOfBoundsException {
        
        while(true) {
            try {
                String title = getUserLine("Enter item title to mark as completed");
                int itemIndex = list.getIndexByTitle(title);

                list.completeItem(itemIndex);

                return;
            }
            catch(ItemNotFoundException err) {
                System.out.println(err.getMessage());
            }
        }
    }

    private void deleteItemOption(TodoList list) throws Exception, ItemNotFoundException, NoElementsException, IndexOutOfBoundsException {
        String title = getUserLine("Enter item title to delete");
        String confirmTitle = getUserLine("Re-enter item title to confirm");

        if (!title.equals(confirmTitle)) {
            throw new Exception("Item titles did not match");
        }

        int itemIndex = list.getIndexByTitle(title);

        Item removedItem = list.removeItem(itemIndex);

        System.out.println("Item removed:");
        System.out.println(removedItem);
    }

    private void saveListOption(TodoList list) {
        String saveFileName = getUserLine("Enter name for save file");
        FileWriter fw = new FileWriter(saveFileName);

        fw.write(list.getSave());

        for (Item item : list) {
            fw.write(item.getSave());
        }

        try {
            fw.save();
        } catch (IOException err) {
            System.out.println(err.getMessage());
        }

        System.out.println("Saved to '" + fw.getFilePath() + "'");
    }

    private void loadListOption() {

        String saveFileName = getUserLine("Enter file name to load");

        try {
            FileReader fr = new FileReader(saveFileName);

            String listTitle = fr.readLine();

            TodoList newList = new TodoList(listTitle);

            int numItems = fr.readInt();
            for (int i = 0; i < numItems; i++) {
                String itemTitle = fr.readLine();
                String itemDesc = fr.readLine();
                LocalDateTime dueDate = fr.readDateTime();
                int priority = fr.readInt();
                boolean completed = fr.readBoolean();

                newList.addItem(itemTitle, itemDesc, dueDate, priority);

                if (completed) {
                    newList.completeItem(newList.getIndexByTitle(itemTitle));
                }

            }

            this.list = newList;

            System.out.println("List loaded from file '" + fr.getFilePath() + "'");

        } catch (FileFormatException err) {
            System.out.println(err.getMessage());
        } catch (Exception err) {
            System.out.println(err);
        }

    }

    public void runInputLoop() throws Exception {

        int userChoice = 0;

        while ((userChoice = promptMenu(mainMenu)) != mainMenu.textToOptionInt("Exit")) {

            try {

                switch (userChoice) {
                    case 1:
                        System.out.println(list.toString());
                        break;
                    case 2:
                        addItemToListOption(list);
                        break;
                    case 3:
                        completeItemOption(list);
                        break;
                    case 4:
                        deleteItemOption(list);
                        break;
                    case 5:
                        saveListOption(list);
                        break;
                    case 6:
                        loadListOption();
                        break;
                }

            } catch(NoElementsException err) {
                System.out.println(err.getMessage());
            } catch(IndexOutOfBoundsException err) {
                System.out.println("Index is out of bounds for the list: " + err.getMessage());
            } catch (Exception err) {
                System.out.println(err);
            }

        }
    }

    public static void main(String[] args) {
        TextUI ui = new TextUI();

        try {
            ui.runInputLoop();
        } catch (Exception err) {
            System.out.println("Error running ui input loop!");
        }
    }

}
