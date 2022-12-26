package vn.edu.clevai.quiztest.service;

import org.springframework.data.domain.Pageable;
import vn.edu.clevai.quiztest.payload.dto.LearningObjectDTO;
import vn.edu.clevai.quiztest.payload.response.LearningObjectResponse;
import vn.edu.clevai.quiztest.payload.response.PageableLearningObjectResponse;

import java.util.List;

public interface ILearningObjectService {

    /**
     * find all Learning Object with pageable
     * @param pageable is Pageable object for pagination.
     * @return list LearningObjectResponse that have information what you need.
     */
    List<LearningObjectResponse> findAll(Pageable pageable);

    /**
     * get total Learning Object in database.
     * @return total Learning Object.
     */
    int getTotalItem();

    /**
     * response data for pagination to client
     * @param page is page order you want to show Learning Object.
     * @param limit is limit of Learning Object in one page.
     * @return object PageableLearningObjectResponse that have information what you need.
     */
    PageableLearningObjectResponse pagingLO(int page, int limit);

//    LearningObjectDTO update(LearningObjectDTO learningObjectDTO, int id);
}
