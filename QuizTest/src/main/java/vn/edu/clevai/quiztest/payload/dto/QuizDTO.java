package vn.edu.clevai.quiztest.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizDTO extends AbstractDTO {

    private String explanation;
    private  String quizQuestion;
    private  Integer expAmount ;
}
