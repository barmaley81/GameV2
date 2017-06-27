
public class Circle {
	 private char image;
	  private int x;
	  private int y;
	  private int dx;
	  private int dy;
	  private boolean isWall;

	  public Circle(char image, int x, int y, int dx, int dy, boolean isWall) {
	   this.image = image;
	   this.x = x;
	   this.y = y;
	   this.dx = dx;
	   this.dy = dy;
	   this.isWall = isWall;
	  }
	  
	  public boolean isWall(){
		  return isWall;
	  }

	  public void setSpeed(int dx, int dy) {
	   this.dx = dx;
	   this.dy = dy;
	  }

	  public void setLocation(int x, int y) {
	   this.x = x;
	   this.y = y;
	  }

	  public void nextStep() {
	   x = x + dx;
	   y = y + dy;
	  }
	  
	  public void nextStep2() {
		   x = x - dx;
		   y = y - dy;
		  }


	  public int getX() {
	   return x;
	  }

	  public int getY() {
	   return y;
	  }

	  public int getdX() {
		   return dx;
		  }

	  public int getdY() {
		   return dy;
		   }
	  
	  public char getImage() {
	   return image;
	  }
	 }
