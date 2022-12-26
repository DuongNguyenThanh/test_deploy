package vn.edu.clevai.quiztest.service.implement;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import vn.edu.clevai.quiztest.entity.*;
import vn.edu.clevai.quiztest.exception.exeptions.*;
import vn.edu.clevai.quiztest.filter.mapper.Mapper;
import vn.edu.clevai.quiztest.filter.mapper.QuizMapper;
import vn.edu.clevai.quiztest.payload.dto.UserLoDTO;
import vn.edu.clevai.quiztest.payload.dto.UserQuizDTO;
import vn.edu.clevai.quiztest.payload.request.CreateAnswerRequest;
import vn.edu.clevai.quiztest.payload.request.QuizRequest;
import vn.edu.clevai.quiztest.payload.request.UpdateQuizProgressReq;
import vn.edu.clevai.quiztest.payload.response.QuizDetailsResponse;
import vn.edu.clevai.quiztest.filter.random.RandomAndGet;
import vn.edu.clevai.quiztest.payload.response.UpdateQuizProgressResponse;
import vn.edu.clevai.quiztest.payload.response.UserProgressResponse;
import vn.edu.clevai.quiztest.repository.*;

import vn.edu.clevai.quiztest.service.IQuizService;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;


@Service
@Slf4j
public class QuizServiceImpl implements IQuizService {

    private final AnswerRepository answerRepository;

    private final LearningObjectRepository learningObjectRepository;

    private final QuizRepository quizRepository;

    private final QuizTypeRepository quizTypeRepository;

    private final UserQuizRepository userQuizRepository;

    private final UserLoRepository userLoRepository;

    private final QuizMapper quizMapper;

    public QuizServiceImpl(AnswerRepository answerRepository, LearningObjectRepository learningObjectRepository, QuizRepository quizRepository, QuizTypeRepository quizTypeRepository, UserQuizRepository userQuizRepository, UserLoRepository userLoRepository, QuizMapper quizMapper) {
        this.answerRepository = answerRepository;
        this.learningObjectRepository = learningObjectRepository;
        this.quizRepository = quizRepository;
        this.quizTypeRepository = quizTypeRepository;
        this.userQuizRepository = userQuizRepository;
        this.userLoRepository = userLoRepository;
        this.quizMapper = quizMapper;
    }

    public List<Integer> getAllIdQuizByObjectId(Integer id){
        List<Integer> result =  userQuizRepository.findALLIdQuizNotRightByLoId(id,1);
        if (result.size() == 0) throw new NotFoundException("No existence not completed quiz");

        return result;
    }
    public QuizDetailsResponse getRandomQuizByObjectId(Integer id) {

        if(learningObjectRepository.existsById(id)) {
            Integer quizId = (Integer) RandomAndGet.random(this.getAllIdQuizByObjectId(id), 1).get(0);

            return getQuizById(quizId);
        } else throw new NotFoundException("Learning Object do not exist");
    }
    public QuizDetailsResponse getQuizById(Integer id) {
        Quiz quiz = quizRepository.findById(id).orElseThrow(() -> new NotFoundException("No existence quiz"));

        return QuizDetailsResponse.builder().quiz(Mapper.toQuizDto(quiz)).answer(quiz.getAnswers().stream().map(e->Mapper.toAnswerDto(e)).collect(Collectors.toList()))
            .quiz_type(Mapper.toQuizTypeDto(quiz.getQuizType())).learning_object(Mapper.toLearningObjectDto(quiz.getLearningObject())).build();
    }

    public Object quizUpdateHasDoneRight(UpdateQuizProgressReq updateQuizProgressReq, Integer id){
        UserQuiz userQuiz = userQuizRepository.findByQuizId(id).orElseThrow(() -> new NotFoundException("No existence quiz"));
        UserLo userLo = userLoRepository.findByLoIdAndUserId(1,userQuiz.getQuiz().getLearningObject().getId()).orElseThrow(() -> new NotFoundException("không thấy learning object của Quiz tương ứng"));

        System.out.println(updateQuizProgressReq.getHasDoneRight());

        if (userQuiz.getHasDoneRight() != updateQuizProgressReq.getHasDoneRight()){
           try {
               // save quiz's progress to user_quiz table
               userQuiz.setHasDoneRight(updateQuizProgressReq.getHasDoneRight());
               userQuiz.setCompletedTime(updateQuizProgressReq.getCompletedTime());
               userQuiz.saveModifiedTime("user");

               userQuizRepository.save(userQuiz);

               // change current exp in user_lo table
               if(updateQuizProgressReq.getHasDoneRight()) userLo.setCurrentExp(userLo.getCurrentExp()+userQuiz.getQuiz().getExpAmount());
               else userLo.setCurrentExp(userLo.getCurrentExp()-userQuiz.getQuiz().getExpAmount());
               userLo.saveModifiedTime("user");
               userLoRepository.save(userLo);

               return new UpdateQuizProgressResponse(userLo.getLearningObject().getTotalExpRequired(),userLo.getCurrentExp(),userLo.getLearningObject().getId());
           } catch (Exception ex) {
               throw new InternalServerException(ex.getMessage());
           }

        } else return new UpdateQuizProgressResponse(userLo.getLearningObject().getTotalExpRequired(),userLo.getCurrentExp(),userLo.getLearningObject().getId());
    }

