package family.namkang.webservice.common.exception;

import lombok.Getter;

public enum ExceptionEnums {

    NOT_AUTHORIZED(1001, "작업을 실행할 수 있는 권한이 없습니다.")
    , WRONG_PARAMS(1002,"요청인자가 올바르지 않습니다.")
    , FILE_SYSTEM_ERROR(1003,"파일 처리중 예상치 못한 에러가 발생했습니다.")
    , NOT_FOUND(1004,"요청하신 데이터가 존재하지 않습니다.")
    , LAST_EX(9999,"자리잡기용 의미없음")
    ;

    @Getter
    private int code;
    @Getter
    private String message;

    public String getName() {
    	return this.name();
    }
    
    ExceptionEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
