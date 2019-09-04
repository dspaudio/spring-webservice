package family.namkang.webservice.web;

import java.io.IOException;

import javax.persistence.EntityNotFoundException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import family.namkang.webservice.common.exception.ExceptionEnums;
import family.namkang.webservice.common.exception.MessageException;
import family.namkang.webservice.dto.ExceptionEnumDto;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(MessageException.class)
    public ResponseEntity<ExceptionEnumDto> messageEx(MessageException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body( new ExceptionEnumDto(e.getExceptionDataEnum()) );
    }
	
    @ExceptionHandler(BindException.class)
    public ResponseEntity<ExceptionEnumDto> invalidArg(BindException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body( new ExceptionEnumDto(ExceptionEnums.WRONG_PARAMS) );
    }
	
    @ExceptionHandler(IOException.class)
    public ResponseEntity<ExceptionEnumDto> ioEx(MessageException e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body( new ExceptionEnumDto(ExceptionEnums.FILE_SYSTEM_ERROR) );
    }
	
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionEnumDto> entityNotFound(EntityNotFoundException e) {

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body( new ExceptionEnumDto(ExceptionEnums.NOT_FOUND) );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionEnumDto> defaultException(Exception e) {

		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body( new ExceptionEnumDto(ExceptionEnums.WRONG_PARAMS) );
    }
}
