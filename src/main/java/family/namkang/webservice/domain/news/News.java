package family.namkang.webservice.domain.news;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.common.KeyValue;
import family.namkang.webservice.domain.base.post.BasePostEntity;
import family.namkang.webservice.domain.news.comment.NewsComment;
import family.namkang.webservice.domain.news.file.NewsFile;
import family.namkang.webservice.dto.news.NewsSaveDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class News extends BasePostEntity {
    
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean noticeFlag;

    @Column(nullable=false, columnDefinition="Boolean default false")
    private Boolean tempFlag;

    @OneToMany(mappedBy="newsId", cascade=CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdDate asc")
    @Basic(fetch = FetchType.LAZY)
    private List<NewsFile> newsFiles;

    @OneToMany(mappedBy="newsId", cascade=CascadeType.ALL, orphanRemoval = true)
    @OrderBy("createdDate desc")
    @Basic(fetch = FetchType.LAZY)
    private List<NewsComment> newsComment;
    
    
    @Builder
    public News(Category category, Boolean noticeFlag, Boolean delFlag, String title, String content, Long createdById, MultipartFile[] files) throws IOException {
    	this.category = category;
        this.noticeFlag = noticeFlag == null ? false:noticeFlag;
        this.tempFlag = delFlag == null ? false:delFlag;
        this.title = title;
        this.content = content;
        this.createdById = createdById;
        this.addNewsFiles(files);
    }
    
    public boolean deleteNewsFileById (String fileId) {
    	if (fileId==null || fileId.isEmpty()) return false;
    	if (this.newsFiles==null || this.newsFiles.size()==0) return false; 
    	
    	for (NewsFile newsFile : newsFiles) {
    		if ( fileId.equals(newsFile.getId().toString()) ) {
    			newsFiles.remove(newsFile);
    			return true;
    		} 
		}
    	return false;
    }

    public void deleteNewsFilesById (List<String> delNewsFileIds) {
    	if (delNewsFileIds==null || delNewsFileIds.size()==0) return;
    	if (this.newsFiles==null || this.newsFiles.size()==0) return;
    	
    	for (String delNewsFileId : delNewsFileIds) {
    		deleteNewsFileById(delNewsFileId);
		}
    }
    
    public void addNewsFiles (MultipartFile[] files) throws IOException {
    	if (files==null || files.length==0) return;
    	
    	this.newsFiles = Optional.ofNullable(this.newsFiles).orElse(new ArrayList<NewsFile>());
    	for (MultipartFile multipartFile : files) {
    		this.newsFiles.add( new NewsFile(multipartFile, this.id) );
		}
    }
    
    public void update(NewsSaveDto dto) throws IOException {
        Optional.ofNullable(dto.getCategory()).ifPresent(c->{this.category = c;});
        Optional.ofNullable(dto.getNoticeFlag()).ifPresent(n->{this.noticeFlag = n;});
        Optional.ofNullable(dto.getDelFlag()).ifPresent(d->{this.tempFlag = d;});
        Optional.ofNullable(dto.getTitle()).ifPresent(t->{this.title = t;});
        Optional.ofNullable(dto.getContent()).ifPresent(c->{this.content = c;});
        
        deleteNewsFilesById(dto.getDeleteFileIds());
        addNewsFiles(dto.getFiles());
    } 
    
    
    public enum Category implements KeyValue {
        SOC("사회"),
        POL("정치"),
        ECO("경제"),
        INT("국제"),
        CUL("문화"),
        LIF("생활"),
        TEC("기술");

        private String value;

        Category(String value) {
            this.value = value;
        }
        
        @Override
        public String getKey() {
            return name();
        }
        @Override
        public String getValue() {
            return value;
        }
    }
}