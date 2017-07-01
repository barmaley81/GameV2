import java.util.Comparator;

public class ComparatorAch implements Comparator<GameAchievement>{

	@Override
	public int compare(GameAchievement o1, GameAchievement o2) {
		// TODO Auto-generated method stub
		return o1.score > o2.score ? 1 : -1;
	}

}