    @Override
    @Transactional
    public QuizDetailsResponse create(QuizRequest quizRequest) {
        LearningObject learningObject = learningObjectRepository.findById(quizRequest.getLearningObjectId()).orElseThrow(() -> new NotFoundException("Learning Object is not exist"));
        QuizType quizType = quizTypeRepository.findById(quizRequest.getQuizTypeId()).orElseThrow(() -> new NotFoundException("Quiz Type is not exist"));

        try {

            Quiz quiz = quizMapper.toEntity(quizRequest);
            quiz.setQuizType(quizType);
            quiz.setLearningObject(learningObject);
            quiz.saveCreateTime("admin");
            quiz.saveModifiedTime("admin");
            quizRepository.save(quiz);


            // Not have tblUser
            // List id user
            // for(save(id user))

            /*
             * userID = 1
             */
            UserQuiz userQuiz = UserQuiz.builder().userId(1).quiz(quiz).hasDoneRight(false).build();
            userQuiz.saveCreateTime("admin");
            userQuiz.saveModifiedTime("admin");
            userQuizRepository.save(userQuiz);

            for (CreateAnswerRequest answerRequest : quizRequest.getAnswers()) {
                Answer answer = Answer.builder().answerBlank(answerRequest.getAnswerBlank()).answerOrder(answerRequest.getAnswerOrder()).correctConnectContent(answerRequest.getCorrectConnectContent()).content(answerRequest.getContent()).isRightOption(answerRequest.getIsRightOption()).quiz(quiz).build();
                answer.saveCreateTime("admin");
                answer.saveModifiedTime("admin");
                answerRepository.save(answer);
            }

            return this.getQuizById(quiz.getId());
        } catch(Exception e) {
            log.info(e.toString());
            throw new NotImplementedException("Not Implemented");
        }
    }

    @Override
    @Transactional
    public QuizRequest update(QuizRequest quizRequest, int id) {
        Quiz oldQuiz = quizRepository.findById(id).orElseThrow(() -> new NotFoundException("Quiz is not exist"));
        Quiz quiz = quizMapper.toEntity(quizRequest, oldQuiz);
        LearningObject learningObject = learningObjectRepository.findById(quizRequest.getLearningObjectId()).orElseThrow(() -> new NotFoundException("Learning Object is not exist"));
        QuizType quizType = quizTypeRepository.findById(quizRequest.getQuizTypeId()).orElseThrow(() -> new NotFoundException("Quiz Type is not exist"));

        try{
            quiz.setQuizType(quizType);
            quiz.setLearningObject(learningObject);
            quiz.saveModifiedTime("admin");
            quizRepository.save(quiz);

//            List<Answer> answers = answerRepository.findByQuizId(id);
//            for (CreateAnswerRequest answerRequest : quizRequest.getAnswers()) {
//                Answer answer = Answer.builder().answerBlank(answerRequest.getAnswerBlank()).answerOrder(answerRequest.getAnswerOrder()).correctConnectContent(answerRequest.getCorrectConnectContent()).content(answerRequest.getContent()).isRightOption(answerRequest.getIsRightOption()).quiz(quiz).build();
//                answer.saveModifiedTime("admin");
//                answerRepository.save(answer);
//            }

            return quizMapper.toRequest(quiz);
        }catch (Exception e) {
            log.info(e.toString());
            throw new NotImplementedException("Not Implemented");
        }
    }

    @Override
    @Transactional
    public void delete(int id){
        Quiz existingQuiz = quizRepository.findById(id).orElseThrow(() -> new NotFoundException("Quiz is not exist"));
        try {
            quizRepository.delete(existingQuiz);
        }catch(Exception e) {
            log.info(e.toString());
            throw new NotImplementedException("Not Implemented");
        }
    }

