import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;





public class Game {
	public static final int SPEED_FRAME = 1000;
	 public static final int PAGE_SIZE = 10;
	 private ArrayList<Circle> list = new ArrayList();
	 private ArrayList<Circle> list2 = new ArrayList();
	 private ArrayList<Circle> list3 = new ArrayList();
	 private Circle player;
	 private Circle stone;
	 private Screen frame;
	 boolean gameWork;
	 Random r=new Random();
	 int score=0;
	 
	 public void startGameLoop() throws InterruptedException, IOException {
	 
	  gameWork = true;
	  frame = new Screen();
	  InputStream in = System.in;
	
	  initWorld();
	  int frameCounter = 0;
	 
	  char input = '1';
	  while (gameWork) {
	 
		   // Убираем муссор с экрана
		  frame.clearConsol();
		   boolean newChar = false;
		  if(in.available() > 0){
			  System.out.println(String.format("available:%d", in.available() )); 
			  char buff  = (char)in.read();
			  in.read();
			  in.read();
			  input = buff;
			  newChar = true;
		  }


		System.out.println(String.format("Frame:%d", frameCounter++)); 
		if(newChar) System.out.println("input:"+input);
	   // Отчищаем наш кадр от мусора
	   frame.clearFrame();
	  
	   // Тут мы вычисляем, кто где находиться, если нужно переставляем объекты
	   calculationWorld(newChar,input);
	  
	 
	   // Рисуем наши объекты на карте
	   drawWorld();
	   

	   // Выводим на экран, то что нарисовали
	   frame.printFrame();


	   // Ждем
	   Thread.currentThread().sleep(SPEED_FRAME);
	  }
	  
	  System.out.println("Finish game");
	  System.out.println("Your score: "+score);
	 }
	 public void initWorld(){
		// Circle object = new Circle('#', 0, 0, 0, 0, true);
		  //list.add(object);
		  //object = new Circle('#', 0, 1, 0, 0, true);
		  //list.add(object);
		  
		  
		  
		  for (int i = 0; i < PAGE_SIZE; i++) {
				for (int j = 0; j < PAGE_SIZE; j++) {
					if (i == 0 || i == PAGE_SIZE - 1) {
						
						list.add(new Circle('#', i, j, 0, 0, true));
						continue;
					}
					if (((i > 0 || i < PAGE_SIZE)) && ((j == 0 || j == PAGE_SIZE - 1))) {
						list.add(new Circle('#', i, j, 0, 0, true));
					}
				}
				
				
				
			}
		 
		  
		  
		  player = new Circle('*', 5, 1, 0, 0, false);
		  list.add(player);
		  
		  
		   stone=new Circle('s',r.nextInt(7)+1, r.nextInt(7)+1,0,0,false);
		 list.add(stone);
		}  
		  
		 
	 
	 
	 

	 public void calculationWorld(boolean newChar,char ch) {
		 
		 if(newChar){
			 switch(ch){
			 case 'w':{
				// player.setLocation(player.getX()-1, player.getY());
				 player.setSpeed(-1, 0);
				 break;
			 }
			 case 's':{
				 //player.setLocation(player.getX()+1, player.getY());
				 player.setSpeed(1, 0);
				 break;
			 }
			 case 'a':{
				 //player.setLocation(player.getX(), player.getY()-1);
				 player.setSpeed(0, -1);
				 break;
			 }
			 case 'd':{
				 //player.setLocation(player.getX(), player.getY()+1);
				 player.setSpeed(0, 1);
				 break;
			 }
			 }
		 }
		 
		 player.nextStep();

		
		 //if(player.getX() == 2){
			// gameWork = false;
		 //}
		 
		 for(Circle ball:list){
			// ball.nextStep();

			  // Проверка, что бы наш объект не вышел за пределы экрана
			  // Если он вышел за пределы экрана, мы его вернем в позицию (0,0) (Можно
			  // направление поменять)
			  /*if (boll.getX() < 0 || boll.getX() > PAGE_SIZE - 1 || boll.getY() < 0 || boll.getY() > PAGE_SIZE - 1) {
			   boll.setLocation(0, 0);
			  }*/
			 if(ball.isWall()!=true){
				 list2.add(ball);
			 }else list3.add(ball);
			 
			 
		 }
		 
		 for(Circle ob1:list3){			
			if((player.getX()==ob1.getX())&&(player.getY()==ob1.getY())){
						//System.out.println("Game over");
				gameWork = false;
				}
			}
		 for(Circle ob1:list2){
			 for(Circle ob2:list2){
				if(ob1!=ob2){ 
					
					if(ob1.equals(player)){
			 
			 if((ob1.getX()==ob2.getX())&&(ob1.getY()==ob2.getY())){					
				 score++;
				 ob2.setLocation(r.nextInt(7)+1, r.nextInt(7)+1);
			 }
			 }
			 
		 }
		 }
		 }
		
		 }

	 public  void drawWorld() {
		 for(Circle boll:list){
			  frame.setChar(boll.getImage(), boll.getX(), boll.getY());
		 }
		 
	 }
	 public static void main(String[] args) throws InterruptedException, IOException {
			// TODO Auto-generated method stub
			Game game = new Game();
			game.startGameLoop();
		}

	 }
