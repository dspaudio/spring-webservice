package family.namkang.webservice.dto.user;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserListDto {
    private Long id;
    private String userName;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public UserListDto(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
