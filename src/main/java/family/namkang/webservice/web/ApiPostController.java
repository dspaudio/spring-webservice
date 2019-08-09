package family.namkang.webservice.web;

import java.io.IOException;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.dto.post.PostDetailDto;
import family.namkang.webservice.dto.post.PostListDto;
import family.namkang.webservice.dto.post.PostSaveDto;
import family.namkang.webservice.service.post.PostService;
import lombok.AllArgsConstructor;

@RestController  //REST API 용으로 만들때만 사용하기. return값이 그대로 response 내용
@AllArgsConstructor
@RequestMapping("/api/post")
public class ApiPostController {

    private PostService postsService;
    
    @GetMapping("/list")
    public Page<PostListDto> list(@RequestParam Map<String, String> params, 
    		@SortDefault.SortDefaults({@SortDefault(sort="id", direction=Sort.Direction.DESC)}) Pageable pageable) {
    	// todo : validation
        return postsService.findAll(params, pageable);
    }
    
    @GetMapping("/detail")
    public PostDetailDto detail(@RequestParam Long boardId, @RequestParam Long id) {
    	// todo : validation
		return postsService.findById(boardId, id);
        
    }

    @PostMapping("/create")
    public void create(PostSaveDto dto){
    	
    	try {
			Post created = postsService.create(dto);
		} catch (IOException e) {
			// file save하는데 에러 발생
			e.printStackTrace();
		}
    }

    @PostMapping("/update")
    public void update(PostSaveDto dto){
    	
    	try {
			Post updated = postsService.update(dto);
		} catch (IOException e) {
			// file save하는데 에러 발생
			e.printStackTrace();
		}
    }
    
}
