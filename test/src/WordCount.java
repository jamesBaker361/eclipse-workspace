
public class WordCount {

	public WordCount() {
		// TODO Auto-generated constructor stub
	}

	public static int countWords(String original, int maxLength){
		String[] words=new String[original.length()];
		int wordIndex=0;
		boolean wordFound=false;
		String word="";
		int quantity=0;
		for(int y=0;y<original.length();y++) {
			if(java.lang.Character.isAlphabetic(original.charAt(y))) {		
				word+=original.charAt(y);
				wordFound=true;
				if(y==original.length()-1) {
					words[wordIndex]=word;
				}
			}else {
				if(wordFound) {
					wordFound=false;
					words[wordIndex]=word;
					wordIndex++;
					word="";
				}
			}
		}
		for(int h=0;h<words.length;h++) {
			if(words[h]==null) {
				break;
			}else {
				if(words[h].length()<=maxLength) {
					quantity++;
				}
			}
		}
		return(quantity);
	}
	
	public static void main(String[] args) {
		String x="blah";
		while(x!="end") {
			x=IO.readString();
			IO.outputIntAnswer(countWords(x,1));
		}
		// TODO Auto-generated method stub
		
	}
	
}
