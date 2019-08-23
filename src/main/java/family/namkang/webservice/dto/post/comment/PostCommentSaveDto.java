package family.namkang.webservice.dto.post.comment;

import family.namkang.webservice.domain.post.comment.PostComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostCommentSaveDto {

    private Long id;
    private Long postId;
    private String content;
    private Long createdById; 

    @Builder
    public PostCommentSaveDto(Long id, Long postId, String content, Long createdById) {
        this.id = id;
        this.postId = postId;
        this.content = content;
        this.createdById = createdById;
    }
    
    public PostComment toEntity() {
        return PostComment.builder()
                .postId(postId)
                .content(content)
                .createdById(createdById)
                .build();
    }
    
}
