
public class ReverseWords {

	public ReverseWords() {
		// TODO Auto-generated constructor stub
	}

	public static String Sentence(String sent) {
		if(sent.length()==0) {
			return("");
		}
		int words=0;
		String newWord="";
		String[] forwords= new String[sent.length()];
		String output="";
		int wIndex=0;
		boolean wording=false;
		for(int u=0;u<sent.length();u++) {
			if(sent.charAt(u)!=' '&&sent.charAt(u)!='.') {
				newWord+=sent.charAt(u);
				wording=true;
			}else {
				if(wording) {
					wording=false;
					forwords[wIndex]=newWord;
					wIndex++;
					newWord="";
				}
			}
			if(u==sent.length()-1&&wording) {
				forwords[wIndex]=newWord;
			}
		}
		for(int p=0;p<forwords.length;p++) {
			if(forwords[p]!=null) {
				//IO.outputStringAnswer(forwords[p]);
				//IO.outputIntAnswer(p);
				words++;
			}
		}
		for(int k=words-1;k>-1;k--) {
			output+=forwords[k]+" ";
		}
		output=output.substring(0, 1).toUpperCase()+output.substring(1, output.length()-1)+".";
		return(output);
	}
	
	public static String Sentence() {
		System.out.println("input a sentence");
		return(Sentence(IO.readString()));
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		IO.outputStringAnswer(Sentence("i like  4354 penises yum."));
		IO.outputStringAnswer(Sentence());
	}

}
