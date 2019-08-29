package family.namkang.webservice.dto.news.comment;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.news.comment.NewsComment;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsCommentListDto {

    private Long id;
    private Long newsId;
    private String content;
    private UserOwnedDto createdBy; 
    private String createdDate;
    private String modifiedDate;

    @Builder
    public NewsCommentListDto(NewsComment entity) {
        this.id = entity.getId();
        this.newsId = entity.getNewsId();
        this.content = entity.getContent();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }
    
}
