package vn.edu.clevai.quiztest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.clevai.quiztest.entity.UserQuiz;
import vn.edu.clevai.quiztest.payload.dto.UserQuizDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserQuizRepository extends JpaRepository<UserQuiz,Integer>{

    @Query("select u.quiz.id from UserQuiz u  where u.quiz.learningObject.id=?1 and u.userId=?2 and u.hasDoneRight=false ")
    List<Integer> findALLIdQuizNotRightByLoId(Integer LoId,Integer userId);
    @Query("select u from UserQuiz u where u.quiz.id=?1")
    Optional<UserQuiz> findByQuizId(Integer id);

    @Query("select new vn.edu.clevai.quiztest.payload.dto.UserQuizDTO(u.hasDoneRight,u.completedTime,u.quiz.id) from UserQuiz  u where  u.userId=?1 and u.completedTime is not null")
    List<UserQuizDTO> findCompletedQuizByUserId(Integer userId);

}
