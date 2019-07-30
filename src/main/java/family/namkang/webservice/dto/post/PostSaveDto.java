package family.namkang.webservice.dto.post;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.dto.file.FileUploadDto;
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
    private Long groupNo;
    private Integer inGroupDepth;
    private Integer inGroupOrder;
    private Long boardCategoryId;
    private Boolean noticeFlag;
    private Boolean delFlag;
    private String title;
    private String content;
    private Long createdById;
    private MultipartFile[] files;
    private List<String> deleteFileIds;

    @Builder
    public PostSaveDto(Long id, Long boardId, Long groupNo, Integer inGroupDepth, Integer inGroupOrder, Long boardCategoryId, Boolean noticeFlag, Boolean delFlag, String title, String content, Long createdById, List<String> deleteFileIds) {
        this.id = id;
        this.boardId = boardId;
        this.groupNo = groupNo;
        this.inGroupDepth = inGroupDepth;
        this.inGroupOrder = inGroupOrder;
        this.boardCategoryId = boardCategoryId;
        this.noticeFlag = noticeFlag;
        this.delFlag = delFlag;
        this.title = title;
        this.content = content;
        this.createdById = createdById;
        this.deleteFileIds = deleteFileIds;
    }
    
    public void setMultiFiles(MultipartFile[] files) {
    	this.files = files;
    }
    
    public Post toEntity(){
        return Post.builder()
                .boardId(boardId)
                .groupNo(groupNo)
                .inGroupDepth(inGroupDepth)
                .inGroupOrder(inGroupOrder)
                .boardCategoryId(boardCategoryId)
                .noticeFlag(noticeFlag)
                .delFlag(delFlag)
                .title(title)
                .content(content)
                .createdById(createdById)
                .build();
    }
}
