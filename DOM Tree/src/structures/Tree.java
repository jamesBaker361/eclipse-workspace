package structures;

import java.util.*;

/**
 * This class implements an HTML DOM Tree. Each node of the tree is a TagNode, with fields for
 * tag/text, first child and sibling.
 * 
 */
public class Tree {
	
	/**
	 * Root node
	 */
	TagNode root=null;
	
	/**
	 * Scanner used to read input HTML file when building the tree
	 */
	Scanner sc;
	
	/**
	 * Initializes this tree object with scanner for input HTML file
	 * 
	 * @param sc Scanner for input HTML file
	 */
	public Tree(Scanner sc) {
		this.sc = sc;
		root = null;
	}
	
	private int index=0;
	private ArrayList<String> lines;
	
	/**
	 * Builds the DOM tree from input HTML file, through scanner passed
	 * in to the constructor and stored in the sc field of this object. 
	 * 
	 * The root of the tree that is built is referenced by the root field of this object.
	 */
	
	private void buildRecFour(TagNode root, ArrayList<String> lines) {
		index++;
		
		if(index>=lines.size()) {
			return;
		}
		String line=lines.get(index);
		//System.out.println(line);
		TagNode end=new TagNode(line.replaceAll("<", "").replaceAll("</", "").replaceAll(">", ""),null,null);
		if(line.indexOf("</")==-1&&line.indexOf("<")!=-1) {//not an end tag, but still an opening tag
			if(root.firstChild==null) {
				root.firstChild=end;
			}else {
				TagNode ptr=root.firstChild;
				while(ptr.sibling!=null) {
					ptr=ptr.sibling;
				}
				ptr.sibling=end;
			}
			buildRecFour(end,lines);
			buildRecFour(root,lines);
		}else if(line.indexOf("</")==-1&&line.indexOf("<")==-1) { //just a bit of text
			if(root.firstChild==null) {
				root.firstChild=end;
			}else {
				TagNode ptr=root.firstChild;
				while(ptr.sibling!=null) {
					ptr=ptr.sibling;
				}
				ptr.sibling=end;
			}
			buildRecFour(root,lines);
		}else { //ending tags *shakes fist*
			//index++;
			//buildRecFour(root,lines);
		}
	}
	
		
	public void build() {
		/** COMPLETE THIS METHOD **/
		//TagNode ptr=root;
		/*
		 * so we need a way to move horizontally and vertically
		 * perhaps some sort of 2d array
		 * maybe a function that quickly builds children, and then moves on to siblings
		 * or if we start childing we store the parent in a parent variable and then move the ponter back up
		 * or we have some sort of recrursive function that returns the parent
		 * so it builds the children 
		 * recursion is a hoe when you have to parse
		 * it recurses through and also hmmmmmmmm
		 * 
		 */
		lines=new ArrayList<String>();
		while(sc.hasNextLine()) {
			//System.out.println(sc.nextLine());
			String line=sc.nextLine();
			lines.add(line);
		}
		root=new TagNode(lines.get(0).replaceAll("<", "").replaceAll("</", "").replaceAll(">", ""),null,null);
		index=0;
		buildRecFour(root,lines);
	}
	
	/**
	 * Replaces all occurrences of an old tag in the DOM tree with a new tag
	 * 
	 * @param oldTag Old tag
	 * @param newTag Replacement tag
	 */
	public void replaceTag(String oldTag, String newTag) {
		/** COMPLETE THIS METHOD **/
		String oldConfig=getHTML();
		String newConfig=oldConfig.replaceAll("<"+oldTag+">", "<"+newTag+">");
		StringTokenizer s=new StringTokenizer(newConfig,"\n");
		ArrayList<String> newLines=new ArrayList<String>();
		while(s.hasMoreTokens()) {
			newLines.add(s.nextToken());
		}
		lines=newLines;
		root=new TagNode(lines.get(0).replaceAll("<", "").replaceAll("</", "").replaceAll(">", ""),null,null);
		index=0;
		buildRecFour(root,lines);
	}
	
