package family.namkang.webservice.dto.file;

import family.namkang.webservice.domain.file.File;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class FileUploadDto {
    private String fileName;
    private String fileUrl;
    private String filePath;
    private String mimeType;
    private Long fileSize;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public FileUploadDto(String fileName, String fileUrl, String filePath, String mimeType, Long fileSize, String createdDate, String modifiedDate) {
        this.fileName = fileName;
        this.fileUrl = fileUrl;
        this.filePath = filePath;
        this.mimeType = mimeType;
        this.fileSize = fileSize;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
    
    public File toEntity(){
        return File.builder()
                .fileName(this.fileName)
                .fileUrl(this.fileUrl)
                .filePath(this.filePath)
                .mimeType(this.mimeType)
                .fileSize(this.fileSize)
                .build();
    }
}
