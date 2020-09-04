package com.javaguru.studentservice.quote;

import com.javaguru.studentservice.quote.validation.QuoteValidationMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class QuoteService {

    private static final Logger logger = LoggerFactory.getLogger(QuoteService.class);
    private final RestTemplate restTemplate;
    private final QuoteProperties quoteProperties;

    public QuoteService(RestTemplate restTemplate, QuoteProperties quoteProperties) {
        this.restTemplate = restTemplate;
        this.quoteProperties = quoteProperties;
    }

    public QuoteDto findRandomQuote() {
        QuoteDto quote = restTemplate.getForObject(quoteProperties.getUrls().getRandomQuote(), QuoteDto.class);
        logger.info("Received quote response: {}", quote);
        if (quote == null) {
            throw new IllegalStateException(QuoteValidationMessages.QUOTE_RETRIEVE_FAILURE);
        }
        return quote;
    }
}
