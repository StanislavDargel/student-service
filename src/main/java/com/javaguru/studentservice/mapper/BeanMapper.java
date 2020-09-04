package com.javaguru.studentservice.mapper;

import com.javaguru.studentservice.student.domain.StudentEntity;
import com.javaguru.studentservice.student.dto.StudentCreateRequest;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BeanMapper {

    public StudentEntity toEntity(StudentCreateRequest request) {
        StudentEntity entity = new StudentEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setName(request.getName());
        entity.setLastName(request.getLastName());
        return entity;
    }
}
