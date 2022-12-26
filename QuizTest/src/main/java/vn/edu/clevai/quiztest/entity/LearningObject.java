package vn.edu.clevai.quiztest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "learning_object")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LearningObject extends BaseEntity {

    @Column(name = "title",columnDefinition = "varchar(1023)")
    private String title;

    @Column(name = "level",columnDefinition = "int")
    private Integer level ;

    @Column(name = "total_exp",columnDefinition = "")
    private Integer totalExpRequired ;

    @Column(columnDefinition = "varchar(1023) default 'https://r73troypb4obj.vcdn.cloud/picture/icons/home_icon-star.svg'")
    private String avatar;

    @OneToMany(mappedBy = "learningObject")
    @JsonBackReference
    private List<Quiz> quizzes = new ArrayList<>();

    @OneToMany(mappedBy = "learningObject")
    @JsonIgnore
    private List<UserLo> userLoList = new ArrayList<>();

}
