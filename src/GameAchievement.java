import java.util.Comparator;

public class GameAchievement {
	String name;
	int score;

	public GameAchievement(String name, int score) {
		super();
		this.name = name;
		this.score = score;
	}

	public GameAchievement(String s) {
		String[] values = s.split(":");
		name = values[0];
		score = Integer.valueOf(values[1]);

	}

	public String getStringFormat() {
		return String.format("%s:%d", name, score);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return "GameAchievement [name=" + name + ", score=" + score + "]";
	}


}
