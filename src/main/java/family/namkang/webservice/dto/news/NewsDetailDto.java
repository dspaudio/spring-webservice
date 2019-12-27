package family.namkang.webservice.dto.news;


import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import family.namkang.webservice.domain.news.News;
import family.namkang.webservice.dto.EnumCodeNameDto;
import family.namkang.webservice.dto.news.comment.NewsCommentListDto;
import family.namkang.webservice.dto.news.file.NewsFileOwnedDto;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NewsDetailDto {
    private Long id;
    private EnumCodeNameDto category;
    private boolean noticeFlag;
    private boolean delFlag;
    private String title;
    private String content;
    private UserOwnedDto createdBy;
    private List<NewsFileOwnedDto> files;
    private List<NewsCommentListDto> comments;
    private LocalDateTime createdDate;
    private LocalDateTime modifiedDate;

    @Builder
    public NewsDetailDto(News entity) {
        this.id = entity.getId();
        this.category = new EnumCodeNameDto(entity.getCategory());
        this.noticeFlag = entity.isNoticeFlag();
        this.delFlag = entity.isTempFlag();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.files = entity.getNewsFiles().stream().map(NewsFileOwnedDto::new).collect(Collectors.toList());
        this.comments = entity.getNewsComment().stream().map(NewsCommentListDto::new).collect(Collectors.toList());
        this.createdDate = entity.getCreatedDate();
        this.modifiedDate = entity.getModifiedDate();
    }

}
