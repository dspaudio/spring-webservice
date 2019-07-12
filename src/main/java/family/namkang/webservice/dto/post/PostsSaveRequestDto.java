package family.namkang.webservice.dto.post;

import java.util.ArrayList;
import java.util.List;

import family.namkang.webservice.domain.post.Post;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private Long boardId;
    private Long boardCategoryId;
    private String title;
    private String content;
    private Long userId;

    public Post toEntity(){
        return Post.builder()
                .boardId(this.boardId)
                .boardCategoryId(this.boardCategoryId)
                .title(this.title)
                .content(this.content)
                .userId(this.userId)
                .build();
    }
    
    public static List<Post> toEntity(List<PostsSaveRequestDto> dtos) {
    	List<Post> posts = new ArrayList<Post>();
    	for (PostsSaveRequestDto postsSaveRequestDto : dtos) {
    		posts.add( postsSaveRequestDto.toEntity( ));
		}
    	return posts;
    }
}
