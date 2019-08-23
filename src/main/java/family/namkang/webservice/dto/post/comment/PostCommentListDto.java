package family.namkang.webservice.dto.post.comment;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.post.comment.PostComment;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCommentListDto {

    private Long id;
    private Long postId;
    private String content;
    private UserOwnedDto createdBy; 
    private String createdDate;
    private String modifiedDate;

    @Builder
    public PostCommentListDto(PostComment entity) {
        this.id = entity.getId();
        this.postId = entity.getPostId();
        this.content = entity.getContent();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }
    
}
