package sopher;

import java.util.ArrayList;
import java.util.Collections;

public class WriterBoi {

	public WriterBoi() {
		// TODO Auto-generated constructor stub
	}
	
	public static void allOnes() {
		for(double p=0;p<1;p+=.05) {
			for(double q=0;q<1;q+=.05) {
				ArrayList<Double> fs=new ArrayList<Double>();
				for(double f=0;f<1;f+=.01) {
					if(
							Conditions.Type1(p,q,f)&&
							Conditions.Type2(p, q, f)&&
							Conditions.Type3(p, q, f)&&
							Conditions.Type4(p, q, f)&&
							Conditions.Type5(p,q,f)&&
							Conditions.Type6(p, q, f)) {
						fs.add(f);
						//System.out.println("p is "+p+" q is "+q+" f is "+f);
					}
				}
				if(!fs.isEmpty()) {
					System.out.println("p is "+ p+" q is "+q+" the max f is "+Collections.max(fs));
				}
			}
		}
	}
	
	public static void ZeroZeroZeroZeroOneZero() {
		for(double p=0;p<1;p+=.01) {
			for(double q=0;q<2;q+=.01) {
				ArrayList<Double> fs=new ArrayList<Double>();
				for(double f=0;f<2;f+=.01) {
					if(((!Conditions.Type1(p,q,f))&&
							(!Conditions.Type2(p, q, f))&&
							(!Conditions.Type3(p, q, f))&&
							(!Conditions.Type4(p, q, f))&&
							(Conditions.Type5(p,q,f))&&
							(!Conditions.Type6(p, q, f)))) {
						fs.add(f);
						//System.out.println("p is "+p+" q is "+q+" f is "+f);
					}
				}
				if(!fs.isEmpty()) {
					System.out.println("p is "+ p+" q is "+q+" the max f is "+Collections.max(fs));
				}
			}
		}
	}
	
	public static void ZeroZeroZeroZeroOneOne() {
		for(double p=0;p<2;p+=.01) {
			for(double q=0;q<2;q+=.01) {
				ArrayList<Double> fs=new ArrayList<Double>();
				for(double f=0;f<2;f+=.01) {
					if(((!Conditions.Type1(p,q,f))&&
							(!Conditions.Type2(p, q, f))&&
							(!Conditions.Type3(p, q, f))&&
							(!Conditions.Type4(p, q, f))&&
							(Conditions.Type5(p,q,f))&&
							(Conditions.Type6(p, q, f)))) {
						fs.add(f);
						//System.out.println("p is "+p+" q is "+q+" f is "+f);
					}
				}
				if(!fs.isEmpty()) {
					System.out.println("p is "+ p+" q is "+q+" the max f is "+Collections.max(fs));
				}
			}
		}
	}
	
	public static void ZeroOneZeroZeroZeroZero() {
		for(double p=0;p<2;p+=.01) {
			for(double q=0;q<2;q+=.01) {
				ArrayList<Double> fs=new ArrayList<Double>();
				for(double f=0;f<2;f+=.01) {
					if((
							(!Conditions.Type1(p,q,f))&&
							(Conditions.Type2(p, q, f))&&
							(!Conditions.Type3(p, q, f))&&
							(!Conditions.Type4(p, q, f))&&
							(!Conditions.Type5(p,q,f))&&
							(!Conditions.Type6(p, q, f)))) {
						fs.add(f);
						//System.out.println("p is "+p+" q is "+q+" f is "+f);
					}
				}
				if(!fs.isEmpty()) {
					System.out.println("p is "+ p+" q is "+q+" the max f is "+Collections.max(fs));
				}
			}
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		allOnes();
		//ZeroZeroZeroZeroOneOne();
	}

}
