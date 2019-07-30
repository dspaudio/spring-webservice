package family.namkang.webservice.domain.post;


import java.util.List;
import java.util.Optional;

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
import javax.persistence.PrePersist;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.domain.board.Board;
import family.namkang.webservice.domain.board.category.BoardCategory;
import family.namkang.webservice.domain.file.File;
import family.namkang.webservice.domain.user.User;
import family.namkang.webservice.dto.post.PostSaveDto;
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

    private Long boardId;
    @ManyToOne
    @JoinColumn(name = "boardId", insertable = false, updatable = false)
    private Board board;

    private Long groupNo;

    @org.springframework.lang.NonNull  //java에서의 null불가선언
    @Column(nullable=false, columnDefinition="Integer default 0")  //db에서의 null불가선언
    private Integer inGroupDepth;

    @Column(nullable=false, columnDefinition="Integer default 0")
    private Integer inGroupOrder;

    private Long boardCategoryId;
    @ManyToOne
    @JoinColumn(name = "boardCategoryId", insertable = false, updatable = false)
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

    private Long createdById;
    @ManyToOne
    @JoinColumn(name = "createdById", insertable = false, updatable = false)
    private User createdBy;

    @OneToMany(cascade=CascadeType.ALL, mappedBy="post")
    @Basic(fetch = FetchType.LAZY)
    private List<File> files;
    
    
    @Builder
    public Post(Long boardId, Long groupNo, Integer inGroupDepth, Integer inGroupOrder, Long boardCategoryId, Boolean noticeFlag, Boolean delFlag, String title, String content, Long createdById, List<File> files) {
        this.boardId = boardId;
        this.groupNo = groupNo;
        this.inGroupDepth = inGroupDepth == null ? 0:inGroupDepth;
        this.inGroupOrder = inGroupOrder == null ? 0:inGroupOrder;
        this.boardCategoryId = boardCategoryId;
        this.noticeFlag = noticeFlag == null ? false:noticeFlag;
        this.delFlag = delFlag == null ? false:delFlag;
        this.title = title;
        this.content = content;
        this.createdById = createdById;
        this.files = files;
        
    }
    
    public boolean setDefaultGroupNo() {
    	if (this.groupNo ==null && this.id !=null) {
    		this.groupNo=this.id;
    		return true;
    	}
    	return false;
    }
    
    public void update(PostSaveDto dto) {
        this.boardCategoryId = dto.getBoardCategoryId();
        this.noticeFlag = dto.getNoticeFlag();
        this.delFlag = dto.getDelFlag();
        this.title = dto.getTitle();
        this.content = dto.getContent();
        //this.files = dto.getFiles();
    } 
    
}