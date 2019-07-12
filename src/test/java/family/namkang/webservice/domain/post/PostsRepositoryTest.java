package family.namkang.webservice.domain.post;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.domain.post.PostRepository;
import family.namkang.webservice.domain.user.User;
import family.namkang.webservice.domain.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

	@BeforeClass
	public static void setupForClass(){
	}
	
	@AfterClass
	public static void tearDownForClass(){
	}


	@Before
    public void setUp() {
        /** 
        이후 테스트 코드에 영향을 끼치지 않기 위해 
        테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
        **/
		userRepository.save(User.builder()
                .userName("유저일")
                .build());
    }
    @After
    public void cleanup() {
        /** 
        이후 테스트 코드에 영향을 끼치지 않기 위해 
        테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
        **/
    	postRepository.deleteAll();
    	userRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기() {
        //given
    	User user = userRepository.findAll().get(0);
    	postRepository.save(Post.builder()
                .boardId(1L)
                .title("테스트 게시글")
                .content("테스트 본문")
                .userId( user.getId() )
                .build());

        //when
        List<Post> postList = postRepository.findAll();

        //then
        Post post = postList.get(0);
        
        assertThat(post.getTitle(), is("테스트 게시글"));
        assertThat(post.getContent(), is("테스트 본문"));
        assertThat(post.getCreatedBy().getId(), is(user.getId()));
        assertThat(post.getCreatedBy().getUserName(), is(user.getUserName()));
    }
    
    @Test
    public void 디폴트저장_확인 () {
        //given
    	User user = userRepository.findAll().get(0);
        LocalDateTime now = LocalDateTime.now();
        postRepository.save(Post.builder()
                .boardId(1L)
                .title("테스트 게시글")
                .content("테스트 본문")
                .userId( user.getId() )
                .build());
        //when
        List<Post> postList = postRepository.findAll();

        //then
        LocalDateTime afer = LocalDateTime.now();
        Post post = postList.get(0);
        assertTrue(post.getCreatedDate().isAfter(now));
        assertTrue(post.getModifiedDate().isAfter(now));
        assertTrue(post.getCreatedDate().isBefore(afer));
        assertTrue(post.getModifiedDate().isBefore(afer));
    }

    @Test
    public void BaseTimeEntity_등록 () {
        //given
    	User user = userRepository.findAll().get(0);
        LocalDateTime now = LocalDateTime.now();
        postRepository.save(Post.builder()
                .boardId(1L)
                .title("테스트 게시글")
                .content("테스트 본문")
                .userId(user.getId())
                .build());
        //when
        List<Post> postList = postRepository.findAll();

        //then
        LocalDateTime afer = LocalDateTime.now();
        Post post = postList.get(0);
        assertTrue(post.getCreatedDate().isAfter(now));
        assertTrue(post.getModifiedDate().isAfter(now));
        assertTrue(post.getCreatedDate().isBefore(afer));
        assertTrue(post.getModifiedDate().isBefore(afer));
    }
}