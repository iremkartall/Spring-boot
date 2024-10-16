package com.luv2code.springboot.mvc.validationdemo.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CourseCodeConstraintValidator.class)
@Target({ElementType.METHOD,ElementType.FIELD})     //Oluşturduğumuz anotasyonları nereye uyguluyoruz
@Retention(RetentionPolicy.RUNTIME)    //İşaretlenen anotasyonlar ne kadar süreyle saklanacak ve ya kullanılacak
public @interface CourseCode {
    public String value() default "LUV";
    public String message() default "must start with LUV";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default {};
}



