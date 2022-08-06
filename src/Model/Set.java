package Model;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Set<K,V> implements Serializable {
	private int length=0;
	private ArrayList<K> keyList=new ArrayList<K>();
	private ArrayList<V> valueList=new ArrayList<V>();
	

	public Set() {};
	
	public int getLength() {
		return length;
	}
	
	public K getKey(int i) {
		return keyList.get(i);
	}
	
	public V getValue (int i) {
		return valueList.get(i);
	}
	public Boolean setKey(int index,K key) {
		keyList.set(index, key);
		return true;
	}
	public Boolean setValue(int index,V value) {
		valueList.set(index, value);
		return true;
	}

	public void setKeyList(ArrayList<K> keyList) {
		this.keyList = keyList;
	}

	public void setValueList(ArrayList<V> valueList) {
		this.valueList = valueList;
	}

	public boolean add(K key,V value) {
		for(int i=0;i<length;i++) { //check if the key already exist 
			if(keyList.get(i)==key)
				return false;
		}
		keyList.add(key);
		valueList.add(value);
		
		length++;
		return true;
		
	}
	
	public boolean remove(K key) {
		for(int i=0;i<length;i++) { //if the key exist in the list then remove it and return true
			if(keyList.get(i)==key) {
				keyList.remove(i);
				valueList.remove(i);
				length--;
				return true;
			}
				
		}
		return false;
	}
	
	
}
