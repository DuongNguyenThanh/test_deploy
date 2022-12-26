package vn.edu.clevai.quiztest.payload.response;

import lombok.*;
import org.springframework.http.HttpMessage;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpStatusCodeException;


@Data
@Builder
public class ErrorResponse {

     private Integer status;
     private String message;
     private String path;
     private Long timestamp;

}
