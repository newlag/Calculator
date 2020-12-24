package newlag.calculator;

public class ZeroDivisionException extends Exception {
    private String message;

    public ZeroDivisionException(String message) {
        super(message);
        this.message = message;
    }

    public String toString() {
        return message;
    }
}
