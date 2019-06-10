package pl.com.b2bnetwork.football.exception;

public class NotFoundLeagueException extends BaseException {
    public NotFoundLeagueException() {
        super("Nie ma drużyny o takim id");
    }
}
