package todo;

public class NoElementsException extends Exception {

    public NoElementsException() {
        super("This list is empty");
    }

    public NoElementsException(String message) {
        super(message);
    }

}