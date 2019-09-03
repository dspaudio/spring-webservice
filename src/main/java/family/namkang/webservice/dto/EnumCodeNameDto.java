package family.namkang.webservice.dto;

import family.namkang.webservice.common.EnumCodeName;
import lombok.Builder;
import lombok.Getter;

@Getter
public class EnumCodeNameDto {
    private String code;
    private String name;

    @Builder
    public EnumCodeNameDto(EnumCodeName codeName) {
    	this.code = codeName.getCode();
        this.name = codeName.getName();
    }

}
