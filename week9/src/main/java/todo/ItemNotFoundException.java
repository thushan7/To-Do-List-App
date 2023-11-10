package todo;

public class ItemNotFoundException extends Exception {

    public ItemNotFoundException() {
        super("Item was not found!");
    }

    public ItemNotFoundException(String propertyName, String propertyValue) {
        super("The item with " + propertyName + " = '" + propertyValue + "' could not be found");
    }

}