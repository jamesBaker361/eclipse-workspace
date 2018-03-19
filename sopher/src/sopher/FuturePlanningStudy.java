package sopher;

import java.io.*;
import java.util.*;

public class FuturePlanningStudy {
	

 public static List<Row> getRows(List<Operator> ops, double p, double q, double f) {
  List<Double> rightCol = new ArrayList<Double>();

  rightCol.add(3.0/7.0); //type 4
  rightCol.add(5.0/7.0); //type 5
  rightCol.add(3.0/5.0); //type 6
  rightCol.add(5.0/7.0); //type 1
  rightCol.add(7.0/10.0); //type 2
  rightCol.add(1.0/2.0); //type 3
  List<Double> leftCol = leftValues(p, q, f);
  List<Row> rowList = new ArrayList<Row>();
  for(int i = 0; i < leftCol.size(); i++) {
   Row row = new Row();
   row.left = leftCol.get(i);
   row.right = rightCol.get(i);
   row.op = ops.get(i);
   rowList.add(row);
  }
  return rowList;
 }
 
 static HashMap<String, List<Operator>> opMap = new HashMap<>();
 
 public static void gridSearchBetter(int i, String pat) throws IOException  {
	//pat has to be a 6 letter string thats a combination of 1s and 0s
	 //the i is the step size * 100 (if i=1, we increment up by .01)
	 String[] patterns= {pat};
	 Operator[] ops=new Operator[6];
	 for(int h=5;h>-1;h--) {
		 if(pat.charAt(h)=='0') {
			 ops[h]=Operator.LESSTHAN;
		 }else if(pat.charAt(h)=='1') {
			 ops[h]=Operator.GREATERTHAN;
		 }else {
			 //uhhhh
		 }
	 }
	 List<Operator> list=Arrays.asList(ops);
	 opMap.put(pat,list);
	 for(String pattern : patterns) {
		   BufferedWriter writer = new BufferedWriter(new FileWriter("_"+pattern + "max.csv"));
		   writer.write(""+0+",");
		   for(int q_int = 0; q_int < 100; q_int+=i) {
			   double q = q_int / 100.0;
			   writer.write(""+q+",");
		   }
		   writer.write("\n");
		   for(int p_int = 0; p_int < 100; p_int+=i) {
			double p = p_int / 100.0;
			writer.write(""+p+",");
		    for(int q_int = 0; q_int < 100; q_int+=i) {
		    	double q = q_int / 100.0;
		    	ArrayList<Double> fs=new ArrayList<Double>();
		     for(int f_int = 0; f_int < 100; f_int+=i) {
		      double f = f_int / 100.0;
		      List<Operator> opList = opMap.get(pattern);
		      List<Row> myRows = getRows(opList, p, q, f);
		      if(satisfiesAll(myRows)) {
		    	   fs.add(f);
		       //writer.write("" + p + ", " + q + ", " + f + "\n");
		      }
		     }
		     if(!fs.isEmpty()) {
		    	  writer.write("" +Collections.max(fs) + ",");
		     }else {
		    	 writer.write("" +  -0.1 + ",");
		     }
		    }
		    writer.write("\n");
		   }
		   writer.close();
		  }
		  for(String pattern : patterns) {
			   BufferedWriter writer = new BufferedWriter(new FileWriter("_"+pattern + "min.csv"));
			   writer.write(""+0+",");
			   for(int q_int = 0; q_int < 100; q_int+=i) {
				   double q = q_int / 100.0;
				   writer.write(""+q+",");
			   }
			   writer.write("\n");
			   for(int p_int = 0; p_int < 100; p_int+=i) {
				double p = p_int / 100.0;
				writer.write(""+p+",");
			    for(int q_int = 0; q_int < 100; q_int+=i) {
			    	double q = q_int / 100.0;
			    	ArrayList<Double> fs=new ArrayList<Double>();
			     for(int f_int = 0; f_int < 100; f_int+=i) {
			      double f = f_int / 100.0;
			      List<Operator> opList = opMap.get(pattern);
			      List<Row> myRows = getRows(opList, p, q, f);
			      if(satisfiesAll(myRows)) {
			    	   fs.add(f);
			       //writer.write("" + p + ", " + q + ", " + f + "\n");
			      }
			     }
			     if(!fs.isEmpty()) {
			    	  writer.write("" +Collections.min(fs) + ",");
			     }else {
			    	 writer.write("" +  -0.1 + ",");
			     }
			    }
			    writer.write("\n");
			   }
			   writer.close();
			  }
 }
 
