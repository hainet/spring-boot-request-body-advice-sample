package com.hainet.spring.boot.request.body.advice.sample.web;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(assignableTypes = PersonController.class)
public class HttpResponseAdvice implements ResponseBodyAdvice {

    private static final Logger log = LoggerFactory.getLogger(HttpRequestAdvice.class);

    private final ObjectMapper mapper;

    public HttpResponseAdvice(final ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean supports(
            final MethodParameter returnType,
            final Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(
            final Object body,
            final MethodParameter returnType,
            final MediaType selectedContentType,
            final Class selectedConverterType,
            final ServerHttpRequest request,
            final ServerHttpResponse response) {
        try {
            log.info(this.mapper.writeValueAsString(body));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return body;
    }
}
