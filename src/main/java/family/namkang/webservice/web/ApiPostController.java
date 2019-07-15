package family.namkang.webservice.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.domain.post.PostRepository;
import family.namkang.webservice.dto.post.PostsSaveRequestDto;
import lombok.AllArgsConstructor;

@RestController  //REST API 용으로 만들때만 사용하기. return값이 그대로 response 내용
@AllArgsConstructor
@RequestMapping("/api/post")
public class ApiPostController {
    private PostRepository postsRepository;

    @GetMapping("/listAll")
    public List<Post> listAll() {
        return postsRepository.findAll();
    }
    
    @GetMapping("/list")
    public Page<Post> list(Pageable pageable) {
    	// PageRequest pageable = PageRequest.of(0, 20);
        return postsRepository.findAll(pageable);
    }

    @PostMapping("/save")
    public void save(@RequestBody PostsSaveRequestDto dto){
        postsRepository.save(dto.toEntity());
    }

    @PostMapping("/saveAll")
    public void saveAll(@RequestBody List<PostsSaveRequestDto> dtos){
        postsRepository.saveAll(PostsSaveRequestDto.toEntity(dtos));
    }

    @PostMapping("/update")
    public void update(@PathVariable Long id, @RequestBody PostsSaveRequestDto dto){
    	Post post = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. id=" + id));
        post.update(dto);
    }
}
