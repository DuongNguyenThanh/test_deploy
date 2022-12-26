package vn.edu.clevai.quiztest.payload.response;

import lombok.*;
import vn.edu.clevai.quiztest.payload.dto.UserLoDTO;
import vn.edu.clevai.quiztest.payload.dto.UserQuizDTO;

import java.util.List;


@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserProgressResponse {

    private Integer user_id;

    private List<UserLoDTO>  user_lo;

    private List<UserQuizDTO> user_quiz;

}
