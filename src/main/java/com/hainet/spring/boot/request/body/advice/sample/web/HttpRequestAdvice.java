package com.hainet.spring.boot.request.body.advice.sample.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;

@RestControllerAdvice
public class HttpRequestAdvice extends RequestBodyAdviceAdapter {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestAdvice.class);

    private final ObjectMapper mapper;

    public HttpRequestAdvice(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean supports(
            final MethodParameter methodParameter,
            final Type targetType,
            final Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object afterBodyRead(
            final Object body,
            final HttpInputMessage inputMessage,
            final MethodParameter parameter,
            final Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        try {
            log.info(this.mapper.writeValueAsString(body));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return body;
    }
}
