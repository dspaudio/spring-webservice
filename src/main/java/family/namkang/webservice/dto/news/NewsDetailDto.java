package family.namkang.webservice.dto.news;


import java.util.List;
import java.util.stream.Collectors;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
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
    private Boolean noticeFlag;
    private Boolean delFlag;
    private String title;
    private String content;
    private UserOwnedDto createdBy;
    private List<NewsFileOwnedDto> files;
    private List<NewsCommentListDto> comments;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public NewsDetailDto(News entity) {
        this.id = entity.getId();
        this.category = new EnumCodeNameDto(entity.getCategory());
        this.noticeFlag = entity.getNoticeFlag();
        this.delFlag = entity.getTempFlag();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.files = entity.getNewsFiles().stream().map(NewsFileOwnedDto::new).collect(Collectors.toList());
        this.comments = entity.getNewsComment().stream().map(NewsCommentListDto::new).collect(Collectors.toList());
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
