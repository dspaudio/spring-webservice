package family.namkang.webservice.domain.post;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    public Page<Post> findAll(Specification<Post> spec, Pageable pageable);
    public Optional<Post> findByBoardIdAndId(Long boardId, Long id);
    
}