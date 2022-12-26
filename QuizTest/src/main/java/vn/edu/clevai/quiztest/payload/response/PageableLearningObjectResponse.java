package vn.edu.clevai.quiztest.payload.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageableLearningObjectResponse {

    private int page;
    private int totalPage;
    private List<LearningObjectResponse> lstResult = new ArrayList<>();
}
