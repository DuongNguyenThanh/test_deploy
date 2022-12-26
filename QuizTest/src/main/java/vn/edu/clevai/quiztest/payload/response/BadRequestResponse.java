package vn.edu.clevai.quiztest.payload.response;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class BadRequestResponse {
    private Integer status;
    private String message;
    private String path;
    private Long timestamp;
    private Map<String,String> errors;
}
