package family.namkang.webservice.domain.post;


import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.domain.board.Board;
import family.namkang.webservice.domain.board.category.BoardCategory;
import family.namkang.webservice.domain.file.File;
import family.namkang.webservice.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class Post extends BaseTimeEntity {
    
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "boardId")
    private Board board;

    private Long groupNo;

    @org.springframework.lang.NonNull  //java에서의 null불가선언
    @Column(nullable=false, columnDefinition="Integer default 0")  //db에서의 null불가선언
    private Integer inGroupDepth;

    @Column(nullable=false, columnDefinition="Integer default 0")
    private Integer inGroupOrder;

    @ManyToOne
    @JoinColumn(name = "boardCategoryId")
    private BoardCategory boardCategory;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean noticeFlag;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean delFlag;

    @Column(nullable = false, length = 300)
    private String title;

    @Lob
    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    private String content;

    @ManyToOne
    @JoinColumn(name = "createdById")
    private User createdBy;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="post")
    @Basic(fetch = FetchType.LAZY)
    private List<File> files;
    
    
    @Builder
    public Post(Board board, Long groupNo, Integer inGroupDepth, Integer inGroupOrder, BoardCategory boardCategory, Boolean noticeFlag, Boolean delFlag, String title, String content, User createdBy, List<File> files) {
        this.board = board;
        this.groupNo = groupNo == null ? this.id:groupNo;
        this.inGroupDepth = inGroupDepth == null ? 0:inGroupDepth;
        this.inGroupOrder = inGroupOrder == null ? 0:inGroupOrder;
        this.boardCategory = boardCategory;
        this.noticeFlag = noticeFlag == null ? false:noticeFlag;
        this.delFlag = delFlag == null ? false:delFlag;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
        this.files = files;
    }
    
    
    
}