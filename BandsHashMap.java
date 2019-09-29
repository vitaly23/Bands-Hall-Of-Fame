package Ex2;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class BandsHashMap implements Map<String,Band> {
	
	private final double LOAD_FACTOR= 0.75;
	private final static int INITIAL_CAPACITY = 10;
	
	private BandsEntry entries[];
	private int size =0;
	private int capacity=4;
	
	
	public BandsHashMap() {
		this(INITIAL_CAPACITY);
	}

	public BandsHashMap(int initialCapacity) {
		this.capacity = trimToPowerOf2(initialCapacity);
		entries = new BandsEntry[capacity];
	}

	public void rehash() {
		Set<Map.Entry<String,Band>> set = entrySet(); // Get entries
	    capacity <<= 1; // Double capacity    
	    entries = new BandsEntry[capacity]; // Create a new bands entries
	    size = 0; // Reset size to 0
	    for (Map.Entry<String, Band> entry: set) {
	    	put(entry.getKey(), entry.getValue()); // Store to new table
	    }
	}


	private int trimToPowerOf2(int initialCapacity) {
		int capacity = 1;
	    while (capacity < initialCapacity)
	    	capacity <<= 1;
	    
	    return capacity;
	}



	private int hash(int hashCode) {
		return supplementalHash(hashCode) & (capacity -1);
	}
	
	private static int supplementalHash(int h){
		h ^= (h >>> 20) ^ (h >>> 12);
	    return h ^ (h >>> 7) ^ (h >>> 4);
	  }
	 
	@Override
	public int size() {
		return size;
	}
	@Override
	public boolean isEmpty() {
		return size==0;
	}
	@Override
	public boolean containsKey(Object key) {
		int index = hash(key.hashCode());
		return entries[index] != null && entries[index].equals(key);
	}
	@Override
	public boolean containsValue(Object value) {
		for(int i=0;i<capacity;i++)
			if(entries[i].getValue().equals(value))
				return true;
		return false;
	}
	@Override
	public Band get(Object key) {
		int index = hash(key.hashCode());
		return entries[index].getValue();
	}
	
	@Override
	public Band put(String key, Band value) {//put
		int index = hash(key.hashCode());
		//if load factor is reached
		if(size+1 >= capacity*LOAD_FACTOR)
			rehash();
		
		//if its the same key
		if(entries[index] != null && entries[index].getKey().equals(key)) {
			Band oldValue = entries[index].getValue();
			entries[index].setValue(value);
			return oldValue;
		}
		//if its a different key in the same index
		else if(entries[index] != null && !entries[index].getKey().equals(key)) {
			rehash();
			//after rehash re-evaluating the key and the value
			put(key,value);
		}
		//if key index is null
		if(entries[index] == null) 
			entries[index] = new BandsEntry(key, value);
			size++;
			return null;
		
	}
	@Override
	public Band remove(Object key) {
		int index = hash(key.hashCode());
		//if its empty
		if(entries[index] == null)
			return null;
		else {
			//if there is an object
			Band b = entries[index].getValue();
			entries[index] = null;
			return b;
		}
	}
	
	@Override
	public void putAll(Map<? extends String, ? extends Band> m) {
		Set<?> set = m.entrySet();
		
		for(Object entry : set)
			put(((BandsEntry) entry).getKey(),((BandsEntry) entry).getValue());	
	}
	
	@Override
	public void clear() {
		size=0;
		
	}
	@Override
	public Set<String> keySet() {
		Set<String> set = new HashSet<>();
		
		for (Entry<String,Band> entry: entrySet()) {
			set.add(entry.getKey());
		}
			
				return null;
	}
	@Override
	public Collection<Band> values() {
		LinkedList<Band> values = new LinkedList<>();
				for(BandsEntry e : entries)
					values.add(e.getValue());
		return values;
	}
	@Override
	public Set<Entry<String, Band>> entrySet() {
		Set<Entry<String,Band>> set = new HashSet<>();
		for (int i =0; i < capacity; i ++) {
			if(entries[i] != null) {
				set.add(entries[i]);
			}
		}
		return set;
	}

	
	
	static class BandsEntry implements Map.Entry<String, Band> {

		private String key;
		private Band band;
		
		
		
		public BandsEntry(String key, Band band) { //Constructor
			setKey(key);
			setValue(band);
		}
		
		public void setKey(String key) {
			this.key = key;
		}

		@Override
		public String getKey() {
			return key;
		}
		

		@Override
		public Band getValue() {
			return band;
		}

		@Override
		public Band setValue(Band value) {
			Band oldValue = this.band;
			this.band = value;
			return oldValue;
			//return band = value;
		}
		
	}



	
}
