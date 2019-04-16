package main.java.com.excilys.models;  

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Page<T> {
	
	// ******* VARIABLES *******
	private List<T> data;
	private int index, size;
	private final int PAGE_SIZE = 15;
	private String sorted;
	
	private int toBeginIndex, toEndIndex, previous10Index, next10Index, previous50Index, next50Index;
	
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
	
	public String getSorted() {
		return this.sorted;
	}
	
	public int getPrevious10Index() {
		return previous10Index;
	}
	
	public int getNext10Index() {
		return next10Index;
	}

	public int getPrevious50Index() {
		return previous50Index;
	}
	
	public int getNext50Index() {
		return next50Index;
	}

	public int getToBeginIndex() {
		return toBeginIndex;
	}

	public int getToEndIndex() {
		return toEndIndex;
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
	
	public void setSorted(String sorted) {
		this.sorted = sorted;
	}
	
	public void setPrevious10Index(int previous10Index) {
		this.previous10Index = previous10Index;
	}
	
	public void setNext10Index(int next10Index) {
		this.next10Index = next10Index;
	}	
	
	public void setPrevious50Index(int previous50Index) {
		this.previous50Index = previous50Index;
	}
	public void setNext50Index(int next50Index) {
		this.next50Index = next50Index;
	}
	
	public void setToBeginIndex(int toBeginIndex) {
		this.toBeginIndex = toBeginIndex;
	}
	
	public void setToEndIndex(int toEndIndex) {
		this.toEndIndex = toEndIndex;
	}
	
	// ******* PAGINATION METHODS *******
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
	
	// ******* DISPLAY METHODS *******
	public List<Computer> displayComputerList(ArrayList<Computer> computers){
		return computers.subList(getIndex(), PAGE_SIZE);
	}

	public List<Company> displayCompanyList(ArrayList<Company> company){
		return company.subList(getIndex(), PAGE_SIZE);
	}

	public void sortByName(List<ComputerDTO> computers) {
		
		computers.sort(new Comparator<ComputerDTO>() {

			@Override
			public int compare(ComputerDTO computer1, ComputerDTO computer2) {
				
				if (computer2.getComputerName() == null) {
					return 1;
				}
				
				else if (computer1.getComputerName() == null ){
					return -1;
				}
				
				else {
					return computer1.getComputerName().compareTo(computer2.getComputerName());
				}
			}
			
		});

	}
	
	public void sortByCompany(List<ComputerDTO> computers) {
		
		computers.sort(new Comparator<ComputerDTO>() {

			@Override
			public int compare(ComputerDTO computer1, ComputerDTO computer2) {
				
				if (computer2.getCompanyName() == null) {
					return -1;
				}
				
				else if (computer1.getCompanyName() == null ){
					return 1;
				}
				
				else {
					return computer1.getCompanyName().compareTo(computer2.getCompanyName());
				}
			}
			
		});

	}
	
	public void sortByIntroduced(List<ComputerDTO> computers) {
		
		computers.sort(new Comparator<ComputerDTO>() {

			@Override
			public int compare(ComputerDTO computer1, ComputerDTO computer2) {
				
				if (computer2.getIntroducedDate() == null) {
					return -1;
				}
				
				else if (computer1.getIntroducedDate() == null ){
					return 1;
				}
				
				else {
					return computer1.getIntroducedDate().compareTo(computer2.getIntroducedDate());
				}
			}
			
		});

	}
	
	public void sortByDiscontinued(List<ComputerDTO> computers) {
		
		computers.sort(new Comparator<ComputerDTO>() {

			@Override
			public int compare(ComputerDTO computer1, ComputerDTO computer2) {
				
				if (computer2.getDiscontinuedDate() == null) {
					return -1;
				}
				
				else if (computer1.getDiscontinuedDate() == null ){
					return 1;
				}
				
				else {
					return computer1.getDiscontinuedDate().compareTo(computer2.getDiscontinuedDate());
				}
			}
			
		});

	}
	
	// ******* EQUALS METHODS *******
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