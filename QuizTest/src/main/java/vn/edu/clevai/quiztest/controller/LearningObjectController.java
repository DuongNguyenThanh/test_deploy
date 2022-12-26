package vn.edu.clevai.quiztest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.clevai.quiztest.constant.PathConstants;
import vn.edu.clevai.quiztest.payload.dto.LearningObjectDTO;
import vn.edu.clevai.quiztest.service.ILearningObjectService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@CrossOrigin
@RestController
public class LearningObjectController {

    private final ILearningObjectService learningObjectService;

    public LearningObjectController(ILearningObjectService learningObjectService) {
        this.learningObjectService = learningObjectService;
    }

    @GetMapping(PathConstants.API_LO_BASE_URL)
    public ResponseEntity<?> showLO(@RequestParam("page") @NotNull int page) {

        return ResponseEntity.ok(learningObjectService.pagingLO(page, 4));
    }

//    @PutMapping(PathConstants.API_LO_BASE_URL + "/{id}")
//    public LearningObjectDTO updateLO(@RequestBody @Valid LearningObjectDTO model, @PathVariable("id") @NotNull int id) {
//        return learningObjectService.update(model, id);
//    }
}
