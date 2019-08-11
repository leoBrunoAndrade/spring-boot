package br.com.leobruno.handler;

import br.com.leobruno.erros.EntityConflictException;
import br.com.leobruno.erros.EntityNotFoundExceptionApi;
import br.com.leobruno.erros.ErrorDetail;
import br.com.leobruno.erros.ObjectError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatus status,
                                                                  WebRequest request) {

        ErrorDetail errorDetail = new ErrorDetail.BuilderErrorDetail(status.value()).
                message("invalid fields")
                .object(ex.getBindingResult().getObjectName())
                .status(status.getReasonPhrase())
                .errors(getErrors(ex))
                .build();
        return new ResponseEntity<>(errorDetail,status);
    }

    /*private ErrorDetail getErrorDetail (MethodArgumentNotValidException ex, HttpStatus status, List<ObjectError> errors){
        return new ErrorDetail("Campos Invalid", status.value(),status.getReasonPhrase(),
                ex.getBindingResult().getObjectName(),errors);
    }*/

    private List<ObjectError> getErrors (MethodArgumentNotValidException ex){
        return ex.getBindingResult().getFieldErrors().stream().map(fieldError ->
                new ObjectError(fieldError.getDefaultMessage(),fieldError.getField())).collect(Collectors.toList());
    }

    @ExceptionHandler(EntityNotFoundExceptionApi.class)
    public ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundExceptionApi entityNotFoundEx) {
        ErrorDetail errorDetail = new ErrorDetail.BuilderErrorDetail(HttpStatus.NOT_FOUND.value()).
                message(entityNotFoundEx.getMessage())
                .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();

        return new ResponseEntity<>(errorDetail,HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(EntityConflictException.class)
    public ResponseEntity<Object> handlerEntityConflictException(EntityConflictException entityConflictEx) {
        ErrorDetail errorDetail = new ErrorDetail.BuilderErrorDetail(HttpStatus.CONFLICT.value()).
                message(entityConflictEx.getMessage())
                .status(HttpStatus.CONFLICT.getReasonPhrase())
                .build();

        return new ResponseEntity<>(errorDetail,HttpStatus.CONFLICT);
    }












}
