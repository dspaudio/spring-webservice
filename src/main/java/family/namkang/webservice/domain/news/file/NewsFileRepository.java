package family.namkang.webservice.domain.news.file;


import org.springframework.data.jpa.repository.JpaRepository;

public interface NewsFileRepository extends JpaRepository<NewsFile, Long> {
	public boolean existsById(String fileId);
	

}