 public static void gridSearch(int i) throws IOException { //this is deprecated
	 
  String[] patterns = {"_111111", "_000110", "_011101","_010000","_010010","_110100","_110101","_110111","_000010","_000011"};
  opMap.put("_111111", Arrays.asList(Operator.GREATERTHAN, Operator.GREATERTHAN, Operator.GREATERTHAN,
    Operator.GREATERTHAN, Operator.GREATERTHAN, Operator.GREATERTHAN));
  opMap.put("_000110", Arrays.asList(
		  Operator.LESSTHAN, 
		  Operator.GREATERTHAN, 
		  Operator.GREATERTHAN,
    Operator.LESSTHAN, 
    Operator.LESSTHAN, 
    Operator.LESSTHAN));
  opMap.put("_011101", Arrays.asList(
		  Operator.GREATERTHAN, //6
		  Operator.LESSTHAN, //5
		  Operator.GREATERTHAN,  //4
    Operator.GREATERTHAN, //3
    Operator.GREATERTHAN, //2
    Operator.LESSTHAN)); //1
  opMap.put("_010000", Arrays.asList(
		  Operator.LESSTHAN, //6
		  Operator.LESSTHAN,		//5
		  Operator.LESSTHAN, 	//4
    Operator.LESSTHAN, 		//3
    Operator.GREATERTHAN,		//2
    Operator.LESSTHAN			//1
		  ));
  opMap.put("_010010", Arrays.asList(
		  Operator.LESSTHAN, //6
		  Operator.GREATERTHAN,		//5
		  Operator.LESSTHAN, 	//4
    Operator.LESSTHAN, 		//3
    Operator.GREATERTHAN,		//2
    Operator.LESSTHAN			//1
		  ));
  opMap.put("_110100", Arrays.asList(
		  Operator.LESSTHAN, //6
		  Operator.LESSTHAN,		//5
		  Operator.GREATERTHAN, 	//4
    Operator.LESSTHAN, 		//3
    Operator.GREATERTHAN,		//2
    Operator.GREATERTHAN			//1
		  ));
  opMap.put("_110101", Arrays.asList(
		  Operator.GREATERTHAN, //6
		  Operator.LESSTHAN,		//5
		  Operator.GREATERTHAN, 	//4
    Operator.LESSTHAN, 		//3
    Operator.GREATERTHAN,		//2
    Operator.GREATERTHAN			//1
		  ));
  opMap.put("_110111", Arrays.asList(
		  Operator.GREATERTHAN, //6
		  Operator.GREATERTHAN,		//5
		  Operator.GREATERTHAN, 	//4
    Operator.LESSTHAN, 		//3
    Operator.GREATERTHAN,		//2
    Operator.GREATERTHAN			//1
		  ));
  opMap.put("_000010", Arrays.asList(
		  Operator.LESSTHAN, //6
		  Operator.GREATERTHAN,		//5
		  Operator.LESSTHAN, 	//4
    Operator.LESSTHAN, 		//3
    Operator.LESSTHAN,		//2
    Operator.LESSTHAN			//1
		  ));
  opMap.put("_000011", Arrays.asList(
		  Operator.GREATERTHAN, //6
		  Operator.GREATERTHAN,		//5
		  Operator.LESSTHAN, 	//4
    Operator.LESSTHAN, 		//3
    Operator.LESSTHAN,		//2
    Operator.LESSTHAN			//1
		  ));
  for(String pattern : patterns) {
   BufferedWriter writer = new BufferedWriter(new FileWriter("control"+pattern + "max.csv"));
   writer.write(""+0+",");
   for(int q_int = 0; q_int < 100; q_int+=i) {
	   double q = q_int / 100.0;
	   writer.write(""+q+",");
   }
   writer.write("\n");
   for(int p_int = 0; p_int < 100; p_int+=i) {
	double p = p_int / 100.0;
	writer.write(""+p+",");
    for(int q_int = 0; q_int < 100; q_int+=i) {
    	double q = q_int / 100.0;
    	ArrayList<Double> fs=new ArrayList<Double>();
     for(int f_int = 0; f_int < 100; f_int+=i) {
      double f = f_int / 100.0;
      List<Operator> opList = opMap.get(pattern);
      List<Row> myRows = getRows(opList, p, q, f);
      if(satisfiesAll(myRows)) {
    	   fs.add(f);
       //writer.write("" + p + ", " + q + ", " + f + "\n");
      }
     }
     if(!fs.isEmpty()) {
    	  writer.write("" +Collections.max(fs) + ",");
     }else {
    	 writer.write("" +  -0.1 + ",");
     }
    }
    writer.write("\n");
   }
   writer.close();
  }
  for(String pattern : patterns) {
	   BufferedWriter writer = new BufferedWriter(new FileWriter(pattern + "min.csv"));
	   writer.write(""+0+",");
	   for(int q_int = 0; q_int < 100; q_int+=i) {
		   double q = q_int / 100.0;
		   writer.write(""+q+",");
	   }
	   writer.write("\n");
	   for(int p_int = 0; p_int < 100; p_int+=i) {
		double p = p_int / 100.0;
		writer.write(""+p+",");
	    for(int q_int = 0; q_int < 100; q_int+=i) {
	    	double q = q_int / 100.0;
	    	ArrayList<Double> fs=new ArrayList<Double>();
	     for(int f_int = 0; f_int < 100; f_int+=i) {
	      double f = f_int / 100.0;
	      List<Operator> opList = opMap.get(pattern);
	      List<Row> myRows = getRows(opList, p, q, f);
	      if(satisfiesAll(myRows)) {
	    	   fs.add(f);
	       //writer.write("" + p + ", " + q + ", " + f + "\n");
	      }
	     }
	     if(!fs.isEmpty()) {
	    	  writer.write("" +Collections.min(fs) + ",");
	     }else {
	    	 writer.write("" +  -0.1 + ",");
	     }
	    }
	    writer.write("\n");
	   }
	   writer.close();
	  }
 }
 
