package vn.edu.clevai.quiztest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "quiz_type")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizType extends BaseEntity{

    @Column(name = "quiz_type_name",columnDefinition = "varchar(1023) not null")
    private  String name ;

    @Column(name = "has_blank",columnDefinition = "boolean default false")
    private Boolean hasBlank ;

    @Column(name = "has_option",columnDefinition = "boolean default  false")
    private Boolean hasOption ;

    @Column(name = "has_order",columnDefinition = "boolean default  false")
    private Boolean hasOrder;

    @Column(name = "has_connection",columnDefinition = " boolean default false")
    private  Boolean hasConnection;

    @OneToMany(mappedBy = "quizType")
    @JsonBackReference
    private List<Quiz> quizzes = new ArrayList<>();

}
