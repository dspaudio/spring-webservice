package family.namkang.webservice.dto;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class PageRequestDto {
    private int maxPagesPerBlock = 100;
    private int maxElementsPerPage = 1000;

    private int pagesPerBlock = 10;
    private int elementsPerPage = 20;

	private Sort sort = Sort.by(Direction.DESC, "createdDate");
    private int requestPage = 1;

	public void setPagesPerBlock(int pagesPerBlock) {
		if ( pagesPerBlock > this.maxPagesPerBlock ) {
    		this.pagesPerBlock = this.maxPagesPerBlock;
    	} else if ( pagesPerBlock > 0 ) {
    		this.pagesPerBlock = pagesPerBlock;
    	}
	}
	public void setElementsPerPage(int elementsPerPage) {
		if ( elementsPerPage > this.maxElementsPerPage ) {
    		this.elementsPerPage = this.maxElementsPerPage;
    	} else if ( elementsPerPage > 0 ) {
    		this.elementsPerPage = elementsPerPage;
    	}
	}
    public void setSort(Sort sort) {
    	if ( sort != null ) {
    		this.sort = sort;
    	}
    }
	public void setRequestPage(int requestPage) {
		if ( requestPage > 0 ) {
			this.requestPage = requestPage;
		}
	}
    
    public PageRequest getPageRequestFromDto() {
    	return PageRequest.of(this.requestPage-1, this.elementsPerPage, this.sort);
    }
}
