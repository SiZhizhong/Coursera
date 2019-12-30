import sun.text.CompactByteArray;

public class MaxPQ <Key extends Comparable<Key>> {
	private int capacity=20;//整个数组的大小
	private Key[] keys;
	private int length=0;//元素的个数，不包含第一个空元素
	public MaxPQ() {
		keys=(Key[]) new Comparable[capacity];
	}
	
	public void insert(Key key) {
		if(length+1>=capacity) {
			//double the size
			capacity+=capacity;
			Key[]temp= (Key[]) new Comparable[capacity];
			for(int i=1;i<=length;i++) {
				temp[i]=keys[i];
			}
			keys=temp;
			temp=null;
		}
		else {
			length+=1;
			keys[length]=key;
			swimup(length);
		}
	}
	
	private void swimup(int index) {
		while(index/2>=1 &&keys[index].compareTo(keys[index/2])>0) {
			swap(index,index/2);
			index=index/2;
		}
	}
	private void swap(int indexa,int indexb) {
		Key temp=keys[indexa];
		keys[indexa]=keys[indexb];
		keys[indexb]=temp;
	}
	
	public void del(Key key) {
		
	}
	
	private void swimdown(int index) {
		while(2*index<=length) {
			int child=index*2;
			if(child<length&&keys[child].compareTo(keys[child+1])<0) child++;
			if(keys[index].compareTo(keys[child])>=0) break;
			swap(index,child);
			index=index/2;
		}
	}
	
	public Key getMax() {
		return null;
	}
	
	public Key delMax() {
		return null;
	}

}
