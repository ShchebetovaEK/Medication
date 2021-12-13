package by.tms.medicins.exception;

public class DrugException extends Exception{
    public DrugException() {
        super();
    }

    public DrugException(String message) {
        super(message);
    }

    public DrugException(String message, Throwable cause) {
        super(message, cause);
    }

    public DrugException(Throwable cause) {
        super(cause);
    }
}
