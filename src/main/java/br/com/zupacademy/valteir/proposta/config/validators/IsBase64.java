package br.com.zupacademy.valteir.proposta.config.validators;

import org.hibernate.validator.constraints.CompositionType;
import org.hibernate.validator.constraints.ConstraintComposition;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.NotBlank;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {Base64Validator.class})
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@ConstraintComposition(CompositionType.OR)
public @interface IsBase64 {
    String message() default "Deve estar criptografado em base64";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
