package explorationgame;
import java.io.* ;
import java.util.ArrayList;
import java.util.Collections;



public class Highscore implements Serializable, Comparable<Highscore> {
	private static final long serialVersionUID = 1L;
	
	protected Actor actor ;
	protected int score ;
	final static String filepath = "resources\\highscore.dat";
	public Highscore(Actor actor) {
		this.actor = actor;
		this.score = actor.getTurns() * 100;
	}
	public void writeToFile() throws IOException {
		if ((new File(filepath)).exists()) {
			ArrayList<Highscore> allhs = readAllHighscores();
			allhs.add(this);
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filepath)));
			for (Highscore element : allhs) {
				oos.writeObject(element);
			}
			oos.close();
		}
		else {
			ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(filepath)));
			oos.writeObject(this);
			oos.close();
		}
	}

	public static ArrayList<Highscore> readAllHighscores() {
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream(new File(filepath)));
		} catch (IOException e1) {
			System.out.println("Error: " + e1);
			return null;
		}
		
		ArrayList<Highscore> hslist = new ArrayList<Highscore>();
		
		while (true) {
			try {
				hslist.add((Highscore) ois.readObject());
			}
			catch (Exception e) {
			//	e.printStackTrace();
				break;
			}
		}
		
		try {
			ois.close();
		} catch (IOException e) {
			System.out.println("Error: " + e);
		}
		
		Collections.sort(hslist);
		return hslist;
	}
	
	@Override
	public String toString() {
		return actor.getName() + " : " + score ;
	}
	
	@Override
	public int compareTo(Highscore o) {
		if (this.score > o.score) {
			return 1;
		}
		else if (this.score < o.score) {
			return -1;
		}
		else {
			return 0;
		}
	}
	public static void deleteHighscores() {
		try{
    		File file = new File(filepath);
    		if(file.delete()){
    			System.out.println(file.getName() + " is deleted!");
    		}else{
    			System.out.println("Delete operation failed.");
    		}
    	}
		catch(Exception e){
    		e.printStackTrace();
    	}
	}
	
}
