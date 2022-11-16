package de.nimarion.spring.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ISBNValidator implements ConstraintValidator<ISBN, String>{

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        org.apache.commons.validator.routines.ISBNValidator ibanValidator = new org.apache.commons.validator.routines.ISBNValidator();
        System.out.println("IBANValidator.isValid() " + value);
        System.out.println("IBANValidator.isValid() " + ibanValidator.isValid(value));
        return ibanValidator.isValid(value);
    }
    
}
