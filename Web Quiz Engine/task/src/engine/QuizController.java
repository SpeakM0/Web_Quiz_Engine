package engine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class QuizController {

    @Autowired
    QuizRepository quizList;

    Long quizIdCounter = 0L;

    @GetMapping(value = "/api/quizzes")
    public List<Quiz> getAllQuizzes() {
        return (List<Quiz>) quizList.findAll();
    }

    @GetMapping(value = "/api/quizzes/{id}")
    public Quiz getQuizById(@PathVariable Integer id) throws ResponseStatusException{
        return quizList.findById(Long.valueOf(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found by this id")
        );
    }

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public SolutionAnswer trySolution(@RequestBody UserAnswer answer, @PathVariable("id") int id){
        Quiz currentQuiz = quizList.findById(Long.valueOf(id)).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found by this id")
        );

        if (currentQuiz.isSolvedBy(answer.getAnswer())) {
            return new SolutionAnswer(true, "Congratulations!");
        } else {
            return new SolutionAnswer(false, "Try again!");
        }
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Quiz createQuiz(@RequestBody @Valid Quiz quiz) {
        quiz.setId(quizIdCounter);
        quizList.saveAndFlush(quiz);
        return quizList.findById(Long.valueOf(quizIdCounter++)).get();
    }

}
