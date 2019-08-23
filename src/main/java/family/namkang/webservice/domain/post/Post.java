package family.namkang.webservice.domain.post;


import java.io.IOException;
import java.util.ArrayList;
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
import javax.persistence.OrderBy;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.domain.BaseUserCreatedEntity;
import family.namkang.webservice.domain.board.Board;
import family.namkang.webservice.domain.board.category.BoardCategory;
import family.namkang.webservice.domain.post.comment.PostComment;
import family.namkang.webservice.domain.post.file.PostFile;
import family.namkang.webservice.dto.post.PostSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class Post extends BaseUserCreatedEntity {
    
    @Id
    @GeneratedValue
    private Long id;

    private Long boardId;
    @ManyToOne
    @JoinColumn(name = "boardId", insertable = false, updatable = false)
    private Board board;

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

    @OneToMany(mappedBy="postId", cascade=CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdDate asc")
    @Basic(fetch = FetchType.LAZY)
    private List<PostFile> postFiles;

    @OneToMany(mappedBy="postId", cascade=CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdDate desc")
    @Basic(fetch = FetchType.LAZY)
    private List<PostComment> postComment;
    
    
    @Builder
    public Post(Long boardId, Long boardCategoryId, Boolean noticeFlag, Boolean delFlag, String title, String content, Long createdById, MultipartFile[] files) throws IOException {
        this.boardId = boardId;
        this.boardCategoryId = boardCategoryId;
        this.noticeFlag = noticeFlag == null ? false:noticeFlag;
        this.delFlag = delFlag == null ? false:delFlag;
        this.title = title;
        this.content = content;
        this.createdById = createdById;
        this.addPostFiles(files);
    }
    
    public boolean deletePostFileById (String fileId) {
    	if (fileId==null || fileId.isEmpty()) return false;
    	if (this.postFiles==null || this.postFiles.size()==0) return false; 
    	
    	for (PostFile postFile : postFiles) {
    		if ( fileId.equals(postFile.getId().toString()) ) {
    			postFiles.remove(postFile);
    			return true;
    		} 
		}
    	return false;
    }

    public void deletePostFilesById (List<String> delPostFileIds) {
    	if (delPostFileIds==null || delPostFileIds.size()==0) return;
    	if (this.postFiles==null || this.postFiles.size()==0) return;
    	
    	for (String delPostFileId : delPostFileIds) {
    		deletePostFileById(delPostFileId);
		}
    }
    
    public void addPostFiles (MultipartFile[] files) throws IOException {
    	if (files==null || files.length==0) return;
    	
    	this.postFiles = Optional.ofNullable(this.postFiles).orElse(new ArrayList<PostFile>());
    	for (MultipartFile multipartFile : files) {
    		this.postFiles.add( new PostFile(multipartFile, this.id) );
		}
    }
    
    public void update(PostSaveDto dto) throws IOException {
        Optional.ofNullable(dto.getBoardCategoryId()).ifPresent(c->{this.boardCategoryId = c;});
        Optional.ofNullable(dto.getNoticeFlag()).ifPresent(n->{this.noticeFlag = n;});
        Optional.ofNullable(dto.getDelFlag()).ifPresent(d->{this.delFlag = d;});
        Optional.ofNullable(dto.getTitle()).ifPresent(t->{this.title = t;});
        Optional.ofNullable(dto.getContent()).ifPresent(c->{this.content = c;});
        
        deletePostFilesById(dto.getDeleteFileIds());
        addPostFiles(dto.getFiles());
    } 
    
}