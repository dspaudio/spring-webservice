package family.namkang.webservice.domain.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public List<User> findAllByOrderByCreatedDateDesc();
    public User findByUsername(String username);
}
