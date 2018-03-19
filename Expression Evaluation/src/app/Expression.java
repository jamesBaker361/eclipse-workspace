package app;

import java.io.*;
import java.util.*;
import java.util.regex.*;

import structures.Stack;

public class Expression {

	public static String delims = " \t*+-/()[]";
	public static String delimsLess="\t*+-/()";
	public static String delimsMin="\t*+-/";
	private static String delims2= "[\t*+-/()0123456789.]";
	private static String upper="ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	private static String lower="abcdefghijklmnopqrstuvwxyz";
	private static String letters=lower+upper;
	private static String operandString="0123456789"+letters;
	
	public static void main(String[] args) {
		//String z="d+daddy-papi/jews[buttplug+assnose[bees[]]]/padre*garcon+daddy+dicks[]+anus[bees[]+padre]";
		ArrayList<Variable> vars=new ArrayList<Variable>();
		ArrayList<Array> arrays = new ArrayList<Array>();
		arrays.add(new Array("a"));
		arrays.get(0).values=new int[]{10,20,0,4};
		//makeVariableLists(z,vars,arrays);
		float f=evaluate("(2*1)*a[5*a[2]/1*30+3]",vars,arrays);
		System.out.println(f);
		//System.out.println(f);
		//System.out.println(evaluateFirstArray("30+a[a[2]+1]+34",vars,arrays));
		//"dicks".charAt(90);
		//System.out.println();
		//CODING PROBLEM-why does a+b not do the same as a + b
		
	}
			
    /**
     * Populates the vars list with simple variables, and arrays lists with arrays
     * in the expression. For every variable (simple or array), a SINGLE instance is created 
     * and stored, even if it appears more than once in the expression.
     * At this time, values for all variables and all array items are set to
     * zero - they will be loaded from a file in the loadVariableValues method.
     * 
     * @param expr The expression
     * @param vars The variables array list - already created by the caller
     * @param arrays The arrays array list - already created by the caller
     */
    public static void makeVariableLists(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	/** COMPLETE THIS METHOD **/
    	/** DO NOT create new vars and arrays - they are already created before being sent in
    	 ** to this method - you just need to fill them in.
    	 **/
    	expr=expr.trim();
    	while(expr.indexOf(' ')!=-1) {
    		expr=expr.substring(0,expr.indexOf(' '))+expr.substring(expr.indexOf(' ')+1);
    	} //white space really a tweak
    	if(expr.length()>0) {
    		//it does nothing if expr is an empty string b/c this gets kinda recursive ;p
    		if(expr.indexOf('[')==-1) {
    			//allah akbar! there are no arrays! 
    			String[] bois =expr.split(delims2); //this is the initial token ized shit
    			for(int y=0;y<bois.length;y++) {
    				if(bois[y].length()>0) {
    					//so now weve found out that this ain't an empty string
    					if(!vars.contains(new Variable(bois[y]))) {
    						vars.add(new Variable(bois[y]));
    					}
    				}
    			}
    		}else {
    			/*
    			 * what we want to do is break off the first chunks and
    			 * then recursively handle the rest/everything in between
    			 */
    			if(!vars.contains(new Variable(expr.substring(expr.lastIndexOf(']'))))) { //this is everything AFTER the final right bracket
    				//this is deprecated, b/c we're going from the front
    			}
    			int leftBrack=expr.indexOf('['); //so this is the index of the first left Bracket
    			int arrayNameStart=0;
    			for(int h=leftBrack-1;h>-1;h--) {
    				//this loop goes backwards until it finds a non-Letter character
    				//that means it has found the END of the name of the array like varname[]
    				if(letters.indexOf(expr.charAt(h))==-1) {
    					arrayNameStart=h+1;
    					break;
    				}
    			}
    			String arrayName=expr.substring(arrayNameStart, expr.indexOf('['));
    			if(arrayName.length()>0&&!arrays.contains(new Array(arrayName))) {
    				arrays.add(new Array(arrayName));
    			}
    			//now we have to deal with everything in front of arrayName
    			makeVariableLists(expr.substring(0,arrayNameStart),vars,arrays);
    			//so now we have to handle everything between the first left bracket and its correspodning right bracket
    			int rightBrack=leftBrack;
    			int unmatchedLeft=0;
    			for(int x=leftBrack;x<expr.length();x++) {
    				if(expr.charAt(x)=='[') {
    					unmatchedLeft++;
    				}else if(expr.charAt(x)==']') {
    					unmatchedLeft--;
    				}
    				if(unmatchedLeft==0) {
    					rightBrack=x;
    					break;
    				}
    			}
    			makeVariableLists(expr.substring(rightBrack+1),vars,arrays); //now we handle eveything after the first set of []
    			makeVariableLists(expr.substring(leftBrack+1, rightBrack),vars,arrays); //now we handle everything between the []
    			
    		}
    	}
    	}
    
    
    /**
     * Loads values for variables and arrays in the expression
     * 
     * @param sc Scanner for values input
     * @throws IOException If there is a problem with the input 
     * @param vars The variables array list, previously populated by makeVariableLists
     * @param arrays The arrays array list - previously populated by makeVariableLists
     */
    public static void loadVariableValues(Scanner sc, ArrayList<Variable> vars, ArrayList<Array> arrays) throws IOException {
        while (sc.hasNextLine()) {
            StringTokenizer st = new StringTokenizer(sc.nextLine().trim());
            int numTokens = st.countTokens();
            String tok = st.nextToken();
            Variable var = new Variable(tok);
            Array arr = new Array(tok);
            int vari = vars.indexOf(var);
            int arri = arrays.indexOf(arr);
            if (vari == -1 && arri == -1) {
            	continue;
            }
            int num = Integer.parseInt(st.nextToken());
            if (numTokens == 2) { // scalar symbol
                vars.get(vari).value = num;
            } else { // array symbol
            	arr = arrays.get(arri);
            	arr.values = new int[num];
                // following are (index,val) pairs
                while (st.hasMoreTokens()) {
                    tok = st.nextToken();
                    StringTokenizer stt = new StringTokenizer(tok," (,)");
                    int index = Integer.parseInt(stt.nextToken());
                    int val = Integer.parseInt(stt.nextToken());
                    arr.values[index] = val;              
                }
            }
        }
    }
    
