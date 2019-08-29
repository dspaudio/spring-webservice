package family.namkang.webservice.dto.news;


import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.news.News;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NewsListDto {
    private Long id;
    private News.Category category;
    private Boolean noticeFlag;
    private Boolean delFlag;
    private String title;
    private UserOwnedDto createdBy;
    private Integer filesCount;
    private Integer commentCount;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public NewsListDto(News entity) {
        this.id = entity.getId();
        this.category = entity.getCategory();
        this.noticeFlag = entity.getNoticeFlag();
        this.delFlag = entity.getTempFlag();
        this.title = entity.getTitle();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.filesCount = entity.getNewsFiles().size();
        this.commentCount = entity.getNewsComment().size();
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
