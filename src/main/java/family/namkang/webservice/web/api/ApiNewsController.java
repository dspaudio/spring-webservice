package family.namkang.webservice.web.api;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import family.namkang.webservice.common.exception.MessageException;
import family.namkang.webservice.common.util.EnumUtil;
import family.namkang.webservice.dto.EnumCodeNameDto;
import family.namkang.webservice.dto.news.NewsDetailDto;
import family.namkang.webservice.dto.news.NewsListDto;
import family.namkang.webservice.dto.news.NewsSaveDto;
import family.namkang.webservice.dto.news.comment.NewsCommentSaveDto;
import family.namkang.webservice.service.news.NewsService;
import family.namkang.webservice.service.news.comment.NewsCommentService;
import lombok.AllArgsConstructor;

@RestController  //REST API 용으로 만들때만 사용하기. return값이 그대로 response 내용
@AllArgsConstructor
@RequestMapping("/api/news")
public class ApiNewsController {

    private NewsService newsService;
    private NewsCommentService newsCommentService;
    
    @GetMapping("/list")
    public Page<NewsListDto> list(@RequestParam Map<String, String> params, 
    		@SortDefault.SortDefaults({@SortDefault(sort="id", direction=Sort.Direction.DESC)}) Pageable pageable) {
    	// todo : input validation
        return newsService.findAll(params, pageable);
    }
    
    @GetMapping("/detail")
    public NewsDetailDto detail(@RequestParam Long id) throws MessageException {
    	// todo : input validation
		return newsService.findById(id);
        
    }

    @PostMapping("/create")
    public ResponseEntity<String> create(NewsSaveDto dto) throws MessageException, IOException{
		newsService.insert(dto);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.TEXT_PLAIN)
				.body("뉴스가 등록되었습니다. 제목: " + dto.getTitle());
    }

    @PostMapping("/modify")
    public ResponseEntity<String> modify(NewsSaveDto dto) throws MessageException, IOException{
		newsService.update(dto);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.TEXT_PLAIN)
				.body("뉴스가 수정되었습니다. 제목: " + dto.getTitle());
    }

    @PostMapping("/delete")
    public ResponseEntity<String> delete(NewsSaveDto dto) throws MessageException{
		newsService.delete(dto);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.TEXT_PLAIN)
				.body("뉴스가 삭제되었습니다. 제목: " + dto.getTitle());
    }
    
    @GetMapping("/addComment")
    public ResponseEntity<String> addComment(NewsCommentSaveDto dto) {
    	// todo : input validation
        newsCommentService.insert(dto);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.TEXT_PLAIN)
				.body("코멘트가 등록되었습니다.");
    }
    
    @GetMapping("/modComment")
    public ResponseEntity<String> modComment(NewsCommentSaveDto dto) throws MessageException {
    	// todo : input validation
		newsCommentService.update(dto);
		return ResponseEntity.status(HttpStatus.OK)
				.contentType(MediaType.TEXT_PLAIN)
				.body("코멘트가 수정되었습니다.");
    }
    @GetMapping("/delComment")
    public ResponseEntity<String> delComment(NewsCommentSaveDto dto) throws MessageException {
    	// todo : input validation
    	newsCommentService.delete(dto);
    	return ResponseEntity.status(HttpStatus.OK)
    			.contentType(MediaType.TEXT_PLAIN)
    			.body("코멘트가 삭제되었습니다.");
    }
    
    @GetMapping("/categoryList")
    public List<EnumCodeNameDto> categoryList() {
    	// todo : input validation
    	return EnumUtil.factoryEnumList().get(EnumUtil.NEWS_CATEGORY);
    }
    
}
