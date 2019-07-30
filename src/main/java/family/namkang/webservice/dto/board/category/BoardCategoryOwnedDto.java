package family.namkang.webservice.dto.board.category;

import family.namkang.webservice.domain.board.category.BoardCategory;
import lombok.Builder;
import lombok.Getter;

@Getter
public class BoardCategoryOwnedDto {

    private Long id;
    private Integer orderNo;
    private String boardCategoryName;
    private Boolean delFlag;

    @Builder
    public BoardCategoryOwnedDto(BoardCategory entity) {
        this.id = entity.getId();
        this.orderNo = entity.getOrderNo();
        this.boardCategoryName = entity.getBoardCategoryName();
        this.delFlag = entity.getDelFlag();
    }

}
