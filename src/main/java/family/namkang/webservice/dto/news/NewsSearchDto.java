package family.namkang.webservice.dto.news;


import family.namkang.webservice.domain.news.News;
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

}
