package vn.edu.clevai.quiztest.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserLoDTO extends AbstractDTO{
    private Integer currentExp;
    private Integer learningObjectId;
    private Integer totalExp;
}
