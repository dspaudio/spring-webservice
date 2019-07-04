package family.namkang.webservice.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.domain.post.PostsRepository;
import family.namkang.webservice.dto.post.PostsSaveRequestDto;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class WebRestController {
    private PostsRepository postsRepository;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/savePost")
    public void savePost(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }

    @PostMapping("/saveAllPost")
    public void saveAllPost(@RequestBody List<PostsSaveRequestDto> dtos){
        postsRepository.saveAll(PostsSaveRequestDto.toEntity(dtos));
    }

    @PostMapping("/updatePost")
    public void updatePost(@PathVariable Long id, @RequestBody PostsSaveRequestDto dto){
    	Post post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. id=" + id));;
        post.update(dto);
    }
}
