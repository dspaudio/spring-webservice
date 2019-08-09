package family.namkang.webservice.domain.post.comment;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.domain.post.file.PostFile;
import family.namkang.webservice.domain.user.User;
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

    private Long postId;
    @ManyToOne
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private Post post;

    @Column(nullable = false, length = 4000)
    private String content;

    private Long createdById;
    @ManyToOne
    @JoinColumn(name = "createdById", insertable = false, updatable = false)
    private User createdBy; 

    @Builder
    public PostComment(Long postId, String content, Long createdById) {
        this.postId = postId;
        this.content = content;
        this.createdById = createdById;
    }
    
    public void update(String content) {
        this.content = content;
    }

}
