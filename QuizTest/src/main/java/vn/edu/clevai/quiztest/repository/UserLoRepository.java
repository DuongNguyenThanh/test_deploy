package vn.edu.clevai.quiztest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.edu.clevai.quiztest.entity.UserLo;
import vn.edu.clevai.quiztest.payload.dto.UserLoDTO;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserLoRepository extends JpaRepository<UserLo,Integer> {

    @Query("select u from UserLo u where u.userId=?1 and  u.learningObject.id=?2")
    Optional<UserLo> findByLoIdAndUserId(Integer userId,Integer LoId);

    @Query("select new vn.edu.clevai.quiztest.payload.dto.UserLoDTO(u.currentExp,u.learningObject.id,u.learningObject.totalExpRequired) from UserLo u where  u.userId=?1")
    List<UserLoDTO> findByUserId(Integer userId);

}
