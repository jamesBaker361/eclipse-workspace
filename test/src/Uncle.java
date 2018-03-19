import java.util.Scanner;
import java.io.*;

public class Uncle<T> {

	public T obj;
	
	public Uncle(T o) {
		obj=o;
		// TODO Auto-generated constructor stub
	}
	
	public static void finger() {
		throw new IllegalArgumentException("god hates fags");
	}

	public static void main(String[] args) throws IOException {
		Uncle<Object> u=new Uncle<Object>(new Object());
		
	}

}
