package vn.edu.clevai.quiztest.repository;


import vn.edu.clevai.quiztest.entity.QuizType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuizTypeRepository extends JpaRepository<QuizType, Integer> {

}
