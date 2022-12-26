package vn.edu.clevai.quiztest.exception.exeptions;


import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseWebException{
    public  NotFoundException(String messenger) {
        super(HttpStatus.NOT_FOUND,messenger);
    }

//    public  NotFoundException() {super(HttpStatus.NOT_FOUND);}

}
