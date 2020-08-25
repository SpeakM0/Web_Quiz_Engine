package engine;

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
    Map<Integer, Quiz> quizList = new HashMap<>();
    Integer quizIdCounter = 0;

    @GetMapping(value = "/api/quizzes")
    public List<Quiz> getQuizzes() {
        return new ArrayList<>(quizList.values());
    }

    @GetMapping(value = "/api/quizzes/{id}")
    public Quiz getQuizById(@PathVariable Integer id) throws ResponseStatusException{
        if (quizList.get(id) != null){
            return quizList.get(id);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");
        }

    }

    @PostMapping(value = "/api/quizzes/{id}/solve", consumes = "application/json")
    public SolutionAnswer trySolution(@RequestBody UserAnswer answer, @PathVariable("id") int id){
        if (quizList.get(id) == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Quiz not found");
        } else if (quizList.get(id).isSolvedBy(answer.getAnswer())) {
            return new SolutionAnswer(true, "Congratulations!");
        } else {
            return new SolutionAnswer(false, "Try again!");
        }
    }

    @PostMapping(value = "/api/quizzes", consumes = "application/json")
    public Quiz createQuiz(@RequestBody @Valid Quiz quiz) {
        quiz.setId(quizIdCounter);
        quizList.put(quizIdCounter, quiz);
        return quizList.get(quizIdCounter++);
    }

}
