package family.namkang.webservice.controller;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import family.namkang.webservice.common.exception.MessageException;
import family.namkang.webservice.common.util.EnumUtil;
import family.namkang.webservice.dto.PageRequestDto;
import family.namkang.webservice.dto.PageResponseDto;
import family.namkang.webservice.dto.news.NewsListDto;
import family.namkang.webservice.dto.news.NewsSaveDto;
import family.namkang.webservice.service.news.NewsService;
import lombok.AllArgsConstructor;

@Controller  
@AllArgsConstructor
@RequestMapping("/news")
public class NewsController {
    private NewsService newsService;

    @GetMapping("/list")
    public void list(Model model, @RequestParam Map<String, String> params, PageRequestDto pageRequestDto) {
    	pageRequestDto.setElementsPerPage(10);
    	pageRequestDto.setPagesPerBlock(5);
    	pageRequestDto.setSort(Sort.by(Direction.DESC, "createdDate"));
    	
    	Page<NewsListDto> newsPage = newsService.findAll(params, pageRequestDto.getPageRequestFromDto());
    	PageResponseDto<NewsListDto> newsPageResponseDto = new PageResponseDto<NewsListDto>(newsPage, pageRequestDto.getPagesPerBlock());
    	
    	model.addAttribute("newsPageResponseDto", newsPageResponseDto);
    }
    
    @GetMapping("/detail")
    public void detail(Model model, @RequestParam Long id) throws MessageException {
    	model.addAttribute("newsDetailDto", newsService.findById(id));
    }
    
    @GetMapping("/createForm")
    public void createForm(Model model, @ModelAttribute NewsSaveDto newsSaveDto) throws MessageException {
    	model.addAttribute("newsCategories", EnumUtil.getEnumList(EnumUtil.NEWS_CATEGORY));
    }
    
    @GetMapping("/create")
    public void create(Model model, @ModelAttribute NewsSaveDto newsSaveDto, BindingResult errors) throws MessageException {
    }
}
