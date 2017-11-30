
public class Compress {

	public Compress() {
		// TODO Auto-generated constructor stub
	}

	public static String compress(String original){
		if(original.length()<=1) {
			return(original);
		}
		String out="";
		int streak=1;
		for(int k=0;k<original.length()-1;k++) {
			if(original.charAt(k)==original.charAt(k+1)) {
				streak++;
				if(k+1==original.length()-1) {
					out+=streak;
					out+=original.charAt(k);
				}
			}else {
				if(streak>1) {
					out+=streak;
					out+=original.charAt(k);
					streak=1;
				}else {
					out+=original.charAt(k);
				}
				if(k+1==original.length()-1) {
					out+=original.charAt(k+1);
				}
			}
		}
		return(out);
	}
	
	public static void main(String[] args) {
		String test="";
		while(test!="end") {
			test=IO.readString();
			IO.outputStringAnswer(compress(test));
		}
	}

}
