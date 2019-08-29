package family.namkang.webservice.domain.base.post;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.MappedSuperclass;

import family.namkang.webservice.domain.base.BaseUserCreatedEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter(AccessLevel.PROTECTED)
@MappedSuperclass
public abstract class BasePostEntity extends BaseUserCreatedEntity {

    @Column(nullable = false, length = 300)
    protected String title;

    @Lob
    @Column(nullable = false)
    @Basic(fetch = FetchType.LAZY)
    protected String content;

}
