package vn.edu.clevai.quiztest.payload.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
public class QuizRequest {

    private Integer id;

    @NotNull
    @Size(min = 5, message = "Question should have at least 5 characters")
    private  String quizQuestion;

    @NotNull
    @Size(min = 5, message = "Explanation should have at least 5 characters")
    private String explanation;

    @NotNull
    @Min(value = 20,message = "Exp amount can not be less than 20")
    private  Integer expAmount ;

    @NotNull
    private Integer learningObjectId;

    @NotNull
    private Integer quizTypeId;

    private List<CreateAnswerRequest> answers;
}
