package com.interview_task.textsearchservice.validator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = FilePathExistValidator.class)
@Target( { ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
public @interface FileExist {

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
    String message() default ("File with this path not exist");
}
