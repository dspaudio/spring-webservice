package family.namkang.webservice.domain.post.file;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PostRemove;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.common.util.FileUtil;
import family.namkang.webservice.domain.BaseFileEntity;
import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class PostFile extends BaseFileEntity {

    @Column(nullable = false)
    private Long postId;
    @ManyToOne
    @JoinColumn(name = "postId", insertable = false, updatable = false)
    private Post post;

    @Builder
    public PostFile(String fileName, String fileUri, String filePath, String mimeType, Long fileSize, Long postId) {
    	setFileName(fileName);
    	setFileUri(fileUri);
    	setFilePath(filePath);
    	setMimeType(mimeType);
    	setFileSize(fileSize);
    	this.postId = postId;
    }
    
    public PostFile(MultipartFile multipartFile, Long postId) throws IOException {
    	this.postId = postId;
    	super.setId(UUID.randomUUID());
    	
		Path path = Paths.get(FileUtil.getFilePahtPost().toString(), super.getId().toString());
		multipartFile.transferTo( path );
    	
		setFileName( multipartFile.getOriginalFilename() );
		setFileUri( FileUtil.getFileUri(path) );
		setFilePath( path.toString() );
		setMimeType( multipartFile.getContentType() );
		setFileSize( multipartFile.getSize() );
    }
    
}
