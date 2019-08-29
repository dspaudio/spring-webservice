package family.namkang.webservice.dto.news;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NewsSearchDto {
    private Long userId;
    private Long category;
    private Boolean noticeFlag;
    private Boolean delFlag;
    private String title;
    private String content;
    private String titleContent;
    private String userName;
    private String createdAfter;
    private String createdBefore;

}
