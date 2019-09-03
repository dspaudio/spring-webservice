package family.namkang.webservice.dto.news;


import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.news.News;
import family.namkang.webservice.dto.EnumCodeNameDto;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class NewsOwnedDto {
    private Long id;
    private EnumCodeNameDto category;
    private Boolean delFlag;
    private String title;
    private UserOwnedDto createdBy;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public NewsOwnedDto(News entity) {
        this.id = entity.getId();
        this.category = new EnumCodeNameDto( entity.getCategory() );
        this.delFlag = entity.getTempFlag();
        this.title = entity.getTitle();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
