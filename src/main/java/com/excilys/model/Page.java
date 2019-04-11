package main.java.com.excilys.model;

import java.util.List;

public class Page<T> {
	
	// ******* VARIABLES *******
	private List<T> data;
	private int index, size;
	private final int PAGE_SIZE = 15;
	
	// ******* CONSTRUCTEURS *******
	public Page() {
		this.setIndex(0);
		this.setSize(0);
	}
	
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
		if (this.getSize() == 0) {
			this.setIndex(0);
		} else {
			this.setIndex(Math.min(this.getIndex() + PAGE_SIZE, this.getSize() - 1));
		}
	}
	
	public void previousPage() {
		if (this.getSize() == 0) {
			this.setIndex(0);
		} else {
			this.setIndex(Math.max(this.getIndex() - PAGE_SIZE, 0));
		}
	}
	
	public List<T> getPageData(){
		if (this.getSize() == 0) {
			return data;
		} else {
			return this.data.subList(this.getIndex(), Math.min(this.getIndex() + PAGE_SIZE, this.getSize()));
		}
	}
	
	public int nextIndex(){
		if(this.getIndex() + PAGE_SIZE < data.size()) {
			return index+ PAGE_SIZE;
		} else {
			return data.size() > 0 ? data.size()-1 : 0;
		}
	}
	
	public int previousIndex(){
		if(this.getIndex() >= PAGE_SIZE) {
			return index - PAGE_SIZE;
		} else {
			return 0;
		}
	}

	public void jumpTo(int index) {
		int jumpedIndex;
		jumpedIndex = Math.min(Math.max(0, index), this.getSize() - 1);
		this.setIndex(jumpedIndex);
	}
	
	@Override
	public boolean equals(Object object) {
		if(!(object instanceof Page)) {
			return false;
		}
		
		return dataEquals(object) && sizeEquals(object);
	}

	private boolean dataEquals(Object object) {
		if (this.getData() == null)
			return (((Page<?>) object).getData() == null);
		else
			return this.data.equals(((Page<?>) object).data);
	}
	
	private boolean sizeEquals(Object object) {
		if (this.getSize() == 0)
			return (((Page<?>) object).getSize() == 0);
		else
			return (this.getSize() == ((Page<?>) object).size);
	}	
}

