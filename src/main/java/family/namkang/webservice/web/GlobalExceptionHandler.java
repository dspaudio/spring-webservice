package family.namkang.webservice.web;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import family.namkang.webservice.common.exception.ExceptionDataEnums;
import family.namkang.webservice.common.exception.MessageException;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(MessageException.class)
    public ResponseEntity<String> messageEx(MessageException e) {
    	HttpStatus status = HttpStatus.BAD_REQUEST;
    	
    	int code = e.getExceptionCode();
    	if (code == ExceptionDataEnums.NOT_AUTHORIZED.getCode()) {
			status = HttpStatus.FORBIDDEN; 
    	} else if (code == ExceptionDataEnums.WRONG_PARAMS.getCode()) {
			status = HttpStatus.BAD_REQUEST; 
    	} else if (code == ExceptionDataEnums.FILE_SYSTEM_ERROR.getCode()) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;  
    	} 

		return ResponseEntity.status(status)
			       .contentType(MediaType.TEXT_PLAIN)
			       .body(e.getMessage());
    }
	
    @ExceptionHandler(IOException.class)
    public ResponseEntity<String> ioEx(MessageException e) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			       .contentType(MediaType.TEXT_PLAIN)
			       .body("파일시스템 에러가 발생했습니다.");
    }
	
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> entityNotFound(EntityNotFoundException e) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
			       .contentType(MediaType.TEXT_PLAIN)
			       .body("요청하신 데이터를 찾을 수 없습니다.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> defaultException(Exception e) {

		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
			       .contentType(MediaType.TEXT_PLAIN)
			       .body("처리중 서버에서 에러가 발생했습니다.");
    }
}
