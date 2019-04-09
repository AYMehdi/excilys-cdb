package main.java.com.excilys.model;

import java.util.List;

public class Page<T> {
	// ******* VARIABLES *******
	private int index, size;
	private List<T> data;
	private final int PAGE_SIZE = 15;
	
	// ******* CONSTRUCTEURS *******
	public Page(List<T> data){
		super();
		this.setData(data);
		this.setSize(data.size());
		this.setIndex(0);
	}
	
	// ******* GETTERS *******
	public List<T> getData() {
		return this.data;
	}

	public int getSize() {
		return this.size;
	}
	
	public int getIndex() {
		return this.index;
	}
	
	// ******* SETTERS *******
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public void setIndex(int index) {
		this.index = index;
	}
	
	public void setSize(int size) {
		this.size = size;
	}
	
	// ******* OTHER METHODS *******
	public void nextPage() {
		this.index = Math.min(this.getIndex() + PAGE_SIZE, this.getSize() - 1);
	}
	
	public void previousPage() {
		this.index = Math.max( this.getIndex() - PAGE_SIZE,  0);
	}
	
	public List<T> getPageData(){
		return this.data.subList(this.index, Math.min( this.index + PAGE_SIZE, this.size));
	}
	
	public int nextIndex(){
		if(index + PAGE_SIZE < data.size()) {
			return index+ PAGE_SIZE;
		}else{
			return data.size() > 0 ? data.size()-1 : 0;
		}
	}
	
	public int previousIndex(){
		if(index >= PAGE_SIZE) {
			return index - PAGE_SIZE;
		}
		else {
			return 0;
		}
	}

	@Override
	public boolean equals(Object object) {
		if(! (object instanceof Page)) {
			return false;
		}
		
		return dataEquals(object) && sizeEquals(object);
		
	}

	private boolean dataEquals(Object object) {
		if (this.data == null)
			return (((Page<?>) object).data ==null);
		else
			return this.data.equals(((Page<?>) object).data);
	}
	
	private boolean sizeEquals(Object object) {
		if (this.getSize() == 0)
			return (((Page<?>) object).size == 0);
		else
			return (this.getSize() == ((Page<?>) object).size);
	}	
}

