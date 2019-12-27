package family.namkang.webservice.dto.user;

import family.namkang.webservice.domain.user.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserOwnedDto {
    private Long id;
    private String username;

    @Builder
    public UserOwnedDto(User entity) {
        this.id = entity.getId();
        this.username = entity.getUsername();
    }

}
