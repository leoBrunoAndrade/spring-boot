package br.com.leobruno.handler;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import br.com.leobruno.erros.EntityConflictException;
import br.com.leobruno.erros.EntityNotFoundExceptionApi;
import br.com.leobruno.erros.ErrorDetail;
import br.com.leobruno.erros.ObjectError;
import br.com.leobruno.validate.InvalidEnumException;

@RestControllerAdvice
public class RestExceptionsHandler extends ResponseEntityExceptionHandler {

	@Autowired
	private MessageSource messageSource;

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail.BuilderErrorDetail(status.value()).message("invalid fields")
				.object(ex.getBindingResult().getObjectName()).status(status.getReasonPhrase()).errors(getErrors(ex))
				.build();
		return new ResponseEntity<>(errorDetail, status);
	}

	private List<ObjectError> getErrors(MethodArgumentNotValidException ex) {
		return ex.getBindingResult().getFieldErrors().stream()
				.map(fieldError -> new ObjectError(
						messageSource.getMessage(fieldError, LocaleContextHolder.getLocale()), fieldError.getField()))
				.collect(Collectors.toList());
	}

	@Override
	protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetail errorDetail = new ErrorDetail.BuilderErrorDetail(status.value()).message(ex.getMessage())
				.object(ex.getMethod()).status(status.getReasonPhrase()).build();
		return new ResponseEntity<>(errorDetail, status);
	}

	@ExceptionHandler(EntityNotFoundExceptionApi.class)
	public ResponseEntity<Object> handlerEntityNotFoundException(EntityNotFoundExceptionApi entityNotFoundEx) {
		ErrorDetail errorDetail = new ErrorDetail.BuilderErrorDetail(HttpStatus.NOT_FOUND.value())
				.message(entityNotFoundEx.getMessage()).status(HttpStatus.NOT_FOUND.getReasonPhrase()).build();

		return new ResponseEntity<>(errorDetail, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler(EntityConflictException.class)
	public ResponseEntity<Object> handlerEntityConflictException(EntityConflictException entityConflictEx) {
		ErrorDetail errorDetail = new ErrorDetail.BuilderErrorDetail(HttpStatus.CONFLICT.value())
				.message(entityConflictEx.getMessage()).status(HttpStatus.CONFLICT.getReasonPhrase()).build();

		return new ResponseEntity<>(errorDetail, HttpStatus.CONFLICT);
	}
	
	@ExceptionHandler(InvalidEnumException.class)
	public ResponseEntity<Object> handlerInvalidEnumException(InvalidEnumException ex){
		ErrorDetail errorDetail = new ErrorDetail.BuilderErrorDetail(HttpStatus.NOT_FOUND.value())
				.message(ex.getMessage())
				.status(HttpStatus.NOT_FOUND.getReasonPhrase())
				.build();
		return new ResponseEntity<Object>(errorDetail,HttpStatus.NOT_FOUND);
	}
	
}
