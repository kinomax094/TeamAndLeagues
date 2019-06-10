package pl.com.b2bnetwork.football.exception;

public class BaseException extends RuntimeException {

    private String message;

    public BaseException(final String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(final String message) {
        this.message = message;
    }


}
