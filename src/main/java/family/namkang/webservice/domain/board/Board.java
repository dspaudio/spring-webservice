package family.namkang.webservice.domain.board;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class Board extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String boardName;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean categoryUseFlag;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean hierarchyUseFlag;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean commentUseFlag;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean noticeUseFlag;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean fileUseFlag;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean delFlag;

    @ManyToOne
    @JoinColumn(name = "createdBy")
    private User createdBy; 

    @Builder
    public Board(String boardName, Boolean categoryUseFlag, Boolean hierarchyUseFlag, Boolean commentUseFlag, Boolean noticeUseFlag, Boolean fileUseFlag, Boolean delFlag, User createdBy) {
        this.boardName = boardName;
        this.categoryUseFlag = categoryUseFlag == null ? false:categoryUseFlag;
        this.hierarchyUseFlag = hierarchyUseFlag == null ? false:hierarchyUseFlag;
        this.commentUseFlag = commentUseFlag == null ? false:commentUseFlag;
        this.noticeUseFlag = noticeUseFlag == null ? false:noticeUseFlag;
        this.fileUseFlag = fileUseFlag == null ? false:fileUseFlag;
        this.delFlag = delFlag == null ? false:delFlag;
        this.createdBy = createdBy;
    }
}
