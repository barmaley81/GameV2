import java.util.ArrayList;

public class Wall {
	ArrayList<Character> list = new ArrayList();
	 char arr[][] = new char[Game.PAGE_SIZE][Game.PAGE_SIZE];

	public  void initWall() {
		/*
		 * for(int i=0; i<Game.Size;i++){ for(int j=0; j<Game.Size;j++){
		 * if(i==0||i==Game.Size-1){ arrr[i][j]=1; System.out.print(arrr[i][j]);
		 * continue; } if(((i>0||i<Game.Size))&&((j==0||j==Game.Size-1))){
		 * arrr[i][j]=1; System.out.print(arrr[i][j]);
		 * }if(((i>0||i<Game.Size-1))&&((j>0&&j<Game.Size-1))){
		 * 
		 * System.out.print(" "); } } System.out.println(); }
		 */

		for (int i = 0; i < Game.PAGE_SIZE; i++) {
			for (int j = 0; j < Game.PAGE_SIZE; j++) {
				if (i == 0 || i == Game.PAGE_SIZE - 1) {
					arr[i][j] = '1';
					list.add(arr[i][j]);
					continue;
				}
				if (((i > 0 || i < Game.PAGE_SIZE)) && ((j == 0 || j == Game.PAGE_SIZE - 1))) {
					arr[i][j] = '1';
					list.add(arr[i][j]);
				}
			}
		}
	}
	
}
