package ui;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class TextUI {

    private ArrayList<MenuOption> menuOptions;
    private Scanner input;

    public TextUI() {
        menuOptions = new ArrayList<>();
        input = new Scanner(System.in);
    }

    public void addMenuOption(MenuOption option) {
        menuOptions.add(option);
    }

    public void addMenuOption(String text) {
        MenuOption menuOp = new MenuOption(text);
        addMenuOption(menuOp);
    }

    public int getNumOptions() {
        return menuOptions.size();
    }

    public int getUserChoice() {
	while (true) {
		System.out.println(this);
		try {
			int userChoice = getUserInt("Enter menu choice");
			if (userChoice<=0 || userChoice>getNumOptions()) {
				throw new Exception("Menu choice out of bounds");
			}
			return userChoice;
		} catch (InputMismatchException err) {
			System.out.println("Not an integer");
			input.nextLine();
		} catch (Exception err) {
			System.out.println(err.getMessage());
		}
	}
    }

    private String cleanInput(String rawInput) {
	return rawInput.trim();
    }

    private void promptUser(String prompt) {
	System.out.print(prompt + ": ");
    }

    public String getUserLine(String prompt) {
	promptUser(prompt);
	String userLine = input.nextLine();
	return cleanInput(userLine);
    }

    public int getUserInt(String prompt) throws InputMismatchException {
	promptUser(prompt);
	try {
		int userInt = input.nextInt();
		input.nextLine();
		return userInt;
	} catch(InputMismatchException err) {
		input.nextLine();
		throw err;
	}
    }

    public int getOptionPositionByText(String targetText) throws Exception {
	for(MenuOption option: menuOptions) {
		if(option.getText().equals(targetText)) {
			return menuOptions.indexOf(option) + 1;
		}
	}
	throw new Exception("Could not find " + targetText + " in menu");
    }

    @Override
    public String toString() {
	StringBuilder sb = new StringBuilder();
	sb.append(" === Menu === \n");
	for(MenuOption option : menuOptions) {
		sb.append(menuOptions.indexOf(option)+1);
		sb.append(": " + option.toString());
		sb.append("\n");
	}
	return sb.toString();
    }
}
