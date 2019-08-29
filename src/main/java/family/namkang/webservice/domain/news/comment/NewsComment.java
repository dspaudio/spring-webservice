package family.namkang.webservice.domain.news.comment;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.base.BaseUserCreatedEntity;
import family.namkang.webservice.domain.news.News;
import family.namkang.webservice.dto.news.comment.NewsCommentSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class NewsComment extends BaseUserCreatedEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long newsId;
    @ManyToOne
    @JoinColumn(name = "newsId", insertable = false, updatable = false)
    private News news;

    @Column(nullable = false, length = 4000)
    private String content;

    @Builder
    public NewsComment(Long newsId, String content, Long createdById) {
        this.newsId = newsId;
        this.content = content;
        this.createdById = createdById;
    }
    
    public void update(NewsCommentSaveDto dto) {
        this.content = dto.getContent();
    }

}
