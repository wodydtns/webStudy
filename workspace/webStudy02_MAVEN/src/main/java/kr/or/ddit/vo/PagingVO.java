package kr.or.ddit.vo;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PagingVO<T> {
	
	public PagingVO(int screenSize, int blockSize) {
		super();
		this.screenSize = screenSize;
		this.blockSize = blockSize;
	}

	private int screenSize=10;
	private int blockSize=5;
	private int currentPage;
	
	private int totalRecord;
	private int totalPage;
	
	private int startRow;
	private int endRow;
	
	private int startPage;
	private int endPage;
	
	private List<T> dataList;
	private SearchVO searchVO; // 일반 검색
	
	private T searchDetail; // 상세 검색
	
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
		totalPage = (int)Math.ceil(totalRecord/(double)screenSize);
	}
	
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
		endRow = screenSize * currentPage;
		startRow = endRow - (screenSize - 1);
		endPage = blockSize * ((currentPage+(blockSize-1))/blockSize);
		startPage = endPage - (blockSize-1);
	}
	
	public String getPagingHTML() {
		StringBuffer html = new StringBuffer();
		html.append("<nav aria-label='...'>\n");
		html.append("<ul class='pagination'>\n");
	 	int lastPage = endPage>totalPage ?
	 						totalPage : endPage;
	 	if(startPage>blockSize){
	 		html.append("<li class='page-item'>\n");
	 		html.append("<a class='page-link' data-page='"+(startPage-blockSize)+"' href='?page="+(startPage-blockSize)+"' tabindex='-1' aria-disabled='true'>Previous</a>\n");
	 		html.append("</li>\n");
	 	}
	 	for(int i=startPage; i<=lastPage; i++){
	 		if(i==currentPage){
	 			html.append("<li class='page-item active' aria-current='page'>\n");
	 			html.append("<a data-page='"+i+"' class='page-link' href='#'>"+i+"<span class='sr-only'>(current)</span></a>\n");
	 			html.append("</li>\n");
	 		}else{
	 			html.append("<li class='page-item'>\n");
	 			html.append("<a class='page-link'data-page='"+i+"' href='?page="+i+"'>"+i+"</a>\n");
	 			html.append("</li>\n");
	 		}
	 	}
	 	if(lastPage<totalPage){
	 		html.append("<li class='page-item'>\n");
	 		html.append("<a class='page-link' data-page='"+(lastPage + 1)+"' href='?page="+(lastPage + 1)+"'>Next</a>\n");
	 		html.append("</li>\n");
	 	}
	 	html.append("</ul>\n");
	 	html.append("</nav>\n");
	 	return html.toString();
	}
	
}














