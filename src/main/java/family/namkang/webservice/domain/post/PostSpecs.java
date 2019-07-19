package family.namkang.webservice.domain.post;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import family.namkang.webservice.common.util.DateTimeUtil;
import family.namkang.webservice.domain.board.Board;
import family.namkang.webservice.domain.board.category.BoardCategory;
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

public class PostSpecs {
    
    public static Specification<Post> usernameLike(final String param) {
        return new Specification<Post>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
            	
                Join<Post, User> userJoin = root.join("createdBy");
                return cb.like(userJoin.<String> get("userName"), "%" + param + "%");
                
            }
        };
    }
    
    public static Specification<Post> multiple(final Map<String, String> params) {
        return new Specification<Post>() {
            private static final long serialVersionUID = 1L;
            @Override
            public Predicate toPredicate(Root<Post> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
			                Join<Post, User> userJoin = root.join("createdBy");
			                predicates.add( cb.like(userJoin.<String>get("userName"), "%" + strValue + "%") );
							break;
							
						case "createdById":
			                Join<Post, User> userJoin2 = root.join("createdBy");
			                predicates.add( cb.equal(userJoin2.<String>get("id"), strValue) );
							break;
						case "boardId":
			                Join<Post, Board> boardJoin = root.join("board");
			                predicates.add( cb.equal(boardJoin.<String>get("id"), strValue) );
							break;
						case "boardCategoryId":
			                Join<Post, BoardCategory> boardCategoryJoin = root.join("board");
			                predicates.add( cb.equal(boardCategoryJoin.<String>get("id"), strValue) );
							break;
						}
            		
            	});
            	
                return cb.and( predicates.toArray(new Predicate[predicates.size()]) );                
            }
        };
    }

    
}
