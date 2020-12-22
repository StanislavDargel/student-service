package com.javaguru.studentservice.student.dto;


import java.util.Objects;

public class StudentResponse {

    private String id;
    private String name;
    private String lastName;
    private String quote;

    public StudentResponse() {
    }

    public StudentResponse(String id, String name, String lastName, String quote) {
        this.id = id;
        this.name = name;
        this.lastName = lastName;
        this.quote = quote;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

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

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StudentResponse that = (StudentResponse) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(lastName, that.lastName) &&
                Objects.equals(quote, that.quote);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, lastName, quote);
    }

    @Override
    public String toString() {
        return "StudentResponse{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", quote='" + quote + '\'' +
                '}';
    }
}
