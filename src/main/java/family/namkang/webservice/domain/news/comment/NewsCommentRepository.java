package family.namkang.webservice.domain.news.comment;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface NewsCommentRepository extends JpaRepository<NewsComment, Long> {
    public Page<NewsComment> findByNewsId(Long newsId, Pageable pageable);

}
