package com.tsk.ecommerce.services.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tsk.ecommerce.dtos.responses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SecurityAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        final ObjectMapper mapper = new ObjectMapper();
        httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        httpServletResponse.setContentType(MediaType.APPLICATION_JSON_VALUE);
        mapper.writeValue(httpServletResponse.getOutputStream(), ResponseFactory.forbidden(e.getMessage()));
    }
}
