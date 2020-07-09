package engine;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;

@Constraint(validatedBy = ConsistentAnswerParametersValidator.class)
@Target({ TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ConsistentAnswerParameters {
    String message() default
            "Answer indexes must correspond to options indexes";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    /*String[] answer();
    String[] options();
     */
}
