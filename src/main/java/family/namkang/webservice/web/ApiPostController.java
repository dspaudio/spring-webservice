package family.namkang.webservice.web;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import family.namkang.webservice.dto.post.PostListDto;
import family.namkang.webservice.service.post.PostService;
import lombok.AllArgsConstructor;

@RestController  //REST API 용으로 만들때만 사용하기. return값이 그대로 response 내용
@AllArgsConstructor
@RequestMapping("/api/post")
public class ApiPostController {

    private PostService postsService;

    //테스트용 쓰지마로!!
    @GetMapping("/listAll")
    public List<PostListDto> listAll() {
        return postsService.findAllByOrderByGroupNoDescInGroupOrderDesc();
    }
    
    @GetMapping("/list")
    public Page<PostListDto> list(Pageable pageable) {
        return postsService.findAllByOrderByGroupNoDescInGroupOrderDesc(pageable);
    }

//    @PostMapping("/save")
//    public void save(@RequestBody PostsSaveRequestDto dto){
//    	postRepository.save(dto.toEntity());
//    }

//    @PostMapping("/saveAll")
//    public void saveAll(@RequestBody List<PostsSaveRequestDto> dtos){
//    	postRepository.saveAll(PostsSaveRequestDto.toEntity(dtos));
//    }

//    @PostMapping("/update")
//    public void update(@PathVariable Long id, @RequestBody PostsSaveRequestDto dto){
//    	Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. id=" + id));
//        post.update(dto);
//    }
}
