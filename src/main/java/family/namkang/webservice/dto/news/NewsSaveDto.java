package family.namkang.webservice.dto.news;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.domain.news.News;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class NewsSaveDto {
    private Long id;
    private News.Category category;
    private boolean noticeFlag;
    private boolean delFlag;
    private String title;
    private String content;
    private Long createdById;
    private MultipartFile[] files;
    private List<String> deleteFileIds;

    @Builder
    public NewsSaveDto(Long id, News.Category category, boolean noticeFlag, boolean delFlag, String title, String content, Long createdById, MultipartFile[] files, List<String> deleteFileIds) {
        this.id = id;
        this.category = category;
        this.noticeFlag = noticeFlag;
        this.delFlag = delFlag;
        this.title = title;
        this.content = content;
        this.createdById = createdById;
        this.files = files;
        this.deleteFileIds = deleteFileIds;
    }
    
    public News toEntity() throws IOException{
        return News.builder()
                .category(category)
                .noticeFlag(noticeFlag)
                .delFlag(delFlag)
                .title(title)
                .content(content)
                .createdById(createdById)
                .build();
    }
}
