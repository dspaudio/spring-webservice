package family.namkang.webservice.domain.post;


import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;

import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.domain.user.User;
import family.namkang.webservice.dto.post.PostsSaveRequestDto;
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

    @Column(nullable = false)
    private Long boardId;

    private Long groupNo;

    @Column(nullable=false, columnDefinition="Integer default 0")
    private Integer inGroupDepth;

    @Column(nullable=false, columnDefinition="Integer default 0")
    private Integer inGroupOrder;

    private Long boardCategoryId;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean noticeFlag;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean delFlag;

    @Column(length = 300, nullable = false)
    private String title;

    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    @Lob
    private String content;

    @ManyToOne
    @JoinColumn(name = "userId")
    private User createdBy; 
    
    

    @Builder
    public Post(Long boardId, Long groupNo, Integer inGroupDepth, Integer inGroupOrder, Long boardCategoryId, Boolean noticeFlag, Boolean delFlag, String title, String content, User createdBy) {
    	
        this.boardId = boardId;
        this.groupNo = groupNo == null ? boardId:groupNo;
        this.inGroupDepth = inGroupDepth == null ? 0:inGroupDepth;
        this.inGroupOrder = inGroupOrder == null ? 0:inGroupOrder;
        this.boardCategoryId = boardCategoryId;
        this.noticeFlag = noticeFlag == null ? false:noticeFlag;
        this.delFlag = delFlag == null ? false:delFlag;
        this.title = title;
        this.content = content;
        this.createdBy = createdBy;
    }
    
    public void update(PostsSaveRequestDto dto) {
        this.boardCategoryId = dto.getBoardCategoryId();
    	this.title = dto.getTitle();
        this.content = dto.getContent();
    }

    
}