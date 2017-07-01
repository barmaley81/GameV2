import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HistoryGame {

	public void saveData(GameAchievement ga) {
		try {

			File file = new File("/saveData.txt");
			if (!file.exists()) {
				file.createNewFile();
			}
			FileOutputStream fos = new FileOutputStream(file, true);
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
			bw.write(ga.getStringFormat());
			bw.newLine();
			bw.flush();
			bw.close();
			System.out.println("Path: " + file.getAbsolutePath());

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void showHistory() {
		ArrayList<GameAchievement> list = new ArrayList();
		try {			
			BufferedReader reader = new BufferedReader(new FileReader(new File("/saveData.txt")));
			while (reader.ready()) {
				String s = reader.readLine();
				GameAchievement ga = new GameAchievement(s);
				list.add(ga);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("History result");
		
		Collections.sort(list,new Comparator<GameAchievement>() {

			@Override
			public int compare(GameAchievement o1, GameAchievement o2) {
				return o1.score > o2.score ? -1 : 1;
			}
		});
		
		
		
		
		for(int i=0; i<3; i++){
			System.out.println(list.get(i));
		}
	}

}
