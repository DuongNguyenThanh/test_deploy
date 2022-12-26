package vn.edu.clevai.quiztest.payload.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateQuizProgressReq {

    @NotNull
    @Min(value = 1,message = "quiz's id can not be less than 1")
    private Integer id;

    @NotNull
    private Boolean hasDoneRight;

    @NotNull
    @Min(value = 0,message = "completed time can not be less than 0")
    @Max(value = 36000,message = "completed time can not be greater than 36000")
    private Integer completedTime;

}
