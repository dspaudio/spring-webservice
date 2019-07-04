package family.namkang.webservice.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import family.namkang.webservice.domain.PostsRepository;
import family.namkang.webservice.dto.posts.PostsSaveRequestDto;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
    private PostsRepository postsRepository;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/post")
    public void savePost(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }

    @PostMapping("/posts")
    public void savePosts(@RequestBody List<PostsSaveRequestDto> dtos){
        postsRepository.saveAll(PostsSaveRequestDto.toEntity(dtos));
    }
}
