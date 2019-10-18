package family.namkang.webservice.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PageResponseDto<T> {
	List<T> elementsList;
	
    private int pagesPerBlock;
    private int elementsPerPage;
	private ArrayList<Sort.Order> orders;
	
    private long totalElements;
    private int totalPages;
    private int currentPage = 1;
    private int blockStart;
    private int blockEnd;
    private boolean firstPage;
    private boolean lastPage;
    private boolean firstBlock;
    private boolean lastBlock;
    
    @Builder
    public PageResponseDto(Page<T> page, int pagesPerBlock) {
    	this.elementsList = page.getContent();
    	
    	this.pagesPerBlock = pagesPerBlock<1 ? 1:pagesPerBlock;
    	
    	this.elementsPerPage = page.getSize();
    	this.orders = new ArrayList<Sort.Order>();
      	page.getSort().iterator().forEachRemaining(this.orders::add);
    	
    	this.totalElements = page.getTotalElements();
    	this.totalPages = page.getTotalPages();
    	this.currentPage = page.getNumber()+1;
    	
    	this.blockStart = (page.getNumber() / this.pagesPerBlock) * this.pagesPerBlock +1; 
    	this.blockEnd = this.totalPages < this.blockStart+this.pagesPerBlock-1 ? this.totalPages : this.blockStart+this.pagesPerBlock-1;
    	
    	this.firstPage = this.currentPage == 1;
    	this.lastPage = this.currentPage == this.totalPages;
    	this.firstBlock = this.blockStart == 1;
    	this.lastBlock = this.blockEnd == this.totalPages;
    }

}
