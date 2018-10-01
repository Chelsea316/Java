import java.io.Serializable;
import java.util.Comparator;

/*public abstract class Nimplayer implements Serializable {*/
public abstract class Nimplayer implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username, given_name, family_name;
	private int numberOfGamePlayed, numberOfGameWon;
	private float winRate;
	// winRate means the rate of win

	public abstract int removeStone(int numberOfMove, int totalOfStone, int upperOfStone);

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public int getNumberOfGamePlayed() {
		return numberOfGamePlayed;
	}

	public void setNumberOfGamePlayed(int numberOfGamePlayed) {
		this.numberOfGamePlayed = numberOfGamePlayed;
	}

	public int getNumberOfGameWon() {
		return numberOfGameWon;
	}

	public void setNumberOfGameWon(int numberOfGameWon) {
		this.numberOfGameWon = numberOfGameWon;
	}

	public float getWinRate() {

		return winRate;
	}

	public void setWinRate(int numberOfGameWon, int numberOfGamePlayed) {

		float a = (float) numberOfGameWon / (float) numberOfGamePlayed;
		this.winRate = a;

	}

	static Comparator<Nimplayer> sortUsername = new Comparator<Nimplayer>() {
		public int compare(Nimplayer p1, Nimplayer p2) {
			return p1.getUsername().compareTo(p2.getUsername());
		}
	};
	// sort usernames
	static Comparator<Nimplayer> sortRateAsc = new Comparator<Nimplayer>() {
		public int compare(Nimplayer p1, Nimplayer p2) {
			if (p1.getWinRate() - p2.getWinRate() > 0) {
				return 1;
			} else if (p1.getWinRate() - p2.getWinRate() < 0) {
				return -1;
			} else
				return p1.getUsername().compareTo(p2.getUsername());
		}
	};
	// sort rate of win in ascend order
	static Comparator<Nimplayer> sortRateDesc = new Comparator<Nimplayer>() {
		public int compare(Nimplayer p1, Nimplayer p2) {
			if (p1.getWinRate() - p2.getWinRate() > 0) {
				return -1;
			} else if (p1.getWinRate() - p2.getWinRate() < 0) {
				return 1;
			} else
				return p1.getUsername().compareTo(p2.getUsername());
		}
	};
	// sort rate of win in descend order
}