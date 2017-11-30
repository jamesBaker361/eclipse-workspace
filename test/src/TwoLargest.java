
public class TwoLargest {

	public TwoLargest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		double term=IO.readDouble();
		double[] list=new double[0];
		boolean keepGoing=true;
		do {
			double inp=IO.readDouble();
			if(inp==term) {
				keepGoing=false;
				if(list.length<2) {
					IO.reportBadInput();
					keepGoing=true;
				}
			}else {
				double[] temp=list.clone();
				list=new double[temp.length+1];
				for(int c=0;c<temp.length;c++) {
					list[c]=temp[c];
				}
				list[temp.length]=inp;
			}
		}while(keepGoing);
		
		double big=list[0];
		double lessBig=list[1];
		for(int y=1;y<list.length;y++) {
				if(list[y]>=big) {
					double temp=big;
					big=list[y];
					lessBig=temp;
				}else if(list[y]>=lessBig) {
					lessBig=list[y];
				}
			}
		IO.outputDoubleAnswer(big);
		IO.outputDoubleAnswer(lessBig);
		}
	}