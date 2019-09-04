package family.namkang.webservice.dto;

import family.namkang.webservice.common.exception.ExceptionEnums;
import lombok.Builder;
import lombok.Getter;

@Getter
public class ExceptionEnumDto {
    private String name;
    private int code;
    private String message;

    @Builder
    public ExceptionEnumDto(ExceptionEnums exEnum) {
        this.name = exEnum.getName();
    	this.code = exEnum.getCode();
    	this.message = exEnum.getMessage();
    }

}
