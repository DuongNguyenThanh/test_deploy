package vn.edu.clevai.quiztest.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

@Table(name = "answer")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class  Answer extends BaseEntity {

    @Column(name = "content",columnDefinition = "nvarchar(1023)")
    private String content ;

    @Column(name = "is_right_option",columnDefinition = "boolean default false")
    private Boolean isRightOption ;

    @Column(name = "connect_content",columnDefinition = "nvarchar(1023) default null")
    private String correctConnectContent ;

    @Column(name = "order_answer",columnDefinition = "int default null")
    private Integer answerOrder ;

    @Column(name = "blank_answer",columnDefinition = "nvarchar(255)")
    private  String answerBlank ;

    @ManyToOne
    @JoinColumn(name = "quiz_id",columnDefinition = "INTEGER")
    @JsonBackReference
    private Quiz quiz;
}
