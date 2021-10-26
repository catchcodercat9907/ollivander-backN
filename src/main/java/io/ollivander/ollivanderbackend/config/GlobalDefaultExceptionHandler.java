package io.ollivander.ollivanderbackend.config;

import io.ollivander.ollivanderbackend.common.ErrorInfo;
import io.ollivander.ollivanderbackend.model.dto.ResponseObject;
import org.apache.commons.lang.WordUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * This class simply handles the following default exceptions:
 * NoSuchRequestHandlingMethodException 404 (Not Found)
 * HttpRequestMethodNotSupportedException 405 (Method Not Allowed)
 * HttpMediaTypeNotSupportedException 415 (Unsupported Media Type)
 * MissingServletRequestParameterException 400 (Bad Request)
 * ServletRequestBindingException 400 (Bad Request)
 * ConversionNotSupportedException 500 (Internal Server Error)
 * TypeMismatchException 400 (Bad Request) HttpMessageNotReadableException
 * 400(Bad Request) HttpMessageNotWritableException 500 (Internal Server Error)
 * MethodArgumentNotValidException 400 (Bad Request)
 * MissingServletRequestPartException 400 (Bad Request)
 */
@ControllerAdvice
public class GlobalDefaultExceptionHandler extends ResponseEntityExceptionHandler {

    private final static Logger logger = LoggerFactory.getLogger(GlobalDefaultExceptionHandler.class);

    private ErrorInfo errorInfo;
    private ResponseObject<Object> responseObject = new ResponseObject<>();

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public ResponseEntity<Object> handleCommonException(Exception ex) {
        logger.error("-------> handleCommonException", ex);
        ErrorInfo errorInfo;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        if (ex instanceof MultipartException) {
            errorInfo = new ErrorInfo(ErrorInfo.UNKNOWN_ERROR_CODE, ex.getMessage());

        } else if (ex instanceof AccessDeniedException) {
            errorInfo = ErrorInfo.ACCESS_DENIED_ERROR;
            status = HttpStatus.FORBIDDEN;
        } else {
            errorInfo = new ErrorInfo(ErrorInfo.UNKNOWN_ERROR_CODE, ex.getMessage());
            status = HttpStatus.INTERNAL_SERVER_ERROR;
        }

        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, new HttpHeaders(), status);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers, HttpStatus status,
                                                         WebRequest request) {
        logger.error("-------> handleBindException {}", ex.getMessage());
        errorInfo = new ErrorInfo(ErrorInfo.FIELD_ERROR_CODE,  toArrays(ex.getBindingResult()));

        responseObject.setError(errorInfo);

        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.info("-------> handleConversionNotSupported");

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body, HttpHeaders headers,
                                                             HttpStatus status, WebRequest request) {

        logger.info("-------> handleExceptionInternal");

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.info("-------> handleHttpMediaTypeNotAcceptable");

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.info("-------> handleHttpMediaTypeNotSupported");

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.error("-------> handleHttpMessageNotReadable", ex);

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {
        logger.error("-------> handleHttpMessageNotWritable");
        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        logger.error("request {}", ((ServletWebRequest) request).getRequest().getRequestURI());
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
                                                                         HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.error("-------> handleHttpRequestMethodNotSupported", ex);

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers, HttpStatus status, WebRequest request) {

        errorInfo = new ErrorInfo(ErrorInfo.FIELD_ERROR_CODE, toArrays(ex.getBindingResult()));

        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, HttpStatus.OK);
    }

    private String[] toArrays(BindingResult bindingResult){
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        List<String> messages = new ArrayList<>();

        for (FieldError fieldError : fieldErrors) {

            String message = String.format("%s %s", WordUtils.capitalize(fieldError.getField()),
                    fieldError.getDefaultMessage());

            messages.add(message);
        }
        return messages.toArray(new String[0]);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.info("-------> handleMissingServletRequestParameter", ex);

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(MissingServletRequestPartException ex,
                                                                     HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.info("-------> handleMissingServletRequestPart", ex);

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
            NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.info("-------> handleNoSuchRequestHandlingMethod");

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
                                                                          HttpHeaders headers, HttpStatus status, WebRequest request) {

        logger.info("-------> handleServletRequestBindingException");

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex, HttpHeaders headers,
                                                        HttpStatus status, WebRequest request) {

        logger.info("-------> handleTypeMismatch");

        errorInfo = new ErrorInfo(status.value(), ex.getMessage());
        responseObject.setError(errorInfo);
        return new ResponseEntity<>(responseObject, headers, status);
    }
}
