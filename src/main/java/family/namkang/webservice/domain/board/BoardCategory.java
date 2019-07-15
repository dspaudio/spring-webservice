package family.namkang.webservice.domain.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class BoardCategory extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;
    
    @Column(nullable=false, columnDefinition="Integer default 0")
    private Integer orderNo;

    @Column(length = 100, nullable = false)
    private String boardCategoryName;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean delFlag;

    @Builder
    public BoardCategory(Board board, Integer orderNo, String boardCategoryName, Boolean delFlag) {
        this.board = board;
        this.orderNo = orderNo == null ? 0:orderNo;
        this.boardCategoryName = boardCategoryName;
        this.delFlag = delFlag == null ? false:delFlag;
    }

}
