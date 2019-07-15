package family.namkang.webservice.domain.post;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import family.namkang.webservice.domain.board.Board;
import family.namkang.webservice.domain.board.BoardCategoryRepository;
import family.namkang.webservice.domain.board.BoardRepository;
import family.namkang.webservice.domain.file.File;
import family.namkang.webservice.domain.file.FileRepository;
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
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    FileRepository fileRepository;
    
    private User user;
    private Board board;
    private Post savedPost;
    private File savedFile;

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
		this.user = userRepository.findAll().get(0);
		this.board = boardRepository.findAll().get(0);
    }
    @After
    public void cleanup() {
        /** 
        이후 테스트 코드에 영향을 끼치지 않기 위해 
        테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
        **/
    	postRepository.delete(savedPost);
    }

    @Test
    public void 게시글저장_불러오기() {
        //given

        LocalDateTime now = LocalDateTime.now();
        this.savedPost = postRepository.save(Post.builder()
                .board(board)
                .title("테스트 게시글")
                .content("테스트 본문")
                .createdBy(user)
                .build());
        this.savedFile = fileRepository.save(File.builder()
                .fileName("파일파일.jpg")
                .fileUrl("/2019/07/15/파일파일.jpg")
                .filePath("\\2019\\07\\15\\파일파일.jpg")
                .mimeType("image/jpeg")
                .fileSize(100L)
                .post(savedPost)
                .build());
        

        //when
        Optional<Post> result = postRepository.findById(savedPost.getId());
        		
        //then
        LocalDateTime afer = LocalDateTime.now();
        

        assertTrue(result.isPresent());
        Post post = result.get();

        assertTrue(post.getCreatedDate().isAfter(now));
        assertTrue(post.getModifiedDate().isAfter(now));
        assertTrue(post.getCreatedDate().isBefore(afer));
        assertTrue(post.getModifiedDate().isBefore(afer));
        
        assertThat(post.getTitle(), is("테스트 게시글"));
        assertThat(post.getContent(), is("테스트 본문"));
        assertThat(post.getCreatedBy().getId(), is(user.getId()));
        assertThat(post.getCreatedBy().getUserName(), is(user.getUserName()));
        assertThat(post.getBoard().getBoardName(), is(board.getBoardName()));

        assertThat(post.getFiles().get(0).getFileName(), is(savedFile.getFileName()));
        assertThat(post.getFiles().get(0).getFileSize(), is(savedFile.getFileSize()));
        assertThat(post.getFiles().get(0).getMimeType(), is(savedFile.getMimeType()));
    }
    

}