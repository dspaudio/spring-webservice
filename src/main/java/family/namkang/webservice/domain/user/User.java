package family.namkang.webservice.domain.user;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicInsert;
import org.springframework.util.StringUtils;

import family.namkang.webservice.domain.base.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@DynamicInsert  //insert 시 null 인필드 제외
@Getter
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 100, nullable = false)
    private String userName;

    @Column(length = 100, nullable = false)
    private String password;

    @Builder
    public User(String userName, String password) {
        this.userName = userName;
        this.password = StringUtils.hasText(password) ? password:"1234";
    }
    
    public void updateName(String userName) {
        this.userName = userName;
    }
    
    public void updatePassword(String password) {
        this.password = password;
    }
}
