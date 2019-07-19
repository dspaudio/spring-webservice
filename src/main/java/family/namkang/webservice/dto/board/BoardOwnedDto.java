package family.namkang.webservice.dto.board;

import family.namkang.webservice.domain.board.Board;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardOwnedDto {
    private Long id;
    private String boardName;
    private Boolean categoryUseFlag;
    private Boolean hierarchyUseFlag;
    private Boolean commentUseFlag;
    private Boolean noticeUseFlag;
    private Boolean fileUseFlag;
    private Boolean delFlag;

    @Builder
    public BoardOwnedDto(Board entity) {
        this.id = entity.getId();
        this.boardName = entity.getBoardName();
        this.categoryUseFlag = entity.getCategoryUseFlag();
        this.hierarchyUseFlag = entity.getHierarchyUseFlag();
        this.commentUseFlag = entity.getCommentUseFlag();
        this.noticeUseFlag = entity.getNoticeUseFlag();
        this.fileUseFlag = entity.getFileUseFlag();
        this.delFlag = entity.getDelFlag();
    }

}
