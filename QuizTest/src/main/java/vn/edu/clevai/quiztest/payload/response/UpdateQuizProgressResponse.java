package vn.edu.clevai.quiztest.payload.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateQuizProgressResponse {

    private Integer total_exp;

    private Integer current_exp;

    private Integer learning_object_id;
}
