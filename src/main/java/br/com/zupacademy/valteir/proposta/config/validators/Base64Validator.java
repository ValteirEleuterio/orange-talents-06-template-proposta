package br.com.zupacademy.valteir.proposta.config.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class Base64Validator implements ConstraintValidator<IsBase64, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        try {
            if(Objects.isNull(value))
                return false;

            java.util.Base64.getDecoder().decode(value);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}
