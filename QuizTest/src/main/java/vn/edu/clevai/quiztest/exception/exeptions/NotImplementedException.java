package vn.edu.clevai.quiztest.exception.exeptions;

import org.springframework.http.HttpStatus;

public class NotImplementedException extends BaseWebException{

    public NotImplementedException(String message) {
        super(HttpStatus.NOT_IMPLEMENTED, message);
    }
}