 public static enum Operator {
  LESSTHAN, GREATERTHAN
 }
 
 public static class Row {
  public Double left;
  public Double right;
  public Operator op;
 }
 
 public static boolean satisfiesAll(List<Row> rows) {
  for(Row row : rows) {
   switch(row.op) {
    case LESSTHAN:
     if(row.left >= row.right) {
      return false;
     }
     break;
    case GREATERTHAN:
     if(row.left <= row.right) {
      return false;
     }
     break;
   }
  }
  return true;
 }
 
 public static List<Double> leftValues(double p, double q, double f) {
  List<Double> values = new ArrayList<Double>();

  values.add(((1.0-Math.pow(p, 2.0))*(1.0-Math.pow(q, 2.0))*(Math.pow(1.0-f, 0.5)))/(1.0-Math.pow(p, 4.0))); //type 4
  values.add(((1.0-Math.pow(p, 2.0))*(Math.pow(1.0-f, 2.0))*(1.0-Math.pow(q, 2.0)))/((1.0-p)*(1.0-q))); //type 5
  values.add(((1.0-p)*(1.0-q))/((1.0-Math.pow(p, 4.0))*(Math.pow(1.0-f, 1.5)))); //type 6

  values.add(((1.0-Math.pow(p, 2.0))*(1.0-Math.pow(q, 2.0))*(Math.pow(1.0-f, 0.5)))/(1.0-Math.pow(p, 4.0))); //type 1
  values.add(((1.0-p)*(1.0-q))/((1.0-Math.pow(p, 2.0))*(Math.pow(1.0-f, 2.0))*(1.0-Math.pow(q, 2.0)))); //type 2
  values.add(((1.0-p)*(1.0-q))/((1.0-Math.pow(p, 4.0))*(Math.pow(1.0-f, 1.5)))); //type 3
  return values;
 }
 
 public static void main(String[] args) throws IOException {
  //gridSearch(1);
String[] patterns = {"111111", "000110", "011101","010000","010010","110100","110101","110111","000010","000011"};
for(int y=0;y<patterns.length;y++) {
	gridSearchBetter(1,patterns[y]);
}
  System.out.println("bluh");
 }

}
