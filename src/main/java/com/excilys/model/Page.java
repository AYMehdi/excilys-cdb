package com.excilys.model;

import java.util.List;

public class Page<T> {
	
// ******* VARIABLES *******
	private List<T> data;
	private int index;
	private int size;
	private final int PAGE_SIZE = 20;
	
// ******* CONSTRUCTORS *******
	/**
	 * Constructor without parameter
	 */
	public Page() {
		this.setIndex(0);
		this.setSize(0);
	}
	
	/**
	 * Constructor with one parameter
	 */
	public Page(List<T> list) {
		super();
		this.data = list;
		this.index = 0;
		this.size = list.size();
	}
	
// ******* GETTERS *******
	/**
	 * @return data SQL database data
	 */
	public List<T> getData() {
		return this.data;
	}
	
	/**
	 * @return index page index
	 */
	public int getIndex() {
		return this.index;
	}
	
	/**
	 * @return size page size
	 */
	public int getSize() {
		return this.size;
	}
	
// ******* SETTERS *******
	/**
	 * @param data SQL database data
	 */
	public void setData(List<T> data) {
		this.data = data;
		this.size = data.size();
	}
	
	/**
	 * @param index page index
	 */
	public void setIndex(int index) {
		if (index >= 0 && index < this.size) {
			this.index = index;
		}
	}
	
	/**
	 * @param size page size
	 */
	public void setSize(int size) {
		this.size = size;
	}
	
// ******* OTHER METHODS *******
	/**
	 * @return page opened
	 */
	public int start() {
		return this.index * this.PAGE_SIZE;
	}
	
	/**
	 * @return page closed
	 */
	public int end() {
		return (this.index + 1) * this.PAGE_SIZE - 1;
	}
	
	/**
	 * @return index page initialized
	 */
	public int startIndex() {
		if (this.index < 3) {
			return 1;
		}
		return this.index - 2;
	}
	
	/**
	 * @return last index page
	 */
	public int endIndex() {
		if (this.index > getMaxPages() - 3) {
			return getMaxPages();
		}
		return this.index + 2;
	}
	
	/**
	 * @return previous index page
	 */
	public int previousPage() {
		if (this.index * this.PAGE_SIZE > this.PAGE_SIZE) {
			return this.index - 1;
		} else {
			return 0;
		}
	}
	
	/**
	 * @return next index page
	 */
	public int nextPage() {
		if (this.index * this.PAGE_SIZE < this.size) {
			return this.index + 1;
		} else {
			return this.index;
		}
	}
	
	/**
	 * @return index page max
	 */
	public int getMaxPages() {
		return this.size / this.PAGE_SIZE + ((this.size % this.PAGE_SIZE > 0) ? 1 : 0);
	}
}