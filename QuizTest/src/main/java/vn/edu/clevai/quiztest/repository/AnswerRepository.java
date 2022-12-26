package vn.edu.clevai.quiztest.repository;


import vn.edu.clevai.quiztest.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AnswerRepository extends JpaRepository<Answer, Integer> {

    @Query("select an from Answer an where an.quiz.id=?1")
    List<Answer> findByQuizId(int id);

}
