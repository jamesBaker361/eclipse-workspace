

/**
 * @author jamesbaker
 *
 */
public class LuckyNines {

	/**
	 * 
	 */
	public LuckyNines() {
		// TODO Auto-generated constructor stub
	}
	
	public static int howManyNines(int num) {
		int nines=0;
		for(int y=1;y<Math.abs(num)*2;y=y*10) {
			//System.out.println(y);
			if((Math.abs(num)/y)%10==9) {
				nines++;
			}
		}
		return(nines);
	}

	public static int countLuckyNines(int lowerEnd, int upperEnd) {
		if(upperEnd<lowerEnd) {
			return(-1);
		}
		int totalNines=0;
		for(int c=lowerEnd;c<=upperEnd;c++) {
			totalNines+=howManyNines(c);
		}
		return(totalNines);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int low=IO.readInt();
		int high=IO.readInt();
		IO.outputIntAnswer(countLuckyNines(low,high));
	}

}
