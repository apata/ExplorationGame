package explorationgame;

import java.io.IOException;

public class Testhighscore {
	public static void main(String[] args) throws IOException, ClassNotFoundException {
//		Actor a1 = new Player("Tere");
//		Highscore hs = new Highscore(a1);
//		hs.writeToFile();
//		Actor a2 = new Player("Terevanakere");
//		Highscore hs2 = new Highscore(a2);
//		hs2.writeToFile();
		System.out.println(Highscore.readAllHighscores());
//		Highscore.deleteHighscores();
	}

}
