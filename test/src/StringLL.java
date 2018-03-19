
public class StringLL<T> {

	Node<T> front;
	int size;
	
	public StringLL() {
		// TODO Auto-generated constructor stub
		front=null;
		size=0;
	}
	
	public void addToFront(T data) {
		front=new Node<T>(data,front);
		size++;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
