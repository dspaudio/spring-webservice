package family.namkang.webservice.dto.news.comment;

import family.namkang.webservice.domain.news.comment.NewsComment;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsCommentSaveDto {

    private Long id;
    private Long newsId;
    private String content;
    private Long createdById; 

    @Builder
    public NewsCommentSaveDto(Long id, Long newsId, String content, Long createdById) {
        this.id = id;
        this.newsId = newsId;
        this.content = content;
        this.createdById = createdById;
    }
    
    public NewsComment toEntity() {
        return NewsComment.builder()
                .newsId(newsId)
                .content(content)
                .createdById(createdById)
                .build();
    }
    
}
