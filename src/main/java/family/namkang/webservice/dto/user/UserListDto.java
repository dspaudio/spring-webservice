package family.namkang.webservice.dto.user;

import java.time.LocalDateTime;

import family.namkang.webservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserListDto {
    private Long id;
    private String userName;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public UserListDto(User entity) {
        this.id = entity.getId();
        this.userName = entity.getUserName();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
