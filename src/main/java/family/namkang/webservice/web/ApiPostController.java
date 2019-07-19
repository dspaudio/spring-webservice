package family.namkang.webservice.web;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import family.namkang.webservice.dto.post.PostDetailDto;
import family.namkang.webservice.dto.post.PostListDto;
import family.namkang.webservice.service.post.PostService;
import lombok.AllArgsConstructor;

@RestController  //REST API 용으로 만들때만 사용하기. return값이 그대로 response 내용
@AllArgsConstructor
@RequestMapping("/api/post")
public class ApiPostController {

    private PostService postsService;
    
    @GetMapping("/list")
    public Page<PostListDto> list(@RequestParam Map<String, String> params, 
    		@SortDefault.SortDefaults({@SortDefault(sort="GroupNo", direction=Sort.Direction.DESC), @SortDefault(sort="inGroupOrder", direction=Sort.Direction.ASC)}) Pageable pageable) {
    	// todo : validation
        return postsService.findAll(params, pageable);
    }
    
    @GetMapping("/detail")
    public PostDetailDto detail(@RequestParam Long boardId, @RequestParam Long id) {
    	// todo : validation
		return postsService.findById(boardId, id);
        
    }

//    @PostMapping("/save")
//    public void save(@RequestBody PostsSaveRequestDto dto, MultipartFile[] files){
//        postRepository.save(dto.toEntity());
//    }

//    @PostMapping("/saveAll")
//    public void saveAll(@RequestBody List<PostsSaveRequestDto> dtos){
//        postRepository.saveAll(PostsSaveRequestDto.toEntity(dtos));
//    }

//    @PostMapping("/update")
//    public void update(@PathVariable Long id, @RequestBody PostsSaveRequestDto dto){
//        Post post = postRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. id=" + id));
//        post.update(dto);
//    }
}
