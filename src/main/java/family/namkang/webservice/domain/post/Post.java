package family.namkang.webservice.domain.post;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.stream.Stream;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import family.namkang.webservice.domain.BaseTimeEntity;
import family.namkang.webservice.dto.post.PostsSaveRequestDto;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Post extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING) // enum의 key를 DB에 저장하기 위해, 없을 경우 enum의 디폴트값인 숫자가 들어간다.
    private PostType postType; 

    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder
    public Post(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }
    
    public void update(PostsSaveRequestDto dto) {
    	this.title = dto.getTitle();
        this.content = dto.getContent();
        this.author = dto.getAuthor();
    }

    
    public enum PostType {

        TYPE1("타입1"),
        TYPE2("타입2"),
        TYPE3("타입3"),
        TYPE4("타입4"),
        TYPE5("타입5"),
        TYPE6("타입6"),
        TYPE7("타입7"),
        TYPE8("타입8");

        @Getter
        private String value;

        PostType(String value) {
            this.value = value;
        }
    }
    public enum PostTypeGroup {

    	GROUP1("그룹1", new PostType[] {PostType.TYPE1,PostType.TYPE2,PostType.TYPE3}),
    	GROUP2("그룹2", new PostType[] {PostType.TYPE4,PostType.TYPE5,PostType.TYPE6}),
    	GROUP3("그룹3", new PostType[] {PostType.TYPE7,PostType.TYPE8}),
    	EMPTY("없음", new PostType[] {});

        @Getter
        private String value;
        @Getter
        private PostType[] containedPostType;

        PostTypeGroup(String value, PostType[] containedPostType) {
            this.value = value;
            this.containedPostType = containedPostType;
        }
        
        public static PostTypeGroup findGroup(PostType postType) {
        	Stream<PostTypeGroup> stream = Arrays.stream(PostTypeGroup.values());
        	stream.filter(group -> hasPostType(group, postType)).findAny().orElse(PostTypeGroup.EMPTY);
        	return Arrays.stream(PostTypeGroup.values()).filter(group -> hasPostType(group, postType)).findAny().orElse(PostTypeGroup.EMPTY);
        }
        private static boolean hasPostType(PostTypeGroup group, PostType type) {
        	return Arrays.stream(group.getContainedPostType()).anyMatch(contained -> contained==type);
        }
    }
}