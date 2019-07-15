package family.namkang.webservice.domain.post;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.domain.user.User;
import family.namkang.webservice.dto.post.PostsSaveRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class PostComment extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postId")
    private Post post;

    @Column(nullable = false, length = 4000)
    private String content;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private User createdBy; 

    @Builder
    public PostComment(Post post, String content, User createdBy) {
        this.post = post;
        this.content = content;
        this.createdBy = createdBy;
    }
    
    public void update(String content) {
        this.content = content;
    }

}
