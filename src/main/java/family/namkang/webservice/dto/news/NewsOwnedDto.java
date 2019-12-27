package family.namkang.webservice.dto.news;


import java.time.LocalDateTime;

import family.namkang.webservice.domain.news.News;
import family.namkang.webservice.dto.EnumCodeNameDto;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NewsOwnedDto {
    private Long id;
    private EnumCodeNameDto category;
    private boolean delFlag;
    private String title;
    private UserOwnedDto createdBy;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public NewsOwnedDto(News entity) {
        this.id = entity.getId();
        this.category = new EnumCodeNameDto( entity.getCategory() );
        this.delFlag = entity.isTempFlag();
        this.title = entity.getTitle();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
