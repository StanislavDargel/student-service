package com.javaguru.studentservice.student.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.Objects;

public class StudentCreateRequest {

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z-]{3,32}",message="length must be in range(3-32) and may contain only letters")
    private String name;

    @NotEmpty
    @Pattern(regexp="^[a-zA-Z-]{3,32}",message="length must be in range(3-32) and may contain only letters")
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
