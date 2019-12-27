package family.namkang.webservice.domain.user.authority;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserAuthorityRepository extends JpaRepository<UserAuthority, Long> {

}
