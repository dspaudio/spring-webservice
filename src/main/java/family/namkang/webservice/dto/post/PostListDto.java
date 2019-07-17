package family.namkang.webservice.dto.post;


import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.common.util.DateTimeUtil.DateTimePattern;
import family.namkang.webservice.domain.post.Post;
import lombok.Getter;

@Getter
public class PostListDto {
	private Long id;
	private Long boardId;
	private String boardName;
	private Long groupNo;
	private Integer inGroupDepth;
	private Integer inGroupOrder;
	private Long boardCategoryId;
	private String boardCategoryName;
	private Boolean noticeFlag;
	private Boolean delFlag;
	private String title;
	private Long createdById;
	private String createdByName;
	private Integer filesCount;
	private String createdDate;
	private String modifiedDate;

    public PostListDto(Post entity) {
        this.id = entity.getId();
        this.boardId = entity.getBoard().getId();
        this.boardName = entity.getBoard().getBoardName();
        this.groupNo = entity.getGroupNo();
        this.inGroupDepth = entity.getInGroupDepth();
        this.inGroupOrder = entity.getInGroupOrder();
        this.boardCategoryId = entity.getBoardCategory().getId();
        this.boardCategoryName = entity.getBoardCategory().getBoardCategoryName();
        this.noticeFlag = entity.getNoticeFlag();
        this.delFlag = entity.getDelFlag();
        this.title = entity.getTitle();
        this.createdById = entity.getCreatedBy().getId();
        this.createdByName = entity.getCreatedBy().getUserName();
        this.filesCount = entity.getFiles().size();
        this.createdDate = DateTimeUtil.toStringWithPattern(entity.getCreatedDate(), DateTimePattern.YYYYMMDDHMS);
        this.modifiedDate = DateTimeUtil.toStringWithPattern(entity.getModifiedDate(), DateTimePattern.YYYYMMDDHMS);
    }

}
