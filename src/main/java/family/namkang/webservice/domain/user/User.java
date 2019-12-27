package family.namkang.webservice.domain.user;

import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;

import family.namkang.webservice.domain.base.BaseTimeEntity;
import family.namkang.webservice.domain.user.authority.UserAuthority;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class User extends BaseTimeEntity implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String username;

    @Column(length = 100, nullable = false)
    private String password;

    @Column(nullable=false, columnDefinition="Boolean default true")
    private boolean enabled;

    @OneToMany(mappedBy="userId", cascade=CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<UserAuthority> authorities;

    
    @Builder
    public User(String username, String password) {
        this.username = username;
        this.password = StringUtils.hasText(password) ? password:"password";
    }
    
    public void updateName(String username) {
        this.username = username;
    }
    
    public void updatePassword(String password) {
        this.password = password;
    }
    
    public void updateEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    

	@Override
	public boolean isAccountNonExpired() {
		// 계정이 만료되지 않았는 지 리턴한다. (true: 만료안됨)
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// 계정이 잠겨있지 않았는 지 리턴한다. (true: 잠기지 않음)
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// 비밀번호가 만료되지 않았는 지 리턴한다. (true: 만료안됨)
		return true;
	}

}
