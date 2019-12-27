package family.namkang.webservice.dto.news;

import java.time.LocalDateTime;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.news.News;
import family.namkang.webservice.dto.EnumCodeNameDto;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NewsListDto {
    private Long id;
    private EnumCodeNameDto category;
    private boolean noticeFlag;
    private boolean delFlag;
    private String title;
    private UserOwnedDto createdBy;
    private Integer filesCount;
    private Integer commentCount;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public NewsListDto(News entity) {
        this.id = entity.getId();
        this.category = new EnumCodeNameDto( entity.getCategory() );
        this.noticeFlag = entity.isNoticeFlag();
        this.delFlag = entity.isTempFlag();
        this.title = entity.getTitle();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.filesCount = entity.getNewsFiles().size();
        this.commentCount = entity.getNewsComment().size();
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
