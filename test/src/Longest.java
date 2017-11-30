
public class Longest {

	public Longest() {
		// TODO Auto-generated constructor stub
	}

	public static String whomst(String input) {
		if(input.length()==0) {
			IO.reportBadInput();
		}
		char c=input.charAt(0);
		char bestchar=c;
		int length=1;
		int bigLength=1;
		boolean looping=false;
		for(int h=0;h<input.length()-1;h++) {
			if(input.charAt(h)==input.charAt(h+1)) {
				if(looping) {
					length++;
					if(h==input.length()-2) {
						if(length>bigLength) {
							bigLength=length;
							bestchar=c;
						}
					}
				}else {
					looping=true;
					c=input.charAt(h);
					length=2;
				}
			}else if(looping){
				if(length>bigLength) {
					bigLength=length;
					bestchar=c;
				}
				looping=false;
			}
		}
		return(""+bigLength+bestchar);
	}
	
	public static boolean contains(String one, String two) {
		String big="";
		String small="";
		if(one==two) {
			return(true);
		}else if(one.length()==two.length()) {
			return(false);
		}else if(one.length()>two.length()) {
			big=one;
			small=two;
		}else if(one.length()<two.length()) {
			big=two;
			small=one;
		}
		if(big.indexOf(small)!=-1) {
			return(true);
		}else {
			return(false);
		}
	}
	
	public static String reverse(String sentence) {
		if(sentence.length()==0) {
			return(sentence);
		}
		boolean foundWord=false;
		String word="";
		String output="";
		for(int y=0;y<sentence.length();y++) {
			if(sentence.charAt(y)==' ') {
				if(foundWord) {
					foundWord=false;
					for(int u=word.length()-1;u>-1;u--) {
						output+=word.charAt(u);
					}
					output+=" ";
					word="";
				}
			}else {
				if(foundWord) {
					word+=sentence.charAt(y);
				}else {
					foundWord=true;
					word+=sentence.charAt(y);
				}
				if(y==sentence.length()-1) {
					for(int u=word.length()-1;u>-1;u--) {
						output+=word.charAt(u);
					}
				}
			}
		}
		return(output);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//IO.outputStringAnswer(whomst("ddccccccccddddaghhhhhhhhhhh"));
		//IO.outputBooleanAnswer(contains("yoss","ys"));
		//IO.outputStringAnswer(reverse("dicks taste good"));
		IO.outputIntAnswer(Integer.parseInt("101 222"));
	}

}
