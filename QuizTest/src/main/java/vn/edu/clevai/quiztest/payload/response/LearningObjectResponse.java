package vn.edu.clevai.quiztest.payload.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LearningObjectResponse {

    private int id;
    private String title;
    private int level;
    private int totalExpRequired;
    private int currentExp;
    private String avatar;

}
