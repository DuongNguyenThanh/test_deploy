package vn.edu.clevai.quiztest.payload.request;

import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
public class CreateAnswerRequest {

    @NotNull
    private String content ;

    private Boolean isRightOption ;

    private String correctConnectContent ;

    private Integer answerOrder ;

    private  String answerBlank ;


}
