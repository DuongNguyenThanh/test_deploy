package vn.edu.clevai.quiztest.repository;


import vn.edu.clevai.quiztest.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface QuizRepository extends JpaRepository<Quiz, Integer> {

}
