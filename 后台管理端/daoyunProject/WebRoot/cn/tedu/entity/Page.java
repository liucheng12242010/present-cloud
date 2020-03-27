package cn.tedu.entity;

/**
 * 用于分页的实体类
 * @author cdfengyang
 *
 */
public class Page {
	private int page;
	private int rows;
	private int start;
	public Page(int page, int rows) {
		super();
		this.page = page;
		this.rows = rows;
		
	}
	public Page() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getStart() {
		return (page-1)*start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	

}