    public UserProgressResponse getUserProgress(Integer userid) {
        List<UserLoDTO> userLoList = userLoRepository.findByUserId(userid);
        List<UserQuizDTO> userQuizList = userQuizRepository.findCompletedQuizByUserId(userid);


        if(userLoList.size() == 0) throw new NotFoundException("User are not exist");
        if(userQuizList.size() == 0) throw new NotFoundException("No exist user's completed Quiz");

        return new UserProgressResponse(userid, userLoList, userQuizList);
    }

    /**
     * For testing
     */

    public void resetData() {
        List<UserQuiz> userQuizs = userQuizRepository.findAll();
        List<UserLo> userLos = userLoRepository.findAll();

        for (UserQuiz userQuiz: userQuizs) {
            userQuiz.setHasDoneRight(false);
            userQuiz.setModifiedBy("admin");
            userQuiz.setCompletedTime(null);

            userQuizRepository.save(userQuiz);
        }

        for (UserLo userLo: userLos) {
            userLo.setCurrentExp(0);
            userLo.setModifiedBy("admin");

            userLoRepository.save(userLo);
        }
    }

    public void generateQuiz() {
        Random random = new Random();
        quizTypeRepository.save(QuizType.builder().name("choice").hasOption(true).hasOrder(false).hasBlank(false).hasConnection(false).build());
        quizTypeRepository.save(QuizType.builder().name("matching").hasConnection(true).hasBlank(false).hasOption(false).hasOrder(false).build());
        quizTypeRepository.save(QuizType.builder().name("fill_into_blank").hasBlank(true).hasOption(false).hasOrder(false).hasConnection(false).build());
        quizTypeRepository.save(QuizType.builder().name("order").hasOrder(true).hasConnection(false).hasBlank(false).hasOption(false).build());


        for(int i =0;i<5;i++) {
            LearningObject learningObject = LearningObject.builder().level(random.nextInt()).title(random.ints(97,123).limit(10). collect (StringBuilder:: new ,StringBuilder::appendCodePoint,StringBuilder::append). toString ()).totalExpRequired(100).build();
            learningObjectRepository.save(learningObject);
            UserLo userLo = UserLo.builder().userId(1).currentExp(0).learningObject(learningObject).build();
            userLoRepository.save(userLo);
            for (int j =1;j<20;j++) {
                Quiz quiz = Quiz.builder().learningObject(learningObject).quizQuestion(random.ints(97,123).limit(10). collect (StringBuilder:: new ,StringBuilder::appendCodePoint,StringBuilder::append). toString ())
                    .explanation(random.ints(97,123).limit(10). collect (StringBuilder:: new ,StringBuilder::appendCodePoint,StringBuilder::append). toString ())
                    .expAmount(20).quizType(quizTypeRepository.findById((int) random.nextInt(4)+1).get()).build();
                quizRepository.save(quiz);
                userQuizRepository.save(UserQuiz.builder().quiz(quiz).userId(1).hasDoneRight(false).build());
                for(int k=1;k<4;k++){
                    if(quiz.getQuizType().getHasOption()) answerRepository.save(Answer.builder().quiz(quiz).content(random.ints(97,123).limit(10). collect (StringBuilder:: new ,StringBuilder::appendCodePoint,StringBuilder::append). toString ()).isRightOption(random.nextBoolean()).build());
                    if(quiz.getQuizType().getHasOrder()) answerRepository.save(Answer.builder().quiz(quiz).content(random.ints(97,123).limit(10). collect (StringBuilder:: new ,StringBuilder::appendCodePoint,StringBuilder::append). toString ()).answerOrder(random.nextInt(4)+1).build());
                    if(quiz.getQuizType().getHasBlank()) answerRepository.save(Answer.builder().quiz(quiz).answerBlank(random.ints(97,123).limit(10). collect (StringBuilder:: new ,StringBuilder::appendCodePoint,StringBuilder::append). toString ()).answerOrder(random.nextInt(4)+1).build());
                    if(quiz.getQuizType().getHasConnection()) answerRepository.save(Answer.builder().quiz(quiz).content(random.ints(97,123).limit(10). collect (StringBuilder:: new ,StringBuilder::appendCodePoint,StringBuilder::append). toString ()).correctConnectContent(random.ints(97,123).limit(10). collect (StringBuilder:: new ,StringBuilder::appendCodePoint,StringBuilder::append). toString ()).build());
                }
            }
        }
    }
}
