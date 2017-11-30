
public class OrderBooks {

	public OrderBooks() {
		// TODO Auto-generated constructor stub
	}

	public static void orderBooks(String bookOne,String bookTwo, String bookThree) {
		String[] books= {bookOne.toLowerCase(),bookTwo.toLowerCase(),bookThree.toLowerCase()};
		String[] thes= {"","",""};
		for(int y=0;y<3;y++) {
			if(books[y].indexOf("the ")==0) {
				books[y]=books[y].substring(4);
				thes[y]="The ";
			}
		}
		for(int u=0;u<2;u++) {
			if(books[u].substring(0,1).compareTo(books[u+1].substring(0,1))>0) {
				String temp=books[u];
				books[u]=books[u+1];
				books[u+1]=temp;
			}
		}
		for(int u=0;u<2;u++) {
			if(books[u].substring(0,1).compareTo(books[u+1].substring(0,1))>0) {
				String temp=books[u];
				books[u]=books[u+1];
				books[u+1]=temp;
			}
		}
		for(int h=0;h<3;h++) {
			IO.outputStringAnswer(thes[h]+books[h]);
		}
	}
	
	public static void main(String[] args) {
		orderBooks("zzzzz","the fffff", "aaaaaa");
		// TODO Auto-generated method stub

	}

}
