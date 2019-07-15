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
    private String title;
    private String content;

    public Post toEntity(){
        return Post.builder()
                .title(this.title)
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
