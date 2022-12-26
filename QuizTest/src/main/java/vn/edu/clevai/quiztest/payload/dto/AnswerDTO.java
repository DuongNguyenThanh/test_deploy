package vn.edu.clevai.quiztest.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AnswerDTO extends AbstractDTO{

    private String content ;
    private Boolean isRightOption;
    private String correctConnectContent;
    private Integer answerOrder;
    private String answerBlank;
}
