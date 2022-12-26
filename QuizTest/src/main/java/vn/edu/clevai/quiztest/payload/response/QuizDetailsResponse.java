package vn.edu.clevai.quiztest.payload.response;

import lombok.*;
import vn.edu.clevai.quiztest.payload.dto.*;

import java.util.List;

@Data
@Builder
public class QuizDetailsResponse {

   private QuizDTO quiz;
   private List<AnswerDTO> answer;
   private LearningObjectDTO learning_object;
   private QuizTypeDTO quiz_type;
}


