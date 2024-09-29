package com.example.demo.Domain.Shared.Annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LogActivity {
    /**
     * Type of database operation. Can be Insert,Update,Delete.
     */
    DBOperation operation();
}

