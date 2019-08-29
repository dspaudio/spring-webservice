package family.namkang.webservice.domain.news.file;

import java.io.IOException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.domain.base.BaseFileEntity;
import family.namkang.webservice.domain.news.News;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class NewsFile extends BaseFileEntity {

    @Column(nullable = false)
    private Long newsId;
    @ManyToOne
    @JoinColumn(name = "newsId", insertable = false, updatable = false)
    private News news;


	@Override
	protected String getBasePathImpl() {
		return BASE_PATH + "\\news";
	}

    @Builder
    public NewsFile(String fileName, String fileUri, String filePath, String mimeType, Long fileSize, Long newsId) {
    	setFileName(fileName);
    	setFileUri(fileUri);
    	setFilePath(filePath);
    	setMimeType(mimeType);
    	setFileSize(fileSize);
    	this.newsId = newsId;
    }
    
    public NewsFile(MultipartFile multipartFile, Long newsId) throws IOException {
    	super(multipartFile);
    	this.newsId = newsId; 
    }
}
