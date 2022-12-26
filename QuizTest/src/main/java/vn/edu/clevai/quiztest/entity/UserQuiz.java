package vn.edu.clevai.quiztest.entity;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


@Entity
@Table(name = "user_quiz")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserQuiz extends BaseEntity{

    @Column(name = "has_done_right",columnDefinition = "boolean default false not null ")
    private Boolean hasDoneRight;

    @Column(name = "completed_time",columnDefinition = "INTEGER default null ")
    private Integer completedTime;

    @ManyToOne
    @JoinColumn(name = "quiz_id",columnDefinition = "INTEGER")
    @JsonManagedReference
    private Quiz quiz;

    @Column(name = "user_id",columnDefinition = "INTEGER not null")
    private Integer userId;
}
