/**
 * 
 */

/**
 * @author jamesbaker
 *
 */
public class LuckySevens {

	/**
	 * 
	 */
	public LuckySevens() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param args
	 */
	public static int howManySevens(int num) {
		int nines=0;
		for(int y=1;y<Math.abs(num)*2;y=y*10) {
			//System.out.println(y);
			if((Math.abs(num)/y)%10==7) {
				nines++;
			}
		}
		return(nines);
	}
	
	public static void main(String[] args) {
		int low=IO.readInt();
		int high=IO.readInt();
		int sevens=0;
		if(high<low) {
			return;
		}else {
			for(int b=low;b<=high;b++) {
				sevens+=howManySevens(b);
			}
			IO.outputIntAnswer(sevens);
		}
	}

}
