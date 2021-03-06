package family.namkang.webservice.domain.base;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostRemove;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.FileUtil;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class BaseFileEntity extends BaseTimeEntity {
	
	@Id
	@Type(type="uuid-char")
	protected UUID id;

    @Column(nullable = false, length = 300)
    protected String fileName;

    @Column(nullable = false, length = 1000)
    protected String fileUri;

    @Column(nullable = false, length = 1000)
    protected String filePath;

    @Column(nullable = false, length = 1000)
    protected String mimeType;

    @Column(nullable = false)
    protected Long fileSize;
    
    
    // 해당 파일들이 업로드되는 폴더 지정하는 메소드. 구현체에서 정의필요.
    abstract protected String getBasePathImpl();
    
    protected BaseFileEntity(MultipartFile multipartFile) throws IOException {
    	this.id = UUID.randomUUID();
    	
		Path path = Paths.get(getBasePathImpl(), DateTimeUtil.getCurrentyyyy(), DateTimeUtil.getCurrentMM(), DateTimeUtil.getCurrentdd());
		Files.createDirectories(path);

		Path file = Paths.get(path.toString(), this.id.toString());

		multipartFile.transferTo( file );
    	
        this.fileName = multipartFile.getOriginalFilename();
        this.fileUri = FileUtil.getFileUri(path);
        this.filePath = path.toString();
        this.mimeType = multipartFile.getContentType();
        this.fileSize = multipartFile.getSize();
    }
    
    @PostRemove
    public void postRemove(){
    	// EntityListener 삭제 후에 실행됨
    	// 자식클래스에서 postRemove를 다시 만들면 부모의 것은 실행되지 않고 덮어쓰기되어버림
        System.out.println("========== File.postRemove ==========");
    	FileUtil.removeFromDisk(this.filePath);
    }

}
