package family.namkang.webservice.service.post.comment;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import family.namkang.webservice.common.exception.ExceptionDataEnums;
import family.namkang.webservice.common.exception.MessageException;
import family.namkang.webservice.domain.post.comment.PostComment;
import family.namkang.webservice.domain.post.comment.PostCommentRepository;
import family.namkang.webservice.dto.post.comment.PostCommentListDto;
import family.namkang.webservice.dto.post.comment.PostCommentSaveDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostCommentService {
    private PostCommentRepository postCommentRepository;
    
    @Transactional(readOnly = true)
    public Page<PostCommentListDto> findByPostId(Long postId, Pageable pageable) {
    	
        return postCommentRepository.findByPostId(postId, pageable).map(PostCommentListDto::new);
    }
    
    @Transactional(readOnly = true)
    public PostCommentListDto findById(Long id) {
    	
        return postCommentRepository.findById(id).map(PostCommentListDto::new).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. id=" + id));
    }
    
    @Transactional
    public PostComment insert(PostCommentSaveDto dto) {
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	PostComment postComment = postCommentRepository.save(dto.toEntity());
        
    	return postComment;
    }
    
    @Transactional
    public PostComment update(PostCommentSaveDto dto) throws MessageException {
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	PostComment postComment = postCommentRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
    	if ( !postComment.checkOwner(dto.getCreatedById()) ) throw new MessageException(ExceptionDataEnums.NOT_AUTHORIZED);
    	postComment.update(dto);
        
    	return postComment;
    }
    
    @Transactional
    public PostComment delete(PostCommentSaveDto dto) throws MessageException {
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	PostComment postComment = postCommentRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
    	if ( !postComment.checkOwner(dto.getCreatedById()) ) throw new MessageException(ExceptionDataEnums.NOT_AUTHORIZED);
    	
    	postCommentRepository.deleteById( dto.getId() );
        
    	return postComment;
    }
    
    
}
