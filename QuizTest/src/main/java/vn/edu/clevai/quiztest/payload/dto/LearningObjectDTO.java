package vn.edu.clevai.quiztest.payload.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class LearningObjectDTO extends AbstractDTO {

    private String title;
    private Integer level ;
    private Integer totalExpRequired ;
    private String avatar;

}
