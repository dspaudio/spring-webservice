package family.namkang.webservice.service.post;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import family.namkang.webservice.domain.post.PostRepository;
import family.namkang.webservice.dto.post.PostListDto;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class PostService {
	private PostRepository postRepository;

    @Transactional(readOnly = true)
    public List<PostListDto> findAllByOrderByGroupNoDescInGroupOrderDesc() {
        return postRepository.findAllByOrderByGroupNoDescInGroupOrderDesc()
                .map(PostListDto::new)
                .collect(Collectors.toList());
    }
    @Transactional(readOnly = true)
    public Page<PostListDto> findAllByOrderByGroupNoDescInGroupOrderDesc(Pageable pageable) {
        return postRepository.findAll(pageable)
                .map(PostListDto::new);
    }
}
