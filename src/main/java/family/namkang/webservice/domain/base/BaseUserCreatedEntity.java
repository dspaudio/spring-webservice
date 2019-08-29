package family.namkang.webservice.domain.base;

import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import family.namkang.webservice.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class BaseUserCreatedEntity extends BaseTimeEntity {

    protected Long createdById;
    @ManyToOne
    @JoinColumn(name = "createdById", insertable = false, updatable = false)
    protected User createdBy;
    
    public boolean checkOwner(Long createdId){
    	if (createdId==null) {
    		return false;
    	}
    	return createdId.equals(this.createdById);
    }
}
