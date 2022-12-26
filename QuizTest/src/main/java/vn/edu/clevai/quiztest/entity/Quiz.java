package vn.edu.clevai.quiztest.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "quiz")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quiz extends BaseEntity{

    @Column(name = "explanation",columnDefinition = "nvarchar(1023) not null")
    private String explanation;

    @Column(name = "exp_amount",columnDefinition = "int default 20")
    private  Integer expAmount ;

    @Column(name = "quiz_question",columnDefinition = "nvarchar(1023) not null")
    private  String quizQuestion;


    @ManyToOne
    @JoinColumn(name = "learning_object_id",columnDefinition = "INTEGER")
    @JsonManagedReference
    private LearningObject learningObject;

    @ManyToOne
    @JoinColumn(name = "quiz_type_id",columnDefinition = "INTEGER")
    @JsonManagedReference
    private QuizType quizType ;

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<Answer> answers = new ArrayList<>();

    @OneToMany(mappedBy = "quiz", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonBackReference
    private List<UserQuiz> userQuizList = new ArrayList<>();
}