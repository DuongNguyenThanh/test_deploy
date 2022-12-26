package vn.edu.clevai.quiztest.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserQuizDTO extends AbstractDTO{
    private Boolean hasDoneRight;
    private Integer completedTime;
    private Integer quizId ;
}
