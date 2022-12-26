package vn.edu.clevai.quiztest.filter.mapper;

import vn.edu.clevai.quiztest.entity.*;
import vn.edu.clevai.quiztest.payload.dto.*;

import java.util.Optional;


public class Mapper {

    public static <T extends AbstractDTO> T setTime(T input, Optional<? extends BaseEntity> input2) {
       input.setId(input2.get().getId());
       input.setCreateBy(input2.get().getCreateBy());
       input.setCreateDate(input2.get().getCreateDate());
       input.setModifiedBy(input2.get().getModifiedBy());
       input.setModifiedDate(input2.get().getModifiedDate());

       return input;
    }

    public static QuizDTO toQuizDto(Quiz quiz) {
        QuizDTO result = QuizDTO.builder().quizQuestion(quiz.getQuizQuestion()).explanation(quiz.getExplanation())
            .expAmount(quiz.getExpAmount()).build();
        return Mapper.setTime(result,Optional.of(quiz));
    }

    public static AnswerDTO toAnswerDto(Answer answer) {
        AnswerDTO result = AnswerDTO.builder().answerBlank(answer.getAnswerBlank()).answerOrder(answer.getAnswerOrder()).correctConnectContent(answer.getCorrectConnectContent())
            .content(answer.getContent()).isRightOption(answer.getIsRightOption()).build();
        return Mapper.setTime(result,Optional.of(answer));
    }

    public static QuizTypeDTO toQuizTypeDto(QuizType quizType) {
        QuizTypeDTO result = QuizTypeDTO.builder().name(quizType.getName()).hasConnection(quizType.getHasConnection()).hasOption(quizType.getHasOption())
            .hasOrder(quizType.getHasOrder()).hasBlank(quizType.getHasBlank()).build();
        return Mapper.setTime(result,Optional.of(quizType));
    }

    public static LearningObjectDTO toLearningObjectDto(LearningObject learningObject) {
        LearningObjectDTO result = LearningObjectDTO.builder().level(learningObject.getLevel()).title(learningObject.getTitle())
            .avatar(learningObject.getAvatar()).totalExpRequired(learningObject.getTotalExpRequired()).build();
        return Mapper.setTime(result,Optional.of(learningObject));
    }


}
