package by.tms.medicins.exception;

import java.io.IOException;

public class MedicinsException extends IOException {
    public MedicinsException() {
        super();
    }

    public MedicinsException(String message) {
        super(message);
    }

    public MedicinsException(String message, Throwable cause) {
        super(message, cause);
    }

    public MedicinsException(Throwable cause) {
        super(cause);
    }
}
