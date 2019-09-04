package family.namkang.webservice.dto;

import family.namkang.webservice.common.EnumCodeName;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EnumCodeNameDto {
    private String name;
    private String code;

    @Builder
    public EnumCodeNameDto(EnumCodeName codeName) {
        this.name = codeName.getName();
    	this.code = codeName.getCode();
    }

}