    /**
     * 
     * evaluate firsy array turns the first array into a float
     * 
     */
    
    private static String evaluateFirstArray(String expr,ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	expr=expr.trim();
    	while(expr.indexOf(' ')!=-1) {
    		expr=expr.substring(0,expr.indexOf(' '))+expr.substring(expr.indexOf(' ')+1);
    	} //white space really a tweak
    		if(expr.indexOf('[')==-1) {
    			return(expr);
    		}else {
    			int leftBracket=expr.indexOf('[');
    			int rightBracket=leftBracket+1;
    			int unclosedLeft=0;
    			for(int h=leftBracket;h<expr.length();h++) {
    				if(expr.charAt(h)=='[') {
    					unclosedLeft++;
    				}else if(expr.charAt(h)==']') {
    					unclosedLeft--;
    				}
    				if(unclosedLeft==0) {
    					rightBracket=h;
    					break;
    				}
    			}
    			String arrayName="";
    			int arrayBegin=leftBracket-1; //the index of the first character of the array name
    			//we don't have to worry about arrayBegin being -1 or less, b/c then we'd just be having some shit like this [3] with brackets unattached to an array
    			for(int k=leftBracket-1;k>=0;k--) {
    				if(expr.charAt(k)==' '||
    					expr.charAt(k)=='+'||
    					expr.charAt(k)=='-'||
    					expr.charAt(k)=='/'||
    					expr.charAt(k)=='\t'||
    					expr.charAt(k)=='*'||
    					expr.charAt(k)=='$'||
    					expr.charAt(k)=='('||
    					expr.charAt(k)==')') {
    					arrayBegin=k+1;
    					break;
    				}else {
    					arrayBegin=k;
    					arrayName=expr.charAt(k)+arrayName;
    				}
    			}
    			System.out.println("arrayName is "+arrayName);
    			String evaluatedArr="";
    			int index=Math.round(evaluate(expr.substring(leftBracket+1, rightBracket),vars,arrays)-.5f); //this is just everything in between the brackets, so we evaluate it and get an integer
    			System.out.println("index is "+index);
    			for(int g=0;g<arrays.size();g++) {
    				if(arrays.get(g).name==arrayName||arrays.get(g).name.equals(arrayName)) {
    					
    					evaluatedArr=""+arrays.get(g).values[index];
    					break;
    				}
    			}
    			System.out.println("evaluatedArr is "+evaluatedArr);
    			return(expr.substring(0,arrayBegin)+evaluatedArr+expr.substring(rightBracket+1));
    		}
    		//return("");
    }
    
