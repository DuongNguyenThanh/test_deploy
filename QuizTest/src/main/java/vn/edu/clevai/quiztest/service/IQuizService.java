package vn.edu.clevai.quiztest.service;

import vn.edu.clevai.quiztest.payload.request.QuizRequest;
import vn.edu.clevai.quiztest.payload.request.UpdateQuizProgressReq;
import vn.edu.clevai.quiztest.payload.response.QuizDetailsResponse;

import java.util.List;

public interface IQuizService {

    /**
     * @param id is id of learning object.
     * @return list id of all quiz what are not right.
     */
    List<Integer> getAllIdQuizByObjectId(Integer id);

    /**
     *
     * @param id is id of quiz what you want to find.
     * @return object QuizDetailsRepose that have information what you need to take from quiz.
     */
    QuizDetailsResponse getQuizById(Integer id);

    /**
     *
     * @param id is id of learning object what you want to take from.
     * @return object QuizDetailsRepose that have information what you need to take from quiz .
     * this quiz is What you take after random all quiz that are not right.
     */
    QuizDetailsResponse getRandomQuizByObjectId(Integer id);

    /**
     * update quiz of user in db after user complete this quiz on web.
     * @param updateQuizProgressReq  is body of request from client
     * @param id is id of quiz what you want to update.
     * @return object have information about exp of user's learning object
     */
    Object quizUpdateHasDoneRight(UpdateQuizProgressReq updateQuizProgressReq, Integer id);

    /**
     * create quiz
     * @param quizRequest is body of request from client.
     * @return object QuizDetailsResponse that have information what you created.
     */
    QuizDetailsResponse create(QuizRequest quizRequest);

    /**
     * update quiz
     * @param quizRequest is body of request from client.
     * @param id is id of quiz you want to update.
     * @return object QuizRequest that have information what you updated.
     */
    QuizRequest update(QuizRequest quizRequest, int id);

    /**
     * delete quiz
     * @param id is id of quiz you want to delete.
     */
    void delete(int id);
}
