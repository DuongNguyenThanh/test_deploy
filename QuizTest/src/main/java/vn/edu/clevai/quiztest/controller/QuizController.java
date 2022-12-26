package vn.edu.clevai.quiztest.controller;

import vn.edu.clevai.quiztest.constant.PathConstants;
import vn.edu.clevai.quiztest.payload.request.QuizRequest;
import vn.edu.clevai.quiztest.payload.request.UpdateQuizProgressReq;
import vn.edu.clevai.quiztest.payload.response.QuizDetailsResponse;
import vn.edu.clevai.quiztest.service.implement.QuizServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;


@CrossOrigin
@RestController
public class QuizController {

    private final QuizServiceImpl quizServiceImpl;

    public QuizController(QuizServiceImpl quizServiceImpl) {
        this.quizServiceImpl = quizServiceImpl;
    }

    @GetMapping(PathConstants.API_QUIZ_VIEW_URL)
    public ResponseEntity<?> getQuizById(@PathVariable @NotNull  int id) {

        return ResponseEntity.ok(quizServiceImpl.getQuizById(id));
    }

    @GetMapping(PathConstants.API_QUIZ_RANDOM_URL)
    public ResponseEntity<?> getRandomQuizByObjectId(@RequestParam(name = "learning-object-id") @NotNull int id) {
        QuizDetailsResponse result = quizServiceImpl.getRandomQuizByObjectId(id);

        return ResponseEntity.ok(result);
    }

    @PatchMapping(PathConstants.API_QUIZ_UPDATE_URL)
    public ResponseEntity<?> quizUpdateProgress(@RequestBody @Valid UpdateQuizProgressReq updateQuizProgressReq, @PathVariable int id) {

        return ResponseEntity.ok(quizServiceImpl.quizUpdateHasDoneRight(updateQuizProgressReq, id));
    }

    @GetMapping("/quiz/gen")
    public ResponseEntity<?> generateQuiz() {
        quizServiceImpl.generateQuiz();

        return ResponseEntity.ok("ok nhe");
    }

    @GetMapping("/quiz/reset")
    public ResponseEntity<?> resetData() {
        quizServiceImpl.resetData();

        return ResponseEntity.ok("Reset data success");
    }

    @PostMapping(PathConstants.API_QUIZ_BASE_URL)
    public ResponseEntity<?> createQuiz(@RequestBody @Valid QuizRequest quizRequest) {

        return ResponseEntity.status(200).body(quizServiceImpl.create(quizRequest));
    }

    @GetMapping(PathConstants.API_USER_PROGRESS_VIEW_URL)
    public ResponseEntity<?> getUserProgress ( @PathVariable @NotNull int id){

        return ResponseEntity.ok(quizServiceImpl.getUserProgress(id));
    }

    @PutMapping(PathConstants.API_QUIZ_BASE_URL + "/{id}")
    public ResponseEntity<?> updateQuiz (@RequestBody @Valid QuizRequest model, @PathVariable("id") @NotNull int id){

        return ResponseEntity.ok(quizServiceImpl.update(model, id));
    }

    @DeleteMapping(PathConstants.API_QUIZ_BASE_URL + "/{id}")
    public ResponseEntity<?> deleteQuiz ( @PathVariable @NotNull int id){
        quizServiceImpl.delete(id);

        return ResponseEntity.ok("Deleted successfully");
    }
}