    /**
     * Evaluates the expression.
     * 
     * @param vars The variables array list, with values for all variables in the expression
     * @param arrays The arrays array list, with values for all array items
     * @return Result of evaluation
     */
    public static float 
    evaluate(String expr, ArrayList<Variable> vars, ArrayList<Array> arrays) {
    	expr=expr.trim();
    	while(expr.indexOf(' ')!=-1) {
    		expr=expr.substring(0,expr.indexOf(' '))+expr.substring(expr.indexOf(' ')+1);
    	} //white space really a tweak
   System.out.println("expr is "+expr);
    	/*
    	 * step one: evaluate arrays
    	 * step none: evaluate vars
    	 * step two: evaluate parentheses
    	 * step three: evaluate * and / from left to right
    	 * step four: evaluate + and - from left to right
    	 * base case: just 
    	 */
    	if(expr.indexOf('[')!=-1) {
    		//this means there are arrays 
    		return(evaluate(evaluateFirstArray(expr,vars,arrays),vars,arrays));
    	}else {
    		if(expr.indexOf('(')!=-1) {
    			//this means there are parentheses
    			int firstParen=expr.indexOf('(');
    			int unmatchedLeft=0;
    			int rightParen=firstParen;
    			for(int h=0;h<expr.length();h++) {
				rightParen=h;
    				if(expr.charAt(h)=='(') {
    					unmatchedLeft++;
    				}else if(expr.charAt(h)==')') {
    					unmatchedLeft--;
    					if(unmatchedLeft<=0) {
    						break;
    					}
    				}
    			}
    			String newExpr="";
    			float solved=evaluate(expr.substring(firstParen+1,rightParen),vars,arrays);
    			if(solved<0) {
    				newExpr=expr.substring(0, firstParen)+("&"+Math.abs(solved))+expr.substring(rightParen+1);
    			}else {
    				newExpr=expr.substring(0, firstParen)+solved+expr.substring(rightParen+1);
    			}
    			return(evaluate(newExpr,vars,arrays));
    		}else {
    			if(expr.indexOf('*')!=-1||expr.indexOf('/')!=-1) {
    				//this means there is multiplication or division
    				int opIndex=Math.min(expr.indexOf('*'), expr.indexOf('/')); //this is the index of the leftmost operator
    				if(opIndex<=-1) {
    					opIndex=Math.max(expr.indexOf('*'), expr.indexOf('/')); //if wwe get -1 for opIndex, that means onlty one is there, so we need to make opIndex the bigger one, b/c then it wont be -1
    				}
    				int beforeStart=0; //this is the index of the first character of the operand before the opertor
    				int afterEnd=expr.length()-1; //this is the index of the last character of the operand after
    				for(int y=opIndex-1;y>-1;y--) { //first we find beforeStart
    					if(expr.charAt(y)=='+'
    					||expr.charAt(y)=='-'
    					||expr.charAt(y)=='/'
    					||expr.charAt(y)=='*'
    					||expr.charAt(y)==' ') {
    						beforeStart=y+1;
    						break;
    					}
    				}
    				for(int x=opIndex+1;x<expr.length();x++) {
    					if(expr.charAt(x)=='+'
    	    				||expr.charAt(x)=='-'
    	    				||expr.charAt(x)=='/'
    	    				||expr.charAt(x)=='*'
    	    				||expr.charAt(x)==' ') {
    	 					afterEnd=x-1;
    	    					break;
    	    				}
    				}
    				String newExpr="";
    				float solved=0f;
    				if(expr.charAt(opIndex)=='*') {
    					//newExpr=expr.substring(0,beforeStart)+(evaluate(expr.substring(beforeStart,opIndex),vars,arrays)*evaluate(expr.substring(opIndex+1,afterEnd+1),vars,arrays))+expr.substring(afterEnd+1);
    					solved=(evaluate(expr.substring(beforeStart,opIndex),vars,arrays)*evaluate(expr.substring(opIndex+1,afterEnd+1),vars,arrays));
    				}else if(expr.charAt(opIndex)=='/') {
    					solved=(evaluate(expr.substring(beforeStart,opIndex),vars,arrays)/evaluate(expr.substring(opIndex+1,afterEnd+1),vars,arrays));
    					//newExpr=expr.substring(0,beforeStart)+(evaluate(expr.substring(beforeStart,opIndex),vars,arrays)/evaluate(expr.substring(opIndex+1,afterEnd+1),vars,arrays))+expr.substring(afterEnd+1);
    				}
    				if(solved<0) {
    					newExpr=expr.substring(0,beforeStart)+("&"+Math.abs(solved))+expr.substring(afterEnd+1);
    				}else {
    					newExpr=expr.substring(0,beforeStart)+solved+expr.substring(afterEnd+1);
    				}
    				System.out.println("multiplication "+newExpr);
    				return(evaluate(newExpr,vars,arrays));
    			}else {
    				if(expr.indexOf('+')!=-1||expr.indexOf('-')!=-1) {
    					//this means there is addition or subtraction
    					int opIndex=Math.min(expr.indexOf('+'), expr.indexOf('-')); //this is the index of the leftmost operator
    					if(opIndex<=-1) {
        					opIndex=Math.max(expr.indexOf('+'), expr.indexOf('-')); //if wwe get -1 for opIndex, that means onlty one is there, so we need to make opIndex the bigger one, b/c then it wont be -1
        				}
    					int beforeStart=0; //this is the index of the first character of the operand before the opertor
        				int afterEnd=expr.length()-1; //this is the index of the last character of the operand after
        				for(int y=opIndex-1;y>-1;y--) { //first we find beforeStart
        					if(expr.charAt(y)=='+'
        					||expr.charAt(y)=='-'
        					||expr.charAt(y)=='/'
        					||expr.charAt(y)=='*'
        					||expr.charAt(y)==' ') {
        						beforeStart=y+1;
        						break;
        					}
        				}
        				for(int x=opIndex+1;x<expr.length();x++) {
        					if(expr.charAt(x)=='+'
        	    				||expr.charAt(x)=='-'
        	    				||expr.charAt(x)=='/'
        	    				||expr.charAt(x)=='*'
        	    				||expr.charAt(x)==' ') {
        	 					afterEnd=x-1;
        	    					break;
        	    				}
        				}
        				String newExpr="";
        				float solved=0f;
        				if(expr.charAt(opIndex)=='+') {
        					solved=(evaluate(expr.substring(beforeStart,opIndex),vars,arrays)+evaluate(expr.substring(opIndex+1,afterEnd+1),vars,arrays));
        					//newExpr=expr.substring(0,beforeStart)+(evaluate(expr.substring(beforeStart,opIndex),vars,arrays)+evaluate(expr.substring(opIndex+1,afterEnd+1),vars,arrays))+expr.substring(afterEnd+1);
        					//if(solv)
        				}else if(expr.charAt(opIndex)=='-') {
        					solved=(evaluate(expr.substring(beforeStart,opIndex),vars,arrays)-evaluate(expr.substring(opIndex+1,afterEnd+1),vars,arrays));
        					//newExpr=expr.substring(0,beforeStart)+(evaluate(expr.substring(beforeStart,opIndex),vars,arrays)-evaluate(expr.substring(opIndex+1,afterEnd+1),vars,arrays))+expr.substring(afterEnd+1);
        					//System.out.println("now we eavlaute subtractio "+newExpr);
        				}
        				if(solved<0) {
        					newExpr=expr.substring(0,beforeStart)+("&"+Math.abs(solved))+expr.substring(afterEnd+1);
        				}else {
        					newExpr=expr.substring(0,beforeStart)+(solved)+expr.substring(afterEnd+1);
        				}
        				System.out.println("add/sub "+newExpr);
        				return(evaluate(newExpr,vars,arrays));
    				}else {
    					if(expr.indexOf('&')!=-1) {
    						//instead of like -1, we have &1
    						//this means there are negative numbers
    						int ampIndex=expr.indexOf('&');
    						String newExpr="";
    						for(int v=ampIndex+1;v<expr.length();v++) { //we build the expression to evaluate after the &
    							if(expr.charAt(v)=='+'||
    							expr.charAt(v)=='-'||
    							expr.charAt(v)=='/'||
    							expr.charAt(v)=='*'||
    							expr.charAt(v)==' ') {
    								break;
    							}else {
    								newExpr+=expr.charAt(v);
    							}
    						}
    						return(-1*evaluate(newExpr,vars,arrays));
    					}else {
    					//this just means evaluating a number or variable without any negatives
    						//boolean okay=true;
    						try {
    						float f=Float.parseFloat(expr);
    						return(f);
    					}catch(NumberFormatException e) {
    						System.out.println("Muberformatexception "+expr);
    						for(int v=0;v<vars.size();v++) {
    							if(vars.get(v).name==expr||vars.get(v).name.equals(expr)) {
    								if(vars.get(v).value<0) {
    									return(evaluate("&"+Math.abs(vars.get(v).value),vars,arrays));
    								}else {
    									return(vars.get(v).value); //if this is a positive number we're gucci
    								}
    							}else {
    								System.out.println(vars.get(v).name+" does not fuck wiht :"+expr+": :(");
    							}
    						}
    					}
    					}
    				}
    			}
    		}
    	}
    	System.out.println("it failed when expr was "+expr);
    	return(69f);
    }
    
}
