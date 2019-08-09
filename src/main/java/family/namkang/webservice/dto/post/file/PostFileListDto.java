package family.namkang.webservice.dto.post.file;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.post.file.PostFile;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostFileListDto {
    private String id;
    private String fileName;
    private String fileUri;
    private String filePath;
    private String mimeType;
    private Long fileSize;
    //post정보 넣을까 말까 고민줓
    private String createdDate;
    private String modifiedDate;

    @Builder
    public PostFileListDto(PostFile entity) {
        this.id = entity.getId().toString();
        this.fileName = entity.getFileName();
        this.fileUri = entity.getFileUri();
        this.filePath = entity.getFilePath();
        this.mimeType = entity.getMimeType();
        this.fileSize = entity.getFileSize();
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
