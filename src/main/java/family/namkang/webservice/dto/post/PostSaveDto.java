package family.namkang.webservice.dto.post;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.domain.post.Post;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostSaveDto {
    private Long id;
    private Long boardId;
    private Long boardCategoryId;
    private Boolean noticeFlag;
    private Boolean delFlag;
    private String title;
    private String content;
    private Long createdById;
    private MultipartFile[] files;
    private List<String> deleteFileIds;

    @Builder
    public PostSaveDto(Long id, Long boardId, Long boardCategoryId, Boolean noticeFlag, Boolean delFlag, String title, String content, Long createdById, MultipartFile[] files, List<String> deleteFileIds) {
        this.id = id;
        this.boardId = boardId;
        this.boardCategoryId = boardCategoryId;
        this.noticeFlag = noticeFlag;
        this.delFlag = delFlag;
        this.title = title;
        this.content = content;
        this.createdById = createdById;
        this.files = files;
        this.deleteFileIds = deleteFileIds;
    }
    
    public Post toEntity() throws IOException{
        return Post.builder()
                .boardId(boardId)
                .boardCategoryId(boardCategoryId)
                .noticeFlag(noticeFlag)
                .delFlag(delFlag)
                .title(title)
                .content(content)
                .createdById(createdById)
                .build();
    }
}
