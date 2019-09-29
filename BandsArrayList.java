package Ex2;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;


public class BandsArrayList implements List<Band> {

	public final int INITIAL_CAPACITY = 10;
	private Band[] arr;
	private int size; // logical size

	public BandsArrayList() { // **Constructor
		arr = new Band[INITIAL_CAPACITY];
		size=0;
	}

	private void ensureCapacity() {
		if (size >= arr.length) {
			Band[] newData = new Band[size * 2 + 1];
			System.arraycopy(arr, 0, newData, 0, size);
			arr = newData;
		}
	}

	public BandsArrayList(Band[] b) { // 2nd Constructor
		this();
		for (int i = 0; i < b.length; i++) {
			add(b[i]);
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<Band> iterator() {
		return new BandsIterator();
	}

	@Override
	public Object[] toArray() {
		Band[] temp = new Band[size];
		System.arraycopy(arr, 0, temp, 0, size);
		return temp;
	}

	@Override
	public <T> T[] toArray(T[] a) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean add(Band e) {
		//System.out.println(e.getName());
		ensureCapacity();
		//add(size, e);
		arr[size] = e;
		return e.equals(arr[size++]);
	}

	@Override
	public boolean remove(Object o) {
		// if the object doesn't exist OR the object is not a Band
		if (!this.contains(o) || !(o instanceof Band)) {
			throw new NoSuchElementException("the element is not in the array");
			// else
		} else {
			for (int i = 0; i < size; i++) {
				if (arr[i].equals(o)) {
					// remove by index
					remove(i);
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean containsAll(Collection<?> c) {
		if (!c.isEmpty()) { // not null
			while (c.iterator().hasNext()) {
				if (!(contains(c.iterator().next())))
					return false;
			}
			return true;
		}
		return false;
	}

	@Override
	public boolean addAll(Collection<? extends Band> c) { 
		while (c.iterator().hasNext()) {
			ensureCapacity();
			
			//adds elements from c to the end of the list
			add(++size,(Band) c.iterator().next());
		}
		return containsAll(c);
		
	}

	@Override
	public boolean addAll(int index, Collection<? extends Band> c) {

		if (index < 0 || index > size)
			throw new IndexOutOfBoundsException();
		if (c.isEmpty())
			throw new NullPointerException();

		Band[] temp = new Band[size - index];

		// copy all the indices after "index" to a temp. array
		System.arraycopy(arr, index, temp, 0, size - index);
		int oldIndex = index;
		size = index;

		// adding the elements of "c" to the array
		while (c.iterator().hasNext()) {
			ensureCapacity();
			add(size, (Band) c.iterator().next());
			size++;

			// adding back the indices that were copied
			for (int i = 0; i < temp.length; i++) {
				ensureCapacity();
				add(size, temp[i]);
				size++;
			}
		}
			//check if they were added
		 if(containsAll(c)) {
			 for(int i =0;i<temp.length;i++) {
				 if(!(contains(temp[i])))
					 return false;
			 }
			 return true;
		 }
		 return false;
	}

	@Override
	public boolean removeAll(Collection<?> c) {
		if(c.isEmpty())
			throw new NullPointerException();
		Iterator<Band> itr = (Iterator<Band>) c.iterator();
		boolean answer = false;
		
		while(itr.hasNext())
			if(remove(itr.next()))
				answer = true;
		return answer;
	}

	@Override
	public boolean retainAll(Collection<?> c) {
		int tempSize=0;
		Band[] temp = new Band[tempSize];
		boolean isChanged=false;
		
		while(c.iterator().hasNext())
			if(contains(c.iterator().next())) {
				temp[tempSize] = (Band) c.iterator();
				tempSize++;
				isChanged = true;
			}
		arr=temp;
		return isChanged;
	}

	@Override
	public void clear() {
		size = 0;
	}

	@Override
	public Band get(int index) {
		return arr[index];
	}

	@Override
	public Band set(int index, Band element) {
		
		if(index<0 || index>size)
			throw new IndexOutOfBoundsException();
		
		Band b = arr[index];
		arr[index] = element;
		return b;
	}

	@Override
	public void add(int index, Band element) {
		if (index <= size) {
			int oldSize = size-1;
			++size;
			// shift right from index
			while (oldSize >= index) {
				arr[oldSize + 1] = arr[oldSize];
				oldSize--;
			}
			// adding the element to the index
			arr[index] = element;
		} else
			throw new IndexOutOfBoundsException("Index is out of bounds");
	}

	@Override
	public Band remove(int index) {
		
		if(index<0 || index>size)
			throw new IndexOutOfBoundsException();
		
			Band b = arr[index];
			arr[index] = null;
			int counter = index;
			while (counter < size) {
				arr[counter] = arr[counter + 1];
				arr[counter + 1] = null;
				counter++;
			}	//while
			size--;
			return b;

	}

	@Override
	public int indexOf(Object o) {
		for(int i =0; i<size;i++) {
			if(o.equals(arr[i]))
				return i;
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object o) {
		
		int index = -1;
		if (o instanceof Band) {
			for (int i = 0; i < size; i++) {
				if (o.equals(arr[i]))
					index = i;
			}
		}	
			return index;
	}

	@Override
	public ListIterator<Band> listIterator() {
		BandsListIterator itr = new BandsListIterator();
		return itr;
	}

	@Override
	public ListIterator<Band> listIterator(int index) {
		BandsListIterator itr = new BandsListIterator();
		itr.index = index;
		return itr;
	}

	@Override
	public List<Band> subList(int fromIndex, int toIndex) {
		
		List<Band> temp = new BandsArrayList();	
		
		for(int i =fromIndex; i<=toIndex; i++) {
			//adding to the end of the array
			temp.add( get(i));
		}

		return temp;
	}

	@Override
	public boolean contains(Object o) {
		if (o instanceof Band) {
			for (int i = 0; i < size; i++) {
				if (o.equals(arr[i]))
					return true;
			}
		}
		return false;
	}
	
	public void trimToSize() { //removes all null indices
		for(int i=0;i<arr.length;i++)
			if(arr[i] == null)
				remove(arr[i]);
	}
	
	

	@Override
	public void sort(Comparator<? super Band> c) {
		arr = ((Band[])toArray());
		Arrays.sort(arr, c);
		for(int i=0;i< arr.length;i++)
			set(i, arr[i]);
	}



	class BandsIterator implements Iterator<Band> { // **Iterator**
		
		private int index = 0;

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Band next() {
			if (hasNext())
				return (Band) arr[index++];

			else
				throw new NoSuchElementException("This is the end of the array");
		}

	}

	class BandsListIterator implements ListIterator<Band> { // **List Iterator
		
		
		
		private int index = 0;

		public BandsListIterator() {
		//BandsListIterator itr = new BandsListIterator();
		
		}
		
		public BandsListIterator(int index) {
			this.index=index;
		}

		@Override
		public boolean hasNext() {
			return index < size;
		}

		@Override
		public Band next() {
			if (hasNext())
				return arr[index++];         
			return null;
		}

		@Override
		public boolean hasPrevious() {
			return index > 0;
		}

		@Override
		public Band previous() {
			if (hasPrevious())
				return arr[--index];
			return null;
		}

		@Override
		public int nextIndex() {
			if (hasNext()) {
				int a = index + 1;
				return a;
			}
			return -1;

		}

		@Override
		public int previousIndex() {
			if (hasPrevious()) {
				int b = index - 1;
				return b;
			}
			return -1;
		}

		@Override
		public void remove() {
			arr[index] = null;
			int newIndex = index;
			while (newIndex < size-1) {
				arr[newIndex] = arr[newIndex + 1];
				newIndex++;
			}
			size--;
			
		}

		@Override
		public void set(Band e) {
			arr[index] = e;
		}

		@Override
		public void add(Band e) {
			//adding the element to the current index
			BandsArrayList.this.add(index,e);
			
		}

	}

}
