package family.namkang.webservice.domain.post;

import java.util.stream.Stream;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
	
	public Stream<Post> findAllByOrderByGroupNoDescInGroupOrderDesc();
	public Page<Post> findAll(Pageable pageable);
}