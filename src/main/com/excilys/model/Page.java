package main.com.excilys.model;

import java.util.List;

public class Page<T> {
	// ******* VARIABLES *******
	private int start, size;
	private List<T> data;
	private final int NB_ELEMENTS = 15;
	
	// ******* CONSTRUCTEURS *******
	public Page(List<T> data){
		super();
		this.setData(data);
		this.setSize(data.size());
		this.setStart(0);
	}
	
	// ******* GETTERS *******
	public List<T> getData() {
		return this.data;
	}

	public int getSize() {
		return this.size;
	}
	
	public int getStart() {
		return this.start;
	}
	
	// ******* SETTERS *******
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public void setStart(int start) {
		this.start = start;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	// ******* OTHER METHODS *******
	public void next() {
		this.start = Math.min( this.start + NB_ELEMENTS, this.size - 1 );
	}
	
	public void previous() {
		this.start = Math.max( this.start - NB_ELEMENTS,  0);
	}
	
	public List<T> getPageData(){
		return this.data.subList( this.start, Math.min( this.start + NB_ELEMENTS, this.size) );
	}
	
}
