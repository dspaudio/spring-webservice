package family.namkang.webservice.domain.user;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import java.util.List;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import family.namkang.webservice.domain.post.Post;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepositoryTest {

    @Autowired
    UserRepository userRepository;

    @After
    public void cleanup() {
        /** 
        이후 테스트 코드에 영향을 끼치지 않기 위해 
        테스트 메소드가 끝날때 마다 respository 전체 비우는 코드
        **/
    	userRepository.deleteAll();
    }

	@Test
	public void User저장_불러오기() {
        //given
		userRepository.save(User.builder()
                .userName("유저일")
                .build());

        //when
        List<User> userList = userRepository.findAll();

        //then
        User User = userList.get(0);
        assertThat(User.getUserName(), is("유저일"));
	}

}
