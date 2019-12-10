public class MyStudent implements Comparable<MyStudent>
{
	private int score;
	private String name;
	
	public MyStudent(String name,int score) {
		// TODO Auto-generated constructor stub
		this.name=name;
		this.score=score;
	}

	@Override
	public int compareTo(MyStudent o) {
		// TODO Auto-generated method stub
		if (this.score<o.score) return -1;
		if (this.score>o.score) return 1;
		return 0;
	}
	public boolean less(MyStudent o) {
		return this.compareTo(o)==-1;
	}
	
}