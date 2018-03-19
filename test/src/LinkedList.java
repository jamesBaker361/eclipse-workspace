import java.util.*;

public class LinkedList<T> {

	public Node<T> front=null;
	public int size=0;
	public LinkedList() {
		// TODO Auto-generated constructor stub
	}

	public void addToFront(T data) {
		Node<T> nod=new Node<T>(data);
		nod.next=front;
		front=nod;
	}
	
	public void addToBack(T data) {
		if(front==null) {
			addToFront(data);
		}else {
			Node<T> ptr=front;
			while(ptr.next!=null) {
				ptr=ptr.next;
			}
			ptr.next=new Node<T>(data);
		}
	}
	
	public void traverse(Node<T> front) {
		Node<T> ptr=front;
		while(ptr!=null) {
			System.out.print(ptr.data+" -->");
			ptr=ptr.next;
		}
	}
	
	
	
	public void traverse() {
		Node<T> ptr=front;
		while(ptr!=null) {
			System.out.print(ptr.data+" -->");
			ptr=ptr.next;
		}
		System.out.println("\n");
	}
	
	public int findIndex(Node<T> nod,T data) {
		if(nod.data=data) {
			return(0);
		}else if(nod.next==null) {
			return(-1);
		}else {
			return(1+findIndex(nod.next,data));
		}
	}
	
	public Node findNode(Node<T> front,int target) {
		if(target<=0) {
			return(front);
		}else if(front.next==null) {
			return(null);
		}else {
			return(findNode(front.next,target-1));
		}
	}
	
	public Node findNode(int target) {
		if(target<=0) {
			return(front);
		}else if(front.next==null) {
			return(null);
		}else {
			return(findNode(front.next,target-1));
		}
	}
	
	public Node deleteByIndex(int i) {
		if(i<=0) {
			front=front.next;
		}else if(findNode(i+1)!=null){
			findNode(i-1).next=findNode(i+1);
			return(findNode(i));
		}else {
			findNode(i-1).next=null;
			return(findNode(i));
		}
	}
	
	public T removeFront() {
		if(front!=null) {
			T frontData=front.data;
			front=front.next;
			size-=1;
			return(frontData);
		}else {
			throw new NoSuchElementException("this bitch is empty. yeet!");
		}
	}
	
	public void addBehind(int index, T data) {
		Node<T> behind=findNode(front, index);
		Node <T>newNode=new Node<T>(data,behind.next);
		behind.next=newNode;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList<Integer> lin=new LinkedList<Integer>();
		lin.addToFront(new Integer(4));
		lin.addToFront(new Integer(5));
		lin.addToFront(new Integer(6));
		lin.addBehind(1, new Integer(69));
		//lin.addToBack(new Integer(10));
		lin.traverse();
		lin.removeFront();
		lin.traverse();
		LinkedList<Integer> v=new LinkedList<Integer>();
		v.removeFront();
	}

}
