package vn.edu.clevai.quiztest.entity;


import javax.persistence.*;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;


@Table(name = "user_lo")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class UserLo extends BaseEntity{

    @Column(name = "user_id",columnDefinition = "INTEGER not null")
    private Integer userId;

    @Column(name = "current_exp",columnDefinition = "int")
    private Integer currentExp;

    @ManyToOne
    @JoinColumn(name = "learning_object_id")
    @JsonManagedReference
    private LearningObject learningObject;

}
