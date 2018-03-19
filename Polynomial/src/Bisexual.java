
public class Bisexual {

	public Bisexual() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] j= {1,2,3,4,5,6,7};
		RecSearch(j,5);
	}
	
	public static int Search(int[] A, int target) {
		int l=0;
		int r=A.length-1;
		while(l<r) {
			int m=(l+r)/2;
			if(target==A[m]) {
				return(m);
			}else if(target<A[m]) {
				r=m-1;
			}else {
				l=m+1;
			}
		}
		return(-1);
	}
	
	public static int RecSearch(int[] A, int target) {
		return(RecSearch(A, target, 0, A.length-1));
	}
	
	public static int RecSearch(int[] A, int target, int front, int end) {
		if(front>end) {
			return(-1);
		}
		int m=(front+end)/2;
		if(target==A[m]) {
			return(m);
		}else if(target<A[m]) {
			//we move to the lower half of the array
			return RecSearch(A, target, front, m-1);
		}else {
			//we should move to the top half
			return RecSearch(A, target, m+1, end);
		}
	}

	@Override
	public String toString() {
		return "Bisexual []";
	}

}
