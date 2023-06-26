package paf.visa.day24_pafworkshop.exception;

public class UnableToCreateOrderException extends RuntimeException{
    
    public UnableToCreateOrderException() {
        super();
    }

    public UnableToCreateOrderException(String message) {
        super(message);
    }

    public UnableToCreateOrderException(Throwable cause) {
        super(cause);
    }

    public UnableToCreateOrderException(String message, Throwable cause) {
        super(message, cause);
    }
}
