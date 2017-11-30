public class SmallestLargest {

	public SmallestLargest() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		double term=IO.readDouble();
		double[] list=new double[0];
		boolean keepGoing=true;
		do {
			double inp=IO.readDouble();
			if(inp==term) {
				keepGoing=false;
				if(list.length<1) {
					IO.reportBadInput();
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
		double max=list[0];
		double min=list[0];
		for(int y=0;y<list.length;y++) {
			if(list[y]>max) {
				max=list[y];
			}
			if(list[y]<min) {
				min=list[y];
			}
		}
		IO.outputDoubleAnswer(min);
		IO.outputDoubleAnswer(max);

	}

}
