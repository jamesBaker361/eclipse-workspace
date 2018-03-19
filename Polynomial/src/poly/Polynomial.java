package poly;

import java.io.IOException;
import java.util.Scanner;

/**
 * This class implements evaluate, add and multiply for polynomials.
 * 
 * @author runb-cs112
 *
 */
public class Polynomial {
	
	/**
	 * Reads a polynomial from an input stream (file or keyboard). The storage format
	 * of the polynomial is:
	 * <pre>
	 *     <coeff> <degree>
	 *     <coeff> <degree>
	 *     ...
	 *     <coeff> <degree>
	 * </pre>
	 * with the guarantee that degrees will be in descending order. For example:
	 * <pre>
	 *      4 5
	 *     -2 3
	 *      2 1
	 *      3 0
	 * </pre>
	 * which represents the polynomial:
	 * <pre>
	 *      4*x^5 - 2*x^3 + 2*x + 3 
	 * </pre>
	 * 
	 * @param sc Scanner from which a polynomial is to be read
	 * @throws IOException If there is any input error in reading the polynomial
	 * @return The polynomial linked list (front node) constructed from coefficients and
	 *         degrees read from scanner
	 */
	public static Node read(Scanner sc) 
	throws IOException {
		Node poly = null;
		while (sc.hasNextLine()) {
			Scanner scLine = new Scanner(sc.nextLine());
			poly = new Node(scLine.nextFloat(), scLine.nextInt(), poly);
			scLine.close();
		}
		return poly;
	}
	
	/**
	 * Returns the sum of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list
	 * @return A new polynomial which is the sum of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node add(Node poly1, Node poly2) {
		if(poly1==null&&poly2==null) {
			return(null);
		}else if(poly1==null) {
			return(poly2);
		}else if(poly2==null) {
			return(poly1);
		}
		Node p1 =poly1;
		Node p2=poly2;
		Node first=new Node(0,0,null);
		boolean firstInit=false;
		Node nextNode=first;
		while(p1!=null||p2!=null) {
			if(p1!=null&&p2!=null) {
				if(p1.term.degree==p2.term.degree) {
					//System.out.println("same degree is "+p1.term.degree+" and the coefficients are "+p1.term.coeff+"and"+p2.term.coeff);
					if(firstInit==true) {
						if(p1.term.coeff+p2.term.coeff!=0) {
							nextNode.next=new Node(p1.term.coeff+p2.term.coeff,p1.term.degree,null);
							nextNode=nextNode.next;
						}
					}else {
						if(p1.term.coeff+p2.term.coeff!=0) {
							firstInit=true;
							first=new Node(p1.term.coeff+p2.term.coeff,p1.term.degree,nextNode);
							nextNode=first;
						}
					}
					p1=p1.next;
					p2=p2.next;
				}else if(p1.term.degree<p2.term.degree) {
					if(firstInit==true) {
						nextNode.next=new Node(p1.term.coeff,p1.term.degree,null);
						nextNode=nextNode.next;
					}else {
						firstInit=true;
						first=new Node(p1.term.coeff,p1.term.degree,nextNode);
						nextNode=first;
					}
					p1=p1.next;
				}else if(p1.term.degree>p2.term.degree) {
					if(firstInit==true) {
						nextNode.next=new Node(p2.term.coeff,p2.term.degree,null);
						nextNode=nextNode.next;
					}else {
						firstInit=true;
						first=new Node(p2.term.coeff,p2.term.degree,nextNode);
						nextNode=first;
					}
					p2=p2.next;
				}
			}else if(p1!=null) {
				if(firstInit==true) {
					nextNode.next=new Node(p1.term.coeff,p1.term.degree,null);
					nextNode=nextNode.next;
				}else {
					firstInit=true;
					first=new Node(p1.term.coeff,p1.term.degree,nextNode);
					nextNode=first;
				}
				p1=p1.next;
			}else if(p2!=null) {
				if(firstInit==true) {
					nextNode.next=new Node(p2.term.coeff,p2.term.degree,null);
					nextNode=nextNode.next;
				}else {
					firstInit=true;
					first=new Node(p2.term.coeff,p2.term.degree,nextNode);
					nextNode=first;
				}
				p2=p2.next;
			}
		}
		//return(first);
		//System.out.println("we added "+toString(poly1)+" and "+toString(poly2)+" and got "+ toString(first));
		return(first);
	}
	
	/**
	 * Returns the product of two polynomials - DOES NOT change either of the input polynomials.
	 * The returned polynomial MUST have all new nodes. In other words, none of the nodes
	 * of the input polynomials can be in the result.
	 * 
	 * @param poly1 First input polynomial (front of polynomial linked list)
	 * @param poly2 Second input polynomial (front of polynomial linked list)
	 * @return A new polynomial which is the product of the input polynomials - the returned node
	 *         is the front of the result polynomial
	 */
	public static Node multiply(Node poly1, Node poly2) {
		if(poly1==null||poly2==null) {
			return(null);
		}
		Node out=null;
		Node p1=poly1;
		//Node p2=poly2;
		while(p1!=null) {
			Node p2=poly2;
			while(p2!=null) {
				//p2.term.coeff=p2.term.coeff*p1.term.coeff;
				//p2.term.degree+=p1.term.degree;
				Node bitch=new Node(p2.term.coeff*p1.term.coeff,p2.term.degree+p1.term.degree,null);
				out=add(out,bitch);
				p2=p2.next;
			}
			p1=p1.next;
		}
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		return(out);
	}
		
	/**
	 * Evaluates a polynomial at a given value.
	 * 
	 * @param poly Polynomial (front of linked list) to be evaluated
	 * @param x Value at which evaluation is to be done
	 * @return Value of polynomial p at x
	 */
	public static float evaluate(Node poly, float x) {
		/** COMPLETE THIS METHOD **/
		// FOLLOWING LINE IS A PLACEHOLDER TO MAKE THIS METHOD COMPILE
		// CHANGE IT AS NEEDED FOR YOUR IMPLEMENTATION
		if(poly==null||x==0) {
			return(0);
		}
		int sum=0;
		Node p=poly;
		while(p!=null) {
			sum+=p.term.coeff*Math.pow(x, p.term.degree);
			p=p.next;
		}
		return(sum);
		//return 0;
	}
	
	/**
	 * Returns string representation of a polynomial
	 * 
	 * @param poly Polynomial (front of linked list)
	 * @return String representation, in descending order of degrees
	 */
	public static String toString(Node poly) {
		if (poly == null) {
			return "0";
		} 
		
		String retval = poly.term.toString();
		for (Node current = poly.next ; current != null ;
		current = current.next) {
			retval = current.term.toString() + " + " + retval;
		}
		return retval;
	}	
}
