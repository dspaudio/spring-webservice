package family.namkang.webservice.dto.post;


import java.util.List;
import java.util.stream.Collectors;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.Pattern;
import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.dto.board.BoardOwnedDto;
import family.namkang.webservice.dto.board.category.BoardCategoryOwnedDto;
import family.namkang.webservice.dto.post.comment.PostCommentListDto;
import family.namkang.webservice.dto.post.file.PostFileOwnedDto;
import family.namkang.webservice.dto.user.UserOwnedDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class PostDetailDto {
    private Long id;
    private BoardOwnedDto board;
    private BoardCategoryOwnedDto boardCategory;
    private Boolean noticeFlag;
    private Boolean delFlag;
    private String title;
    private String content;
    private UserOwnedDto createdBy;
    private List<PostFileOwnedDto> files;
    //private List<PostCommentOwnedDto> comments;
    private String createdDate;
    private String modifiedDate;

    @Builder
    public PostDetailDto(Post entity) {
        this.id = entity.getId();
        this.board = new BoardOwnedDto(entity.getBoard());
        this.boardCategory = new BoardCategoryOwnedDto(entity.getBoardCategory());
        this.noticeFlag = entity.getNoticeFlag();
        this.delFlag = entity.getDelFlag();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.createdBy = new UserOwnedDto(entity.getCreatedBy());
        //this.files = entity.getFiles().stream().map(f -> new FileOwnedDto(f)).collect(Collectors.toList());
        this.files = entity.getPostFiles().stream().map(PostFileOwnedDto::new).collect(Collectors.toList());
        //this.comments = entity.getPostComment().stream().map(PostCommentOwnedDto::new).collect(Collectors.toList());
        this.createdDate = DateTimeUtil.toString(entity.getCreatedDate(), Pattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toString(entity.getModifiedDate(), Pattern.YYYYMMDDHMS);
    }

}
