package sopher;

public class Conditions {

	public Conditions() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	//these methods return TRUE if the dominant choice condition is met
	public static boolean Type1(double p, double q, double f) {
		return(((1-Math.pow(p, 4))*10*Math.pow(1-f, 2.5))<((1-Math.pow(p, 2))*(1-Math.pow(q, 2))*14*Math.pow(1-f, 3)));
	}
	
	public static boolean Type2(double p, double q, double f) {
		return((1-Math.pow(p, 2))*(1-Math.pow(q, 2))*14*Math.pow(1-f, 3)<(1-p)*20*(1-f));
	}
	
	public static boolean Type3(double p, double q, double f) {
		return((1-Math.pow(p, 4))*10*Math.pow(1-f, 2.5)<(1-p)*20*(1-f));
	}
	
	public static boolean Type4(double p, double q, double f) {
		return((1-Math.pow(p, 4))*6*Math.pow(1-f, 2.5)<(1-Math.pow(p, 2))*(1-Math.pow(q, 2))*14*Math.pow(1-f, 3));
	}
	
	public static boolean Type5(double p, double q, double f) {
		return((1-p)*10*(1-f)<(1-Math.pow(p, 2))*(1-Math.pow(q, 2))*14*Math.pow(1-f, 3));
	}
	
	public static boolean Type6(double p, double q, double f) {
		return((1-Math.pow(p, 4))*6*Math.pow(1-f, 2.5)<(1-p)*10*(1-f));
	}

}
