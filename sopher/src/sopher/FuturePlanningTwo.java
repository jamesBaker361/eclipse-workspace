package sopher;
import java.io.*;
import java.util.*;

public class FuturePlanningTwo {

	public FuturePlanningTwo() {
		// TODO Auto-generated constructor stub
	}
	/*
	 * average- idfk
	 * avoid quiz- 000011/000010
	 * dominant-111111
	 * jerry-010000
	 * george-010010
	 * elaine-110100
	 * kramer- 110101
	 * newman- 110111
	 * 
	 * jk its backwards
	 * 
	 * 
	 * */

 public static List<Row> getRows(List<Operator> ops, double p, double q, double f) {
  List<Double> rightCol = new ArrayList<Double>();
  rightCol.add(3.0/5.0); //type 6
  rightCol.add(5.0/7.0); //type 5
  rightCol.add(3.0/7.0); //type 4
  rightCol.add(1.0/2.0); //type 3
  rightCol.add(7.0/10.0); //type 2
  rightCol.add(5.0/7.0); //type 1
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
 
 public static void gridSearch(int i) throws IOException {
	 
  String[] patterns = {"dominant", "avoidQuiz", "average"};
  opMap.put("dominant", Arrays.asList(Operator.GREATERTHAN, Operator.GREATERTHAN, Operator.GREATERTHAN,
    Operator.GREATERTHAN, Operator.GREATERTHAN, Operator.GREATERTHAN));
  opMap.put("avoidQuiz", Arrays.asList(
		  Operator.LESSTHAN, 
		  Operator.GREATERTHAN, 
		  Operator.GREATERTHAN,
    Operator.LESSTHAN, 
    Operator.LESSTHAN, 
    Operator.LESSTHAN));
  opMap.put("average", Arrays.asList(
		  Operator.GREATERTHAN, 
		  Operator.LESSTHAN,
		  Operator.GREATERTHAN, 
    Operator.GREATERTHAN, 
    Operator.GREATERTHAN,
    Operator.LESSTHAN));
  for(String pattern : patterns) {
   BufferedWriter writer = new BufferedWriter(new FileWriter(pattern + ".csv"));
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
    	 writer.write("" +  0.0 + ",");
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
  values.add(((1.0-p)*(1.0-q))/((1.0-Math.pow(p, 4.0))*(Math.pow(1.0-f, 1.5)))); //type 6
  values.add(((1.0-Math.pow(p, 2.0))*(Math.pow(1.0-f, 2.0))*(1.0-Math.pow(q, 2.0)))/((1.0-p)*(1.0-q))); //type 5
  values.add(((1.0-Math.pow(p, 2.0))*(1.0-Math.pow(q, 2.0))*(Math.pow(1.0-f, 0.5)))/(1.0-Math.pow(p, 4.0))); //type 4
  values.add(((1.0-p)*(1.0-q))/((1.0-Math.pow(p, 4.0))*(Math.pow(1.0-f, 1.5)))); //type 3
  values.add(((1.0-p)*(1.0-q))/((1.0-Math.pow(p, 2.0))*(Math.pow(1.0-f, 2.0))*(1.0-Math.pow(q, 2.0)))); //type 2
  values.add(((1.0-Math.pow(p, 2.0))*(1.0-Math.pow(q, 2.0))*(Math.pow(1.0-f, 0.5)))/(1.0-Math.pow(p, 4.0))); //type 1
  return values;
 }
 
 public static void main(String[] args) throws IOException {
  gridSearch(5);
  System.out.println("bluh");
 }

}
