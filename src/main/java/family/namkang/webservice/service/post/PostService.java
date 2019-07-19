package family.namkang.webservice.service.post;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import family.namkang.webservice.domain.post.PostRepository;
import family.namkang.webservice.domain.post.PostSpecs;
import family.namkang.webservice.dto.post.PostDetailDto;
import family.namkang.webservice.dto.post.PostListDto;
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
    
//    @Transactional
//    public Long save(PostsSaveDto dto){
//        return postRepository.save(dto.toEntity()).getId();
//    }
}
