package engine;


import org.springframework.lang.Nullable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Arrays;

@ConsistentAnswerParameters
@Entity
public class Quiz {

    @Id
    private Long id;
    @NotBlank(message = "Title of the quiz is not specified!")
    private String title;
    @NotBlank(message = "Text of the quiz is not specified!")
    private String text;
    @Size(min=2, message = "Quiz must contain at least two options.")
    private String[] options;
    @Nullable
    private Integer[] answer;

    public Quiz(){}

    public Quiz(String title, String text, String[] options, Integer[] answer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.answer = answer;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String[] getOptions() {
        return options;
    }

    public void setOptions(String[] options) {
        this.options = options;
    }

    public void setAnswer(Integer[] answer) {
        this.answer = answer;
    }

    public boolean isSolvedBy(@Nullable Integer[] userAnswer) {
        if (answer == null) {
            answer = new Integer[0];
        }
        if (userAnswer == null) {
            userAnswer = new Integer[0];
        }
        return Arrays.equals(answer, userAnswer);
    }

    public Integer[] answers() {
        return answer;
    }

}
