package family.namkang.webservice.web;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.web.bind.annotation.*;

import family.namkang.webservice.common.exception.MessageException;
import family.namkang.webservice.dto.post.PostDetailDto;
import family.namkang.webservice.dto.post.PostListDto;
import family.namkang.webservice.dto.post.PostSaveDto;
import family.namkang.webservice.dto.post.comment.PostCommentListDto;
import family.namkang.webservice.service.post.PostService;
import family.namkang.webservice.service.post.comment.PostCommentService;
import lombok.AllArgsConstructor;

@RestController  //REST API 용으로 만들때만 사용하기. return값이 그대로 response 내용
@AllArgsConstructor
@RequestMapping("/api/post")
public class ApiPostController {

    private PostService postService;
    private PostCommentService postCommentService;
    
    @GetMapping("/list")
    public Page<PostListDto> list(@RequestParam Map<String, String> params, 
    		@SortDefault.SortDefaults({@SortDefault(sort="id", direction=Sort.Direction.DESC)}) Pageable pageable) {
    	// todo : validation
        return postService.findAll(params, pageable);
    }
    
    @GetMapping("/detail")
    public PostDetailDto detail(@RequestParam Long boardId, @RequestParam Long id) {
    	// todo : validation
		return postService.findById(boardId, id);
        
    }

    @PostMapping("/create")
    public void create(PostSaveDto dto){
    	
    	try {
			postService.insert(dto);
		} catch (MessageException e) {
			// file save하는데 에러 발생
			e.printStackTrace();
		}
    }

    @PostMapping("/modify")
    public void modify(PostSaveDto dto){
    	
    	try {
			postService.update(dto);
		} catch (MessageException e) {
			// file save하는데 에러 발생
			e.printStackTrace();
		}
    }
    
    @GetMapping("/comments")
    public Page<PostCommentListDto> list(@RequestParam Long boardId, @RequestParam Long id, @SortDefault.SortDefaults({@SortDefault(sort="id", direction=Sort.Direction.DESC)}) Pageable pageable) {
    	// todo : validation
        return postCommentService.findByPostId(id, pageable);
    }
    
}
