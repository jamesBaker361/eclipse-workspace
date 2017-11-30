
public class Party {

	public Party() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		int people=IO.readInt();
		int slicesperson=IO.readInt();
		int cansperson=IO.readInt();
		double piecost=IO.readDouble();
		int slicepie=IO.readInt();
		double casecost=IO.readDouble();
		int canscase=IO.readInt();
		
		int slicesneeded=people*slicesperson;
		double piesneeded=((double)slicesneeded)/((double)slicepie);
		
		if(piesneeded>(double)(int)piesneeded) {
			piesneeded=(double)(int)piesneeded+1;
		}
		double pizzacost= piesneeded*piecost;
		
		//IO.outputDoubleAnswer(pizzacost);
		
		int cansneeded=people*cansperson;
		double casesneeded=((double)cansneeded)/((double)canscase);
		
		if(casesneeded>(double)(int)casesneeded) {
			casesneeded=(double)(int)casesneeded+1;
		}
		double sodacost=casesneeded*casecost;
		
		IO.outputDoubleAnswer(sodacost+pizzacost);
		
		// TODO Auto-generated method stub

	}

}
