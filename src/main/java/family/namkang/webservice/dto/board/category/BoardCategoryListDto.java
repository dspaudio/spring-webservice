package family.namkang.webservice.dto.board.category;

import family.namkang.webservice.domain.board.category.BoardCategory;
import family.namkang.webservice.dto.board.BoardOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCategoryListDto {

    private Long id;
    private BoardOwnedDto board;
    private Integer orderNo;
    private String boardCategoryName;
    private Boolean delFlag;

    @Builder
    public BoardCategoryListDto(BoardCategory entity) {
        this.id = entity.getId();
        this.board = new BoardOwnedDto(entity.getBoard());
        this.orderNo = entity.getOrderNo();
        this.boardCategoryName = entity.getBoardCategoryName();
        this.delFlag = entity.getDelFlag();
    }

}
