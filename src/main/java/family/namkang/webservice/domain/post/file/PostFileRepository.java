package family.namkang.webservice.domain.post.file;


import org.springframework.data.jpa.repository.JpaRepository;

public interface PostFileRepository extends JpaRepository<PostFile, Long> {
	public boolean existsById(String fileId);
	

}
