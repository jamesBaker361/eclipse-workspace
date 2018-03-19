public class IntNode {
         public int data;
         public IntNode next;
         public IntNode(int data, IntNode next) {
             this.data = data; this.next = next;
         }
         public String toString() {
             return data + "";
      }
         public static IntNode addBefore(IntNode front, int target, int newItem) {
             /* COMPLETE THIS METHOD */
        	 if(front.data==target) {
        		 IntNode newBoi=new IntNode(newItem, front);
        		 return(front);
        	 }
        	 for(IntNode i=front;i.next!=null; i=i.next) {
        		 if(target==i.next.data) {
        			 IntNode newBoi=new IntNode(newItem, i.next);
        			 i.next=newBoi;
        			 return(front);
        		 }
        	 }
        	 return front;
       } 
         public String traverse() {
        	 	if(this.next==null) {
        	 		return(this.toString());
        	 	}else {
        	 		return(this.toString()+"\n"+this.next.traverse());
        	 	}
         }
         
         public static void main(String[] args) {
        	 	IntNode j=new IntNode(3,null);
        	 	IntNode k=new IntNode(4,j);
        	 	IntNode front=addBefore(k,3,2);
        	 	System.out.println(front.traverse());
         }
}