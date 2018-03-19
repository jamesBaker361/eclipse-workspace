
/*
 * Generic Node class
 */
public class Node <T> {

	T data;       // generic data (String, Integer, Float)
	Node<T> next; // points to the next node in the LL
	
	Node (T data, Node<T> next) {
		this.data = data;
		this.next = next;
	}
}