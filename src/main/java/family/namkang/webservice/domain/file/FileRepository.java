package family.namkang.webservice.domain.file;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface FileRepository extends JpaRepository<File, Long> {
	public boolean existsByFileId(String fileId);
	
	@Transactional
    @Modifying
    @Query("delete from File f where f.fileId in :fileIds")
    public void deleteAllByFileIdInQuery(@Param("fileIds") List<String> fileIds);

}
