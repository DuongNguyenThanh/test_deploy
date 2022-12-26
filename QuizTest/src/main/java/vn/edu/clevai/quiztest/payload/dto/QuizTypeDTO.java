package vn.edu.clevai.quiztest.payload.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class QuizTypeDTO extends AbstractDTO {

    private String name;
    private Boolean hasBlank;
    private Boolean hasOption;
    private Boolean hasOrder;
    private Boolean hasConnection;

}
