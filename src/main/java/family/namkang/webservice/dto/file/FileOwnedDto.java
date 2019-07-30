package family.namkang.webservice.dto.file;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.file.File;
import lombok.Builder;
import lombok.Getter;

@Getter
public class FileOwnedDto {
    private String fileId;
    private String fileName;
    private String fileUrl;
    private String filePath;
    private String mimeType;
    private Long fileSize;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public FileOwnedDto(File entity) {
        this.fileId = entity.getFileId().toString();
        this.fileName = entity.getFileName();
        this.fileUrl = entity.getFileUrl();
        this.filePath = entity.getFilePath();
        this.mimeType = entity.getMimeType();
        this.fileSize = entity.getFileSize();
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
