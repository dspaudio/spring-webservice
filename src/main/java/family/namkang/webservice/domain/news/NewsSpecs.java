package family.namkang.webservice.domain.news;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.domain.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class NewsSpecs {
    
    public static Specification<News> usernameLike(final String param) {
        return new Specification<News>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            	
                Join<News, User> userJoin = root.join("createdBy");
                return cb.like(userJoin.<String> get("userName"), "%" + param + "%");
                
            }
        };
    }
    
    public static Specification<News> multiple(final Map<String, String> params) {
        return new Specification<News>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<News> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            	List<Predicate> predicates = new ArrayList<Predicate>();
            	
            	params.forEach( (key, strValue) -> {
            		
            		if (!StringUtils.hasText(key) || !StringUtils.hasText(strValue)) {
            			return; // skip this iteration
            		}
            		
	            		switch (key) {
						case "title":
							predicates.add(cb.like(root.<String>get("title"), "%" + strValue + "%"));
							break;
						case "content":
							predicates.add(cb.like(root.<String>get("content"), "%" + strValue + "%"));
							break;
						case "titlecontent":
							predicates.add(cb.or( cb.like(root.<String>get("title"), "%" + strValue + "%"), cb.like(root.<String> get("content"), "%" + strValue + "%") ));
							break;
						case "createdAfter": //기간검색에서 startDt랑 비교
							predicates.add(cb.greaterThanOrEqualTo( root.<LocalDateTime>get("createdDate"), DateTimeUtil.fromString(strValue, DateTimeUtil.Pattern.YYYYMMDD) ));
							break;
						case "createdBefore": //기간검색에서 endDt랑 비교
							predicates.add(cb.lessThan( root.<LocalDateTime>get("createdDate"), DateTimeUtil.fromString(strValue, DateTimeUtil.Pattern.YYYYMMDD).plusDays(1) ));
							break;
						case "userName":
			                Join<News, User> userJoin = root.join("createdBy");
			                predicates.add( cb.like(userJoin.<String>get("userName"), "%" + strValue + "%") );
							break;
							
						case "createdById":
			                Join<News, User> userJoin2 = root.join("createdBy");
			                predicates.add( cb.equal(userJoin2.<String>get("id"), strValue) );
							break;
						case "category":
							predicates.add(cb.like(root.<String>get("category"), "%" + strValue + "%"));
							break;
						}
            		
            	});
            	
                return cb.and( predicates.toArray(new Predicate[predicates.size()]) );                
            }
        };
    }

    
}
