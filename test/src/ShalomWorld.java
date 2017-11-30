

public class ShalomWorld {
	private static Object ob;
	public ShalomWorld() {
		// TODO Auto-generated constructor stub
	}
	
	public static void capWord() {
		String in=IO.readString();
		if(in.length()<=0) {
			IO.reportBadInput();
		}else {
			String out=in.substring(0,1).toUpperCase()+in.substring(1,in.length()).toLowerCase();
			if(in.indexOf(' ')>=0) {
				IO.reportBadInput();
			}else {
				IO.outputStringAnswer(out);
				//return(out);
			}
		}
	}


	public static void main(String[] args) {
		/*
		System.out.println("input an angle in radians");
		double d=IO.readDouble();
		double tan=Math.tan(d);
		double cos=Math.cos(d);
		double sin=Math.sin(d);
		double deg=Math.toDegrees(d);
		long roundtan=Math.round(tan);
		System.out.println("tan is "+tan);
		System.out.println("tan in degrees is "+Math.toDegrees(tan));
		System.out.println("cos is "+cos);
		System.out.println("cos in degrees is "+Math.toDegrees(cos));
		System.out.println("sin is "+sin);
		System.out.println("sin in degrees is "+Math.toDegrees(sin));
		System.out.println("angle in degrees: "+deg);
		System.out.println("rounded tan: "+roundtan);
		IO.outputDoubleAnswer(Math.toDegrees(tan));
		*/
		Picasso.moveRight(5);
		Picasso.moveDown(5);
		Picasso.drawCircle(10);
	}

}
