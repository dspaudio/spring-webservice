package family.namkang.webservice.domain.user.authority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.GrantedAuthority;

import family.namkang.webservice.domain.user.User;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class UserAuthority implements GrantedAuthority{

    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private Long userId;
    @ManyToOne
    @JoinColumn(name = "userId", insertable = false, updatable = false)
    private User user;

    @Column(length = 100, nullable = false)
    private String authority;

    @Builder
    public UserAuthority(Long userId, String authority) {
    	this.userId = userId;
    	this.authority = authority;
    }
    
}
