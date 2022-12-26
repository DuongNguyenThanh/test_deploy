package vn.edu.clevai.quiztest.repository;


import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import vn.edu.clevai.quiztest.entity.LearningObject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.clevai.quiztest.payload.response.LearningObjectResponse;

import java.util.List;


@Repository
public interface LearningObjectRepository extends JpaRepository<LearningObject, Integer> {

    @Query("select new vn.edu.clevai.quiztest.payload.response.LearningObjectResponse(lo.id, lo.title, lo.level, lo.totalExpRequired, ulo.currentExp, lo.avatar) from LearningObject as lo inner join UserLo as ulo on lo.id = ulo.learningObject.id where ulo.userId = ?1")
    List<LearningObjectResponse> findByUserID(Integer userId, Pageable pageable);

//    @Query("select new vn.edu.clevai.quiztest.payload.response.LearningObjectResponse(l.id,l.title,l.level,l.totalExpRequired) from LearningObject l where l.id =?1")
//    Optional<LearningObjectResponse> findObjectById(Integer id);
}
