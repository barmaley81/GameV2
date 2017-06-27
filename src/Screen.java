

public class Screen {
	
	 public char[][] arr = new char[Game.PAGE_SIZE][Game.PAGE_SIZE];

	  public void clearFrame() {
	   for (int i = 0; i < Game.PAGE_SIZE; i++) {
	    for (int j = 0; j < Game.PAGE_SIZE; j++) {
	     arr[i][j] = '0';
	    }
	   }
	  }

	  public void setChar(char ch, int i, int j) {
	   arr[i][j] = ch;
	  }

	  public void clearConsol() {
	   for (int i = 0; i < Game.PAGE_SIZE; i++) {
	    System.out.println();
	   }
	  }

	  public void printFrame() {
	   for (int i = 0; i < Game.PAGE_SIZE; i++) {
	    for (int j = 0; j < Game.PAGE_SIZE; j++) {
	     System.out.print(arr[i][j] + " ");
	    }
	    System.out.println();
	   }
	  }
	 }