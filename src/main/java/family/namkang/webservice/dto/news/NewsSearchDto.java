package family.namkang.webservice.dto.news;


import family.namkang.webservice.domain.news.News;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsSearchDto {
    private Long userId;
    private News.Category category;
    private Boolean noticeFlag;
    private Boolean delFlag;
    private String title;
    private String content;
    private String titleContent;
    private String userName;
    private String createdAfter;
    private String createdBefore;

    @Builder
    public NewsSearchDto(Long userId, News.Category category, Boolean noticeFlag, Boolean delFlag, String title, String content, String titleContent, String userName, String createdAfter, String createdBefore) {
        this.userId = userId;
        this.category = category;
        this.noticeFlag = noticeFlag;
        this.delFlag = delFlag;
        this.title = title;
        this.content = content;
        this.titleContent = titleContent;
        this.userName = userName;
        this.createdAfter = createdAfter;
        this.createdBefore = createdBefore;
    }
    
}
