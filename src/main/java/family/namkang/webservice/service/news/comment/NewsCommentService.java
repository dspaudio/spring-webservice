package family.namkang.webservice.service.news.comment;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import family.namkang.webservice.common.exception.ExceptionEnums;
import family.namkang.webservice.common.exception.MessageException;
import family.namkang.webservice.domain.news.comment.NewsComment;
import family.namkang.webservice.domain.news.comment.NewsCommentRepository;
import family.namkang.webservice.dto.news.comment.NewsCommentListDto;
import family.namkang.webservice.dto.news.comment.NewsCommentSaveDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NewsCommentService {
    private NewsCommentRepository newsCommentRepository;
    
    @Transactional(readOnly = true)
    public Page<NewsCommentListDto> findByNewsId(Long newsId, Pageable pageable) {
    	
        return newsCommentRepository.findByNewsId(newsId, pageable).map(NewsCommentListDto::new);
    }
    
    @Transactional(readOnly = true)
    public NewsCommentListDto findById(Long id) {
    	
        return newsCommentRepository.findById(id).map(NewsCommentListDto::new).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. id=" + id));
    }
    
    @Transactional
    public NewsComment insert(NewsCommentSaveDto dto) {
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	NewsComment newsComment = newsCommentRepository.save(dto.toEntity());
        
    	return newsComment;
    }
    
    @Transactional
    public NewsComment update(NewsCommentSaveDto dto) throws MessageException {
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	NewsComment newsComment = newsCommentRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
    	if ( !newsComment.checkOwner(dto.getCreatedById()) ) throw new MessageException(ExceptionEnums.NOT_AUTHORIZED);
    	newsComment.update(dto);
        
    	return newsComment;
    }
    
    @Transactional
    public boolean delete(NewsCommentSaveDto dto) throws MessageException {
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	NewsComment newsComment = newsCommentRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
    	if ( !newsComment.checkOwner(dto.getCreatedById()) ) throw new MessageException(ExceptionEnums.NOT_AUTHORIZED);
    	
    	newsCommentRepository.deleteById( dto.getId() );
        
    	return true;
    }
    
    
}
