package family.namkang.webservice.domain.post.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.BaseUserCreatedEntity;
import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.dto.post.comment.PostCommentSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class PostComment extends BaseUserCreatedEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long postId;
    @ManyToOne
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private Post post;

    @Column(nullable = false, length = 4000)
    private String content;

    @Builder
    public PostComment(Long postId, String content, Long createdById) {
        this.postId = postId;
        this.content = content;
        this.createdById = createdById;
    }
    
    public void update(PostCommentSaveDto dto) {
        this.content = dto.getContent();
    }

}
