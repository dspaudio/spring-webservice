package family.namkang.webservice.dto.board;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.board.Board;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardListDto {
    private Long id;
    private String boardName;
    private Boolean categoryUseFlag;
    private Boolean hierarchyUseFlag;
    private Boolean commentUseFlag;
    private Boolean noticeUseFlag;
    private Boolean fileUseFlag;
    private Boolean delFlag;
    private UserOwnedDto createdBy;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public BoardListDto(Board entity) {
        this.id = entity.getId();
        this.boardName = entity.getBoardName();
        this.categoryUseFlag = entity.getCategoryUseFlag();
        this.hierarchyUseFlag = entity.getHierarchyUseFlag();
        this.commentUseFlag = entity.getCommentUseFlag();
        this.noticeUseFlag = entity.getNoticeUseFlag();
        this.fileUseFlag = entity.getFileUseFlag();
        this.delFlag = entity.getDelFlag();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
