package family.namkang.webservice.domain.post.comment;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PostCommentRepository extends JpaRepository<PostComment, Long> {
    public Page<PostComment> findByPostId(Long postId, Pageable pageable);

}
