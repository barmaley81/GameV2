import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Random;

public class Game {
	public static final int SPEED_FRAME = 1000;
	public static final int PAGE_SIZE = 10;
	private ArrayList<Circle> listWarld = new ArrayList();
	private ArrayList<Circle> listPlayers = new ArrayList();
	private ArrayList<Circle> listWalls = new ArrayList();
	private ArrayList<Circle> listTail = new ArrayList();
	private ArrayList<Location> listLocation = new ArrayList();
	private Circle player;
	private Circle apple;
	private Screen frame;
	boolean gameWork;
	boolean setApple=true;
	Random r = new Random();

	int score = 0;

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
			if (in.available() > 0) {
				System.out.println(String.format("available:%d", in.available()));
				char buff = (char) in.read();
				in.read();
				in.read();
				input = buff;
				newChar = true;
			}

			System.out.println(String.format("Frame:%d", frameCounter++));
			if (newChar)
				System.out.println("input:" + input);
			// Отчищаем наш кадр от мусора
			frame.clearFrame();

			// Тут мы вычисляем, кто где находиться, если нужно переставляем
			// объекты
			calculationWorld(newChar, input);
			System.out.println("calculationWorld");
			// Рисуем наши объекты на карте
			drawWorld();
			System.out.println("list4.size: " + listTail.size());
			System.out.println("list.size: " + listWarld.size());
			System.out.println("list.Walls" + listWalls.size());
			System.out.println("list.location" + listLocation.size());

			if (listLocation.size() > 1) {
				System.out.println("Lastlocation" + listLocation.get(listLocation.size() - 2));
			}

			if (!listLocation.isEmpty()) {
				System.out.println("Lastlocation" + listLocation.get(listLocation.size() - 1));
			}
			// Выводим на экран, то что нарисовали
			frame.printFrame();
			listWalls.clear();

			// Ждем
			Thread.currentThread().sleep(SPEED_FRAME);
		}

		System.out.println("Finish game");
		System.out.println("Your score: " + score);

	}

	public void initWorld() {
		for (int i = 0; i < PAGE_SIZE; i++) {
			for (int j = 0; j < PAGE_SIZE; j++) {
				if (i == 0 || i == PAGE_SIZE - 1) {
					listWarld.add(new Circle('#', i, j, 0, 0, true));
					continue;
				}
				if (((i > 0 || i < PAGE_SIZE)) && ((j == 0 || j == PAGE_SIZE - 1))) {
					listWarld.add(new Circle('#', i, j, 0, 0, true));
				}
			}

		}

		player = new Circle('*', 5, 1, 0, 0, false);
		listWarld.add(player);
		
		/*
		Circle newItemPlayer = new Circle('*', player.getX(),
				player.getY(),0,0, false);		
		listWarld.add(newItemPlayer);
		listTail.add(newItemPlayer);
		
		newItemPlayer = new Circle('*', player.getX(),
				player.getY(),0,0, false);
		listWarld.add(newItemPlayer);
		listTail.add(newItemPlayer);
		
		*/
		for (int i = 0; i < 3; i++) {
			apple = new Circle('s', r.nextInt(7) + 1, r.nextInt(7) + 1, 0, 0, false);
			listWarld.add(apple);
		}
	}

	public void calculationWorld(boolean newChar, char ch) {

		if (newChar) {
			switch (ch) {
			case 'w': {
				// player.setLocation(player.getX()-1, player.getY());
				player.setSpeed(-1, 0);
				break;
			}
			case 's': {
				// player.setLocation(player.getX()+1, player.getY());
				player.setSpeed(1, 0);
				break;
			}
			case 'a': {
				// player.setLocation(player.getX(), player.getY()-1);
				player.setSpeed(0, -1);
				break;
			}
			case 'd': {
				// player.setLocation(player.getX(), player.getY()+1);
				player.setSpeed(0, 1);
				break;
			}
			}
		}

		for (Circle ball : listWarld) {

			if (ball.isWall() != true) {
				listPlayers.add(ball);
			} else
				listWalls.add(ball);
		}

		for (Circle ob1 : listWalls) {
			if ((player.getX() + player.getdX() == ob1.getX()) && (player.getY() + player.getdY() == ob1.getY())) {
				gameWork = false;
			}
		}

		for (Circle ob1 : listPlayers) {
			for (Circle ob2 : listPlayers) {
				if (ob1 != ob2) {
					if (ob1.equals(player)) {

						if ((ob1.getX() + player.getdX() == ob2.getX())
								&& (ob1.getY() + player.getdY() == ob2.getY())) {

							score++;
							if(listTail.size()>1){
							while(setApple){
								int x=-1, y=-1;
								int tmpX=r.nextInt(7)+1;
								int tmpY=r.nextInt(7)+1;
								
								for(int i=0;i<listTail.size();i++){
									if((tmpX!=listTail.get(i).getX())&&(tmpY!=listTail.get(i).getY())){
										x=tmpX;
										y=tmpY;
									}
									
								}							
								if(x!=-1&&y!=-1){
									setApple=false;
									ob2.setLocation(x,y);
									
								}
							}	
							}	
						
							Circle newItemPlayer = new Circle('*', player.getX(),
									player.getY(),0,0, false);
							listWarld.add(0, newItemPlayer);
							listTail.add(0, newItemPlayer);
						}
					}
				}
			}
		}

		Location location = new Location(player.getX(), player.getY());
		listLocation.add(location);

		player.nextStep();

		for(int i=listTail.size()-1;i>=0;i--){
			if(listLocation.size() > 1){
				Circle tmp = listTail.get(i);
				Location locTmp = listLocation.get((listLocation.size()-1)-i);
				tmp.setLocation(locTmp.getX(), locTmp.getY());
			}
		}
		// описывается событие пересечения хвоста
		for(int i=0; i<listTail.size(); i++){
			if((player.getX()==listTail.get(i).getX())&&(player.getY()==listTail.get(i).getY())){
				gameWork = false;
				System.out.println("Прищимили хвоост");
			}
		}
		
		
	}

	public void drawWorld() {
		for (Circle boll : listWarld) {
			frame.setChar(boll.getImage(), boll.getX(), boll.getY());
		}

	}

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		Game game = new Game();
		game.startGameLoop();
	}

}
