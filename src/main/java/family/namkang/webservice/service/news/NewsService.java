package family.namkang.webservice.service.news;

import java.io.IOException;
import java.util.Map;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import family.namkang.webservice.common.exception.ExceptionEnums;
import family.namkang.webservice.common.exception.MessageException;
import family.namkang.webservice.domain.news.News;
import family.namkang.webservice.domain.news.NewsRepository;
import family.namkang.webservice.domain.news.NewsSpecs;
import family.namkang.webservice.dto.news.NewsDetailDto;
import family.namkang.webservice.dto.news.NewsListDto;
import family.namkang.webservice.dto.news.NewsSaveDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NewsService {
	
    private NewsRepository newsRepository;
    
    @Transactional(readOnly = true)
    public Page<NewsListDto> findAll(Map<String, String> params, Pageable pageable) {
    	
        return newsRepository.findAll(NewsSpecs.multiple(params), pageable)
                .map(NewsListDto::new);
    }
    
    @Transactional(readOnly = true)
    public NewsDetailDto findById(Long id) throws MessageException {
    	
        return newsRepository.findById(id).map(NewsDetailDto::new).orElseThrow(EntityNotFoundException::new);
    }
    
    @Transactional
    public News insert(NewsSaveDto dto) throws IOException{
    	if (dto==null) {
    		throw new NullPointerException();
    	}

        	News news = newsRepository.save(dto.toEntity());
			news.addNewsFiles(dto.getFiles());
	    	return news;
    }
    
    @Transactional
    public News update(NewsSaveDto dto) throws MessageException, IOException{
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	News news = newsRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
    	if ( !news.checkOwner(dto.getCreatedById()) ) throw new MessageException(ExceptionEnums.NOT_AUTHORIZED);
			news.update(dto);
        
    	return news;
    }
    
    @Transactional
    public boolean delete(NewsSaveDto dto) throws MessageException {
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	News news = newsRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
    	if ( !news.checkOwner(dto.getCreatedById()) ) throw new MessageException(ExceptionEnums.NOT_AUTHORIZED);
    	
    	newsRepository.deleteById( dto.getId() );
        
    	return true;
    }
    
    
}
