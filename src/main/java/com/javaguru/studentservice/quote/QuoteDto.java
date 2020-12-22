package com.javaguru.studentservice.quote;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class QuoteDto {
    @JsonProperty("_id")
    private String secondExternalId;
    @JsonProperty("en")
    private String quote;
    private String author;
    @JsonProperty("id")
    private String externalId;

    public QuoteDto() {
    }

    public QuoteDto(String secondExternalId, String quote, String author, String externalId) {
        this.secondExternalId = secondExternalId;
        this.quote = quote;
        this.author = author;
        this.externalId = externalId;
    }

    public String getSecondExternalId() {
        return secondExternalId;
    }

    public void setSecondExternalId(String secondExternalId) {
        this.secondExternalId = secondExternalId;
    }

    public String getQuote() {
        return quote;
    }

    public void setQuote(String quote) {
        this.quote = quote;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuoteDto quotesDto = (QuoteDto) o;
        return Objects.equals(secondExternalId, quotesDto.secondExternalId) &&
                Objects.equals(quote, quotesDto.quote) &&
                Objects.equals(author, quotesDto.author) &&
                Objects.equals(externalId, quotesDto.externalId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(secondExternalId, quote, author, externalId);
    }

    @Override
    public String toString() {
        return "QuoteDto{" +
                "secondExternalId='" + secondExternalId + '\'' +
                ", quote='" + quote + '\'' +
                ", author='" + author + '\'' +
                ", externalId='" + externalId + '\'' +
                '}';
    }
}
