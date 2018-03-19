public class parenMatch{

	public static boolean parenMatch(String expr){
		Stack<Character> stack=new Stack<Character>();
		for(int i=0;i<expr.length();i++){
			char chexpr=expr.charAt(i);
			if(chexpr=='('||chexpr=='['){
				stack.push(chexpr);
			}else if(chexpr==')'||chexpr==']'){
				char chStack=stack.pop();
				if(chexpr==')'&&chStack=='['){
					return false;
				}else if(chexpr==']'&&chStack=='('){
					return false;
				}
			}else{
				return true;
			}
		}
		return true;
	}
	public static void main(String[] args){
		System.out.print(parenMatch("aaa)"));
	}
}