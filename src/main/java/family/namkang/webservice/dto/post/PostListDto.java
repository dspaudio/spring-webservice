package family.namkang.webservice.dto.post;


import java.util.Optional;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.dto.board.BoardOwnedDto;
import family.namkang.webservice.dto.board.category.BoardCategoryOwnedDto;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostListDto {
    private Long id;
    private BoardOwnedDto board;
    private Long groupNo;
    private Integer inGroupDepth;
    private Integer inGroupOrder;
    private BoardCategoryOwnedDto boardCategory;
    private Boolean noticeFlag;
    private Boolean delFlag;
    private String title;
    private UserOwnedDto createdBy;
    private Integer filesCount;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public PostListDto(Post entity) {
        this.id = entity.getId();
        this.board = new BoardOwnedDto(entity.getBoard());
        this.groupNo = entity.getGroupNo();
        this.inGroupDepth = entity.getInGroupDepth();
        this.inGroupOrder = entity.getInGroupOrder();
        this.boardCategory = Optional.ofNullable(entity.getBoardCategory()).map(BoardCategoryOwnedDto::new).orElse(null);
        this.noticeFlag = entity.getNoticeFlag();
        this.delFlag = entity.getDelFlag();
        this.title = entity.getTitle();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.filesCount = entity.getFiles().size();
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
