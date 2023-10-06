package ui;

public class MenuOption {

    private String text;
    
    public MenuOption(String newText) {
        this.text = newText;
    }

    public MenuOption() {
        this("empty option");
    }

    public String getText() {
        return text;
    }

    public void setText(String newText) {
        this.text = newText;
    }

    @Override
    public String toString() {
        return getText();
    }

}
