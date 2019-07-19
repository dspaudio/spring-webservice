package family.namkang.webservice.dto.post;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostSearchDto {
    private Long userId;
    private Long boardId;
    private Long boardCategoryId;
    private Boolean noticeFlag;
    private Boolean delFlag;
    private String title;
    private String content;
    private String titleContent;
    private String userName;
    private String createdAfter;
    private String createdBefore;


}
