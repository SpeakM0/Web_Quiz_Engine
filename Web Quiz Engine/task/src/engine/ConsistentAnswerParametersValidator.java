package engine;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConsistentAnswerParametersValidator
        implements ConstraintValidator<ConsistentAnswerParameters, Quiz> {

    @Override
    public void initialize(ConsistentAnswerParameters constraintAnnotation) {

    }

    @Override
    public boolean isValid(Quiz value, ConstraintValidatorContext context) {
        Integer[] answer = value.answers();
        String[] options = value.getOptions();
        if (answer.length > options.length) {
            return false;
        } else if (answer.length == 0) {
            return true;
        } else {
            for (Integer i : answer) {
                if (i > options.length - 1) {
                    return false;
                }
            }
        }
        return true;
    }

}