	/**
	 * Boldfaces every column of the given row of the table in the DOM tree. The boldface (b)
	 * tag appears directly under the td tag of every column of this row.
	 * 
	 * @param row Row to bold, first row is numbered 1 (not 0).
	 */
	public void boldRow(int row) {
		/** COMPLETE THIS METHOD **/
		//lets find the rows
		//then bold them
		int rowCount=0;
		ArrayList<String> newLines=new ArrayList<String>();
		//String htmlDummy=getHTML();
		StringTokenizer s=new StringTokenizer(getHTML(),"\n");
		while(s.hasMoreTokens()) {
			String tok=s.nextToken();
			if(tok=="<tr>"||tok.equals("<tr>")) {
				rowCount++;
				if(rowCount==row) {
					newLines.add("<b>");
				}
				newLines.add(tok);
			}else if(tok=="</tr>"||tok.equals("</tr>")) {
				newLines.add(tok);
				if(rowCount==row) {
					newLines.add("</b>");
				}
			}else {
				newLines.add(tok);
			}
		}
		lines=newLines;
		root=new TagNode(lines.get(0).replaceAll("<", "").replaceAll("</", "").replaceAll(">", ""),null,null);
		index=0;
		buildRecFour(root,lines);
	}
	
	/**
	 * Remove all occurrences of a tag from the DOM tree. If the tag is p, em, or b, all occurrences of the tag
	 * are removed. If the tag is ol or ul, then All occurrences of such a tag are removed from the tree, and, 
	 * in addition, all the li tags immediately under the removed tag are converted to p tags. 
	 * 
	 * @param tag Tag to be removed, can be p, em, b, ol, or ul
	 */
	public void removeTag(String tag) {
		/** COMPLETE THIS METHOD **/
		removeTag(tag,root);
	}
	
	
	private void removeTag(String tag, TagNode root) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			while(ptr.sibling!=null&&(ptr.sibling.tag==tag||ptr.sibling.tag.equals(tag))) { //sibling match
				TagNode toRemove=ptr.sibling;
				if(toRemove.firstChild!=null) {
					if(tag=="ol"||tag=="ul"||tag.equals("ol")||tag.equals("ul")) {
						listToP(toRemove); //if tag is ol or ul, this goes through and turns all the li children to p children
					}
					ptr.sibling=toRemove.firstChild;
					TagNode finalChild=toRemove.firstChild; //this will be the last sibling of the children of the node we're removing
					while(finalChild.sibling!=null) {
						finalChild=finalChild.sibling;
					}
					finalChild.sibling=toRemove.sibling;
				}else {
					ptr.sibling=toRemove.sibling;
				}
				
				//root.sibling.sibling
					//root.firstChild and root.sibling.firstChild
					//how do we merge two firstChilds?
					//like if the <p> tag has 3 children and one sibling (and that sibling is a div tag)
					//and we're removing all the <div> tags, what happens to the div children?
				
			}
			if (ptr.firstChild != null) {
				//we need to check if ptr.firstChild doesnt have the tag we want to kill
				while(ptr.firstChild!=null&&(ptr.firstChild.tag==tag||ptr.firstChild.tag.equals(tag))) {
					TagNode toRemove=ptr.firstChild;
					if(ptr.firstChild!=null) {
						if(tag=="ol"||tag=="ul"||tag.equals("ol")||tag.equals("ul")) {
							listToP(toRemove); //if tag is ol or ul, this goes through and turns all the li children to p children
						}
						ptr.firstChild=toRemove.firstChild;
						TagNode finalChild=toRemove.firstChild; //this will be the last sibling of the children of the node we're removing
						while(finalChild.sibling!=null) {
							finalChild=finalChild.sibling;
						}
						finalChild.sibling=toRemove.sibling;
					}else {
						ptr.firstChild=toRemove.sibling;
					}
				}
				removeTag(tag,ptr.firstChild); //now this will check ptr.firstChild's siblings and children
			}
		}
	}
	
	//gets all the immediate children of this root, and turns all the lis into ps
	private void listToP(TagNode root) { //root is the ol or ul
		if(root.firstChild!=null) {
			for(TagNode ptr=root.firstChild; ptr != null;ptr=ptr.sibling) {
				if(ptr.tag=="li"||ptr.tag.equals("li")) {
					ptr.tag="p";
				}
			}
		}
	}
	
	/**
	 * Adds a tag around all occurrences of a word in the DOM tree.
	 * 
	 * @param word Word around which tag is to be added
	 * @param tag Tag to be added
	 */
	public void addTag(String word, String tag) {
		/** COMPLETE THIS METHOD **/
		String newConfig=getHTML();
		
		StringTokenizer s=new StringTokenizer(newConfig,"\n"); //each line becomes 
		ArrayList<String> newLines=new ArrayList<String>();
		while(s.hasMoreTokens()) {
			String x=s.nextToken();
			newLines.add(x);
			//System.out.println(x);
		}
		for(int j=0;j<newLines.size();j++) {
			if(newLines.get(j).toLowerCase().contains(word.toLowerCase())) {
				//System.out.println(newLines.get(j));
				int index=0;
				while(index<newLines.get(j).length()) {
					if(newLines.get(j).toLowerCase().charAt(index)==word.toLowerCase().charAt(0)) { //weve found the first letter 
						boolean match=false; //by default match is false
						for(int k=0;k<word.length();k++) {
							match=word.toLowerCase().charAt(k)==(newLines.get(j).toLowerCase().charAt(index+k)); //if the kth letter in the word does not match the index+k letter of the line, then the boys don't match so we can stop
							if(!match) {
								break;
							}
						}
						//now we need to check that the character after the charcter after the word either doesn't exist OR is a ' ' or '<', otherwise this might be part of a word or something yuh
						
						if(match&&(index+word.length()>=newLines.get(j).length()
								||newLines.get(j).charAt(index+word.length())==' '
								||newLines.get(j).charAt(index+word.length())=='<'
								||newLines.get(j).charAt(index+word.length())=='?'
								||newLines.get(j).charAt(index+word.length())=='.'
								||newLines.get(j).charAt(index+word.length())==';'
								||newLines.get(j).charAt(index+word.length())==':'
								||newLines.get(j).charAt(index+word.length())=='!') //so we've found the word and now we need to check that whatever comes after the word is okay as well
								) { 
							//System.out.println(newLines.get(j)+" is a match and word.length()+1>=newLines.get(j).length()||newLines.get(j).charAt(index+word.length()+1)==' '||newLines.get(j).charAt(index+word.length()+1)=='<')");
							if(index-1<=-1||newLines.get(j).charAt(index-1)==' '||newLines.get(j).charAt(index-1)=='>') { //now we make sure there's nothing funny BEFORE the word
								//System.out.println("index-1<=-1||newLines.get(j).charAt(index-1)==' '||newLines.get(j).charAt(index-1)=='>'");
								String newLine="";
								
										//newLines.get(j).substring(0, index)+"<"+tag+">";
								if(index+word.length()>=newLines.get(j).length()
										||newLines.get(j).charAt(index+word.length())=='<'
										||newLines.get(j).charAt(index+word.length())==' ') {
									newLine+=newLines.get(j).substring(0, index)+"<"+tag+">"+newLines.get(j).substring(index, index+word.length())+"</"+tag+">"+newLines.get(j).substring(index+word.length());
									newLines.set(j, newLine);
								//	System.out.println(newLine);
									index+=word.length()+(tag.length()*2)+5; //the new line has (tag.length()*2)+5 more characters, b/c <tag></tag>, so we have to account fro that AND ofc we can skip the next word.length() characters b/c we've alreadt parsed those, sorta
								}else if(
										newLines.get(j).charAt(index+word.length())=='?'
										||newLines.get(j).charAt(index+word.length())=='.'
										||newLines.get(j).charAt(index+word.length())==';'
										||newLines.get(j).charAt(index+word.length())==':'
										||newLines.get(j).charAt(index+word.length())=='!'
									) {
									if(index+word.length()+1>=newLines.get(j).length()||
										newLines.get(j).charAt(index+word.length()+1)==' '||
										newLines.get(j).charAt(index+word.length()+1)=='<') {
										newLine+=newLines.get(j).substring(0, index)+"<"+tag+">"+newLines.get(j).substring(index, index+word.length()+1)+"</"+tag+">"+newLines.get(j).substring(index+word.length()+1);
										newLines.set(j, newLine);
									//	System.out.println(newLine);
										index+=word.length()+(tag.length()*2)+5;
									}else {
										index++;
									}
								}else {
									index++;
								}
			
							}else {
								index++;
							}
						}else {
							index++;
						}
						
					}else {
						index++;
					}
				}
			}
		}
		//lines=newLines;
		//now we have to make it so <x>blah</x> becomes:
		//<x>
		//blah
		//</x>
		String newLinesProper="";
		for(int j=0;j<newLines.size();j++) {
			for(int h=0;h<newLines.get(j).length();h++) {
				if(newLines.get(j).charAt(h)=='<') { //if we hit a < we need to make a new line
					newLinesProper+="\n";
				}
				newLinesProper+=newLines.get(j).charAt(h);
				if(newLines.get(j).charAt(h)=='>') {
					newLinesProper+="\n";
				}
			}
			/*
			if(newLines.get(j).indexOf('<')!=-1) { //first we check to see if there are ANY tags whatsoever
				if(newLines.get(j).substring(newLines.get(j).indexOf('<')+1).indexOf('<')!=-1) { //now we check to see if there any more tags and if there are we got a <a>xxx</x>
					System.out.println(newLines.get(j));
					String firstTag=newLines.get(j).substring(newLines.get(j).indexOf('<'), newLines.get(j).indexOf('<')+1);
					String withoutFirstTag=newLines.get(j).substring(firstTag.length());
					String content
				}
			}
			*/
		}
		StringTokenizer tok=new StringTokenizer(newLinesProper,"\n");
		ArrayList<String> newLinesList=new ArrayList<String>();
		while(tok.hasMoreTokens()) {
			String next=tok.nextToken();
			if(next.replaceAll(" ", "").replaceAll("\t", "").length()>0) { //if killing all the whitespaces gets us nothing then heck
				newLinesList.add(next);
				System.out.println("$"+next+"$");
			}
		}
		lines=newLinesList;
		root=new TagNode(lines.get(0).replaceAll("<", "").replaceAll("</", "").replaceAll(">", ""),null,null);
		index=0;
		
		buildRecFour(root,lines);
	}
	
	
	
	/**
	 * Gets the HTML represented by this DOM tree. The returned string includes
	 * new lines, so that when it is printed, it will be identical to the
	 * input file from which the DOM tree was built.
	 * 
	 * @return HTML string, including new lines. 
	 */
	public String getHTML() {
		StringBuilder sb = new StringBuilder();
		getHTML(root, sb);
		return sb.toString();
	}
	
	private void getHTML(TagNode root, StringBuilder sb) {
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			if (ptr.firstChild == null) {
				sb.append(ptr.tag);
				sb.append("\n");
			} else {
				sb.append("<");
				sb.append(ptr.tag);
				sb.append(">\n");
				getHTML(ptr.firstChild, sb);
				sb.append("</");
				sb.append(ptr.tag);
				sb.append(">\n");	
			}
		}
	}
	
	/**
	 * Prints the DOM tree. 
	 *
	 */
	public void print() {
		print(root, 1);
	}
	
	private void print(TagNode root, int level) {
		//System.out.println("root exists??" +root);
		for (TagNode ptr=root; ptr != null;ptr=ptr.sibling) {
			for (int i=0; i < level-1; i++) {
				System.out.print("      ");
			};
			if (root != this.root) {
				System.out.print("|---- ");
			} else {
				System.out.print("      ");
			}
			System.out.println(ptr.tag);
			if (ptr.firstChild != null) {
				print(ptr.firstChild, level+1);
			}
		}
	}
}
