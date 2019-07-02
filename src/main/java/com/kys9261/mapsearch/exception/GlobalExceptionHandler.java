package com.kys9261.mapsearch.exception;

import com.kys9261.mapsearch.model.response.ApiResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.server.ServerErrorException;

@Log4j2
@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ApiResult> newResponse(Throwable throwable, HttpStatus status) {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        return new ResponseEntity<>(new ApiResult(throwable, status), headers, status);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentTypeMismatchException.class, IllegalArgumentException.class})
    public ResponseEntity<ApiResult> badRequest(Exception exception) {
        log.error(exception.getMessage(), exception);
        return newResponse(exception, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseEntity<ApiResult> unauthrized(UnauthorizedException exception) {
        log.error(exception.getMessage(), exception);
        return newResponse(exception, HttpStatus.UNAUTHORIZED);
    }

    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ApiResult> forbidden(AccessDeniedException exception) {
        log.error(exception.getMessage(), exception);
        return newResponse(exception, HttpStatus.FORBIDDEN);
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ApiResult> notFound(NotFoundException exception) {
        log.error(exception.getMessage(), exception);
        return newResponse(exception, HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerErrorException.class)
    public ResponseEntity<ApiResult> internalServerError(ServerErrorException exception) {
        log.error(exception.getMessage(), exception);
        return newResponse(exception, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
