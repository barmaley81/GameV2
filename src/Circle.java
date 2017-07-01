
public class Circle {
	public static final int PLAYER=0;
	public static final int WALL=1;
	public static final int APPLE=2;
	public static final int TAIL=3;
	 private char image;
	  private int x;
	  private int y;
	  private int dx;
	  private int dy;
	  private int type;

	  public Circle(char image, int x, int y, int dx, int dy, int type) {
	   this.image = image;
	   this.x = x;
	   this.y = y;
	   this.dx = dx;
	   this.dy = dy;
	   this.type = type;
	  }
	  
	  public int getType(){
		  return type;
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
