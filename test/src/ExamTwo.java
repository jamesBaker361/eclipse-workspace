
public class ExamTwo {

	public ExamTwo() {
		// TODO Auto-generated constructor stub
	}
	
	public static String decode(String in) {
		String out="";
		String characters="0123456789abcdefghijklmnopqrstuvwxyz";
		String inLower=in.toLowerCase();
		for(int h=0;h<inLower.length();h++) {
			IO.outputStringAnswer(inLower.substring(h, h+1));
			if(inLower.charAt(h)=='a') {
				out+="z";
			}else if(inLower.charAt(h)=='0') {
				out+="9";
			}else if(inLower.charAt(h)==' '){
				out+=" ";
			}else {
				if(characters.indexOf(inLower.charAt(h))==-1) {
					out+=inLower.charAt(h);
				}else {
					for(int y=0;y<characters.length();y++) {
					//IO.outputStringAnswer("character substring");
					//IO.outputStringAnswer(characters.substring(y, y+1));
						if(characters.substring(y, y+1).compareTo(inLower.substring(h, h+1))==-1) {
						IO.outputStringAnswer("character substring");
						IO.outputStringAnswer(characters.substring(y, y+1));
						out+=characters.substring(y, y+1);
						}
					}
				}
			}
		}
		return(out);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IO.outputIntAnswer("b".compareTo("a"));
		IO.outputStringAnswer(decode(""));
	}

}
