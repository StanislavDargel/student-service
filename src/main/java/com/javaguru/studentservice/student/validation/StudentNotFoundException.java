package com.javaguru.studentservice.student.validation;

import com.javaguru.studentservice.core.exceptions.ItemNotFoundException;

public class StudentNotFoundException extends ItemNotFoundException {

    public StudentNotFoundException(String message) {
        super(StudentValidationMessages.STUDENT_NOT_FOUND);
    }
}
