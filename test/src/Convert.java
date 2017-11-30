
public class Convert {

	public Convert() {
		// TODO Auto-generated constructor stub
	}

	public static int convert (String binaryString, boolean signBit){
		int power=0;
		int total=0;
		
			for(int u=binaryString.length()-1;u>-1;u--) {
				if(binaryString.charAt(u)=='0'||binaryString.charAt(u)=='1') {
					total+=java.lang.Integer.parseInt(""+binaryString.charAt(u))*Math.pow(2, power);
					if(signBit&&u==0&&binaryString.charAt(u)=='1') {
						IO.outputDoubleAnswer(total);
						total-=java.lang.Integer.parseInt(""+binaryString.charAt(u))*Math.pow(2, power);
						total=-1*total;
					}
				}else {
					IO.reportBadInput();
				}
				power++;
			}
		return(total);
	}
	
	public static void main(String[] args) {
		String x="blah";
		while(x!="end") {
			x=IO.readString();
			IO.outputIntAnswer(convert(x,true));
		}

	}

}
