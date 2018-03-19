
public class Node<T> {

	public T data;
	Node<T> next;
	public Node() {
		// TODO Auto-generated constructor stub
	}
	
	public Node(T dat) {
		this.data=dat;
	}
	
	public Node(T dat, Node nex) {
		this.data=dat;
		this.next=nex;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
