package local.zcw.demo;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Created by zcw on 17/3/14.
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.FIELD})
@Constraint(validatedBy = MyCheckAnnotation.MyChecker.class)
public @interface MyCheckAnnotation {

    String message() default "the data is invalid";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    class MyChecker implements ConstraintValidator<MyCheckAnnotation, String> {
        public void initialize(MyCheckAnnotation myCheckAnnotation) {
            System.out.println("=======do initialize");
        }

        public boolean isValid(String str, ConstraintValidatorContext constraintValidatorContext) {
            if (str.contains("error")) {
                return false;
            }
            return true;
        }
    }
}
