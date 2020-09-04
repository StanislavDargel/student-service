package com.javaguru.studentservice.student;

import com.javaguru.studentservice.student.domain.StudentEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class StudentRepositoryIT {

    @Autowired
    private StudentRepository victim;

    @Test
    void shouldSaveStudents() {
        victim.save(studentEntity());
    }

    @Test
    void shouldFindById() {
        StudentEntity createdEntity = studentEntity();
        victim.save(createdEntity);

        Optional<StudentEntity> foundedEntity = victim.findById(createdEntity.getId());

        assertThat(foundedEntity).contains(createdEntity);
    }

    private StudentEntity studentEntity() {
        StudentEntity entity = new StudentEntity();
        entity.setId(UUID.randomUUID().toString());
        entity.setQuote("Repo_Quote");
        entity.setName("Repo_Name");
        entity.setLastName("Repo_Last_Name");
        return entity;
    }
}