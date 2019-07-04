package family.namkang.webservice.dto.posts;

import java.util.ArrayList;
import java.util.List;

import family.namkang.webservice.domain.Posts;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PostsSaveRequestDto {
    private String title;
    private String content;
    private String author;

    public Posts toEntity(){
        return Posts.builder()
                .title(title)
                .content(content)
                .author(author)
                .build();
    }
    
    public static List<Posts> toEntity(List<PostsSaveRequestDto> dtos) {
    	List<Posts> posts = new ArrayList<Posts>();
    	for (PostsSaveRequestDto postsSaveRequestDto : dtos) {
    		posts.add( postsSaveRequestDto.toEntity( ));
		}
    	return posts;
    }
}
