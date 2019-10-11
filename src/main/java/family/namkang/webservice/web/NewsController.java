package family.namkang.webservice.web;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.SortDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import family.namkang.webservice.dto.news.NewsListDto;
import family.namkang.webservice.service.news.NewsService;
import lombok.AllArgsConstructor;

@Controller  
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private NewsService newsService;

    @GetMapping("/list")
    public void list(Model model, @RequestParam Map<String, String> params, 
    		@SortDefault.SortDefaults({@SortDefault(sort="id", direction=Sort.Direction.DESC)}) Pageable pageable) {
    	// todo : input validation
    	
    	Page<NewsListDto> listData = newsService.findAll(params, pageable);
    	model.addAttribute("listData", listData);
    	
    	
    }
}
