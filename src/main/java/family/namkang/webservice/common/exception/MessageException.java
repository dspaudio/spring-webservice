package family.namkang.webservice.common.exception;

import lombok.Getter;

public class MessageException extends Exception {
	
    @Getter
	private ExceptionDataEnums exceptionDataEnum;

	public MessageException(ExceptionDataEnums exEnum) {
		super( exEnum.getMessage() );
		exceptionDataEnum = exEnum;
	}
	
	public int getExceptionCode() {
		return this.exceptionDataEnum.getCode();
	}
}
