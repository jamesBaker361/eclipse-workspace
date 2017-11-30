

public class Menu {
	
	static int[][] birthdays=new int[2][0];
	static String[] names=new String[0];
	
	public Menu() {
		// TODO Auto-generated constructor stub
	}
	
	public static String getBirthday(String name,String[] names,int[][] birthdays) {
		for(int j=0;j<names.length;j++) {
			if(names[j]==name) {
				return(""+birthdays[j][0]+"/"+birthdays[j][1]);
			}
		}
		//IO.outputStringAnswer(s);
		return(name+ " not found");
	}
	
	public static void getNames(int month,int[][] birthdays, String[] names) {
		for(int v=0;v<names.length;v++) {
			if(birthdays[v][0]==month) {
				IO.outputStringAnswer(names[v]);
			}
		}
	}
	
	public static void getPairs(int[][] birthdays, String[] names) {
		//int[][] birthdayClone=birthdays.clone();
		String[] nameClone=names.clone();
		for(int x=0;x<nameClone.length;x++) {
			if(nameClone[x]!=null) {
				String output=nameClone[x]+" ";
				for(int h=x+1;h<nameClone.length;h++) {
					if(birthdays[h][0]==birthdays[x][0]&&birthdays[h][1]==birthdays[x][1]) {
						output+=nameClone[h]+" ";
						nameClone[h]=null;
					}
				}
				IO.outputStringAnswer(output+ "all have the same birthdays");
			}
		}
	}
	
	public static void birthdays(String[] names) {
		//while(name)
	}
	
	public static void main(String[] args) {
		names=null;
		String[] names= {"a","b","c","josh"};
		birthdays=null;
		int[][] birthdays= {{1,1},{1,1},{1,1},{3,3}};
		getPairs(birthdays,names);
		//IO.outputStringAnswer(getBirthday("josh",names,birthdays));
		//getNames(1,birthdays,names);
		
	}

}
