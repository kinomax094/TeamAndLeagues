package pl.com.b2bnetwork.football.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.com.b2bnetwork.football.dto.ErrorDto;

@ControllerAdvice
public class LeaguesExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(LeaguesExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public ResponseEntity<ErrorDto> handle(final Exception exception) {

        ErrorDto errorDto = new ErrorDto();
        if (exception instanceof BaseException) {
            errorDto.setMessage(exception.getMessage());
        }
        logger.error(errorDto.getMessage());

        ResponseEntity<ErrorDto> result = new ResponseEntity<>(errorDto, HttpStatus.BAD_REQUEST);

        return result;
    }

}
