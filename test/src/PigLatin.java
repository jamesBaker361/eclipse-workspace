public class PigLatin {

	public PigLatin() {
		// TODO Auto-generated constructor stub
	}

	public static String translate (String original){
		if(original.isEmpty()) {
			IO.reportBadInput();
		}
		if(original.charAt(0)=='A'||original.charAt(0)=='E'||original.charAt(0)=='I'||original.charAt(0)=='O'||original.charAt(0)=='U'||original.charAt(0)=='a'||original.charAt(0)=='e'||original.charAt(0)=='i'||original.charAt(0)=='o'||original.charAt(0)=='u') {
			return(original+"vai");
		}else {
			return(original.substring(1)+original.substring(0, 1)+"ai");
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String test="";
		while(test!="end") {
			test=IO.readString();
			IO.outputStringAnswer(translate(test));
		}
	}

}
