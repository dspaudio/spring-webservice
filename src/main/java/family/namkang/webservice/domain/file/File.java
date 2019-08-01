package family.namkang.webservice.domain.file;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.domain.post.Post;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class File extends BaseTimeEntity {
	
	@Id
	@Type(type="uuid-char")
	private UUID id;

    @Column(nullable = false, length = 300)
    private String fileName;

    @Column(nullable = false, length = 1000)
    private String fileUrl;

    @Column(nullable = false, length = 1000)
    private String filePath;

    @Column(nullable = false, length = 1000)
    private String mimeType;

    @Column(nullable = false)
    private Long fileSize;

    private Long postId;
    @ManyToOne
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    @Basic(fetch = FetchType.LAZY)
    private Post post; 
    

    @Builder
    public File(String fileName, String fileUrl, String filePath, String mimeType, Long fileSize, Post post) {
    	this.id = UUID.randomUUID();
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.filePath = filePath;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
        this.post = post;
    }

    
    public void setMultipartFile(MultipartFile multipartFile, Long postId) throws IOException {
    	this.id = UUID.randomUUID();
    	
		Path path = Paths.get(FileUtil.getFilePahtPost().toString(), this.id.toString());
		multipartFile.transferTo( path );
    	
        this.fileName = multipartFile.getOriginalFilename();
        this.fileUrl = FileUtil.getFileUri(path);
        this.filePath = path.toString();
        this.mimeType = multipartFile.getContentType();
        this.fileSize = multipartFile.getSize();
        this.postId = postId;
    }
}
