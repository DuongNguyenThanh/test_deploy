package vn.edu.clevai.quiztest.filter.mapper;

import org.springframework.stereotype.Component;
import vn.edu.clevai.quiztest.entity.LearningObject;
import vn.edu.clevai.quiztest.payload.dto.LearningObjectDTO;

@Component
public class LearningObjectMapper {

//    public LearningObjectDTO toDTO(LearningObject learningObject) {
//        LearningObjectDTO dto = new LearningObjectDTO();
//        dto.setId(learningObject.getId());
//        dto.setTitle(learningObject.getTitle());
//        dto.setLevel(learningObject.getLevel());
//        dto.setTotalExpRequired(learningObject.getTotalExpRequired());
//        dto.setAvatar(learningObject.getAvatar());
//        return dto;
//    }

    public LearningObject toEntity(LearningObjectDTO dto, LearningObject entity) {
        entity.setTitle(dto.getTitle());
        entity.setLevel(dto.getLevel());
        entity.setTotalExpRequired(dto.getTotalExpRequired());
        entity.setAvatar(dto.getAvatar());
        return entity;
    }
}
