package com.javaguru.studentservice.student.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.Objects;

public class StudentCreateRequest {

    @NotEmpty
    @Size(min = 3, max = 30, message = "length must be in range(3-32)")
    @Pattern(regexp = "^[a-zA-Z-]+$", message = "word may contain only letters")
    private String name;

    @NotEmpty
    @Size(min = 3, max = 30, message = "length must be in range(3-32)")
    @Pattern(regexp = "^[a-zA-Z-]+$", message = "word may contain only letters")
    private String lastName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentCreateRequest that = (StudentCreateRequest) o;
        return Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, lastName);
    }

    @Override
    public String toString() {
        return "StudentCreateRequest{" +
                "name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
