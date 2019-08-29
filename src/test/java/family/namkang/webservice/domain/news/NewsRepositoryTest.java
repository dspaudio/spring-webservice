package family.namkang.webservice.domain.news;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
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
import org.springframework.transaction.annotation.Transactional;

import family.namkang.webservice.domain.news.News;
import family.namkang.webservice.domain.news.NewsRepository;
import family.namkang.webservice.domain.news.file.NewsFileRepository;
import family.namkang.webservice.domain.user.User;
import family.namkang.webservice.domain.user.UserRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class NewsRepositoryTest {

    @Autowired
    NewsRepository newsRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    NewsFileRepository fileRepository;
    
    private User user;
    private News savedNews;

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
    }
    @After
    public void cleanup() {
        /** 
        이후 테스트 코드에 영향을 끼치지 않기 위해 
        테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
        **/
    	newsRepository.delete(savedNews);
    }

    @Test
    @Transactional    // LAZY로 불러오는 데이터 취득 위해 영속성 유지되도록 설정.
    public void 게시글저장_불러오기() {
        //given
        try {
			this.savedNews = newsRepository.save(News.builder()
			        .title("테스트 게시글")
			        .content("테스트 본문")
			        .createdById(user.getId())
			        .category(News.Category.TEC)
			        .build());
		} catch (IOException e) {
			e.printStackTrace();
		}
        
        //when
        Optional<News> result = newsRepository.findById(savedNews.getId());
                
        //then

        assertTrue(result.isPresent());
        News news = result.get();
        
        assertThat(news.getTitle(), is("테스트 게시글"));
        assertThat(news.getContent(), is("테스트 본문"));
        assertThat(news.getCreatedById(), is(user.getId()));
        assertThat(news.getCategory(), is(News.Category.TEC));
         
    }
    

}