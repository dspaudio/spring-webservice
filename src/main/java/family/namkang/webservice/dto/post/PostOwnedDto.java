package family.namkang.webservice.dto.post;


import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.dto.board.BoardOwnedDto;
import family.namkang.webservice.dto.board.category.BoardCategoryOwnedDto;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostOwnedDto {
    private Long id;
    private BoardOwnedDto board;
    private BoardCategoryOwnedDto boardCategory;
    private Boolean delFlag;
    private String title;
    private UserOwnedDto createdBy;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public PostOwnedDto(Post entity) {
        this.id = entity.getId();
        this.board = new BoardOwnedDto(entity.getBoard());
        this.boardCategory = new BoardCategoryOwnedDto(entity.getBoardCategory());
        this.delFlag = entity.getDelFlag();
        this.title = entity.getTitle();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
