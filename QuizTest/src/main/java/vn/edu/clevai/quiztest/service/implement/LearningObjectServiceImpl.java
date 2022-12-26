package vn.edu.clevai.quiztest.service.implement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vn.edu.clevai.quiztest.entity.LearningObject;
import vn.edu.clevai.quiztest.exception.exeptions.NotFoundException;
import vn.edu.clevai.quiztest.filter.mapper.LearningObjectMapper;
import vn.edu.clevai.quiztest.filter.mapper.Mapper;
import vn.edu.clevai.quiztest.payload.dto.LearningObjectDTO;
import vn.edu.clevai.quiztest.payload.response.LearningObjectResponse;
import vn.edu.clevai.quiztest.payload.response.PageableLearningObjectResponse;
import vn.edu.clevai.quiztest.repository.LearningObjectRepository;
import vn.edu.clevai.quiztest.service.ILearningObjectService;

import java.util.List;

@Service
@Slf4j
public class LearningObjectServiceImpl implements ILearningObjectService {

    private final LearningObjectRepository learningObjectRepository;

    private final LearningObjectMapper learningObjectMapper;


    public LearningObjectServiceImpl(LearningObjectRepository learningObjectRepository, LearningObjectMapper learningObjectMapper) {
        this.learningObjectRepository = learningObjectRepository;
        this.learningObjectMapper = learningObjectMapper;
    }

    @Override
    public List<LearningObjectResponse> findAll(Pageable pageable) {
        List<LearningObjectResponse> result = learningObjectRepository.findByUserID(1, pageable);
        return result;
    }

    @Override
    public int getTotalItem() {
        return (int)learningObjectRepository.count();
    }

    @Override
    public PageableLearningObjectResponse pagingLO(int page, int limit) {
        PageableLearningObjectResponse result = new PageableLearningObjectResponse();
        result.setPage(page);
        Pageable pageable = PageRequest.of(page-1, limit);
        result.setLstResult(findAll(pageable));
        result.setTotalPage((int)Math.ceil((double)getTotalItem()/(double)limit));
        return result;
    }

//    @Override
//    public LearningObjectDTO update(LearningObjectDTO learningObjectDTO, int id) {
//
//        LearningObject oldLearningObject = learningObjectRepository.findById(id).orElseThrow(() -> new NotFoundException("Không tồn tại Learning Object trong hệ thống"));
//        LearningObject learningObject = learningObjectMapper.toEntity(learningObjectDTO, oldLearningObject);
//
//        learningObject = learningObjectRepository.save(learningObject);
//        return learningObjectMapper.toDTO(learningObject);
//    }
}
