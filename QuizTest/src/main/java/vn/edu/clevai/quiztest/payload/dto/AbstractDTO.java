package vn.edu.clevai.quiztest.payload.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AbstractDTO {

    private int id;
    private Date createDate;
    private String createBy;
    private Date modifiedDate;
    private String modifiedBy;
}
