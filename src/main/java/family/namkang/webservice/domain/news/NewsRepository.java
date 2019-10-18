package family.namkang.webservice.domain.news;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsRepository extends JpaRepository<News, Long> {
    public Page<News> findAll(Specification<News> spec, Pageable pageable);
}