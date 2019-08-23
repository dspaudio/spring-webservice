package family.namkang.webservice.service.post;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import family.namkang.webservice.common.exception.ExceptionDataEnums;
import family.namkang.webservice.common.exception.MessageException;
import family.namkang.webservice.domain.post.Post;
import family.namkang.webservice.domain.post.PostRepository;
import family.namkang.webservice.domain.post.PostSpecs;
import family.namkang.webservice.dto.post.PostDetailDto;
import family.namkang.webservice.dto.post.PostListDto;
import family.namkang.webservice.dto.post.PostSaveDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostService {
    private PostRepository postRepository;
    
    @Transactional(readOnly = true)
    public Page<PostListDto> findAll(Map<String, String> params, Pageable pageable) {
    	
        return postRepository.findAll(PostSpecs.multiple(params), pageable)
                .map(PostListDto::new);
    }
    
    @Transactional(readOnly = true)
    public PostDetailDto findById(Long boardId, Long id) {
    	
        return postRepository.findByBoardIdAndId(boardId, id).map(PostDetailDto::new).orElseThrow(() -> new IllegalArgumentException("해당 포스트가 없습니다. id=" + id));
    }
    
    @Transactional
    public Post insert(PostSaveDto dto) throws MessageException{
    	if (dto==null) {
    		throw new NullPointerException();
    	}

        try {
        	Post post = postRepository.save(dto.toEntity());
			post.addPostFiles(dto.getFiles());
	    	return post;
		} catch (IOException e) {
			throw new MessageException(ExceptionDataEnums.FILE_SYSTEM_ERROR);
		}
    }
    
    @Transactional
    public Post update(PostSaveDto dto) throws MessageException{
    	if (dto==null) {
    		throw new NullPointerException();
    	}
    	
    	Post post = postRepository.findById(dto.getId()).orElseThrow(EntityNotFoundException::new);
    	if ( !post.checkOwner(dto.getCreatedById()) ) throw new MessageException(ExceptionDataEnums.NOT_AUTHORIZED);
    	
    	try {
			post.update(dto);
		} catch (IOException e) {
			throw new MessageException(ExceptionDataEnums.FILE_SYSTEM_ERROR);
		}
        
    	return post;
    }
    
    
}
