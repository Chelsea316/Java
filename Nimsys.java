
/*Author statement:
 * This is projB written by CHUNXUE WEI
 * The university of Melbourne
 * student number:929150  29/04/2018
 */
import java.util.Arrays;
import java.util.Scanner;
import java.io.*;

public class Nimsys {
	static Scanner keyboard = new Scanner(System.in);
	static String commandSentence;
	static Nimsys nimsys = new Nimsys();

	public static void main(String[] args) {
		NimGame Game = new NimGame();
		int playerNumber = 1;
		Nimplayer[] player = new Nimplayer[playerNumber];
		String playOrNo = "Y";
		File file = new File("test.dat");//set the file
		FileOutputStream out;
		FileInputStream in;
		try {
			in = new FileInputStream(file);
			ObjectInputStream obin = new ObjectInputStream(in);
			player = (Nimplayer[]) obin.readObject(); // set the object and named obin 
			obin.close();
		} catch (IOException e) {
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println("Welcome to Nim\n");
		System.out.print("$");
		while (playOrNo == "Y") {
			commandSentence = keyboard.nextLine();
			String[] command = commandSentence.split(" ");
			command = Arrays.copyOf(command, 2);
			try {
				if (command[0].equals("addplayer")) {
					try {
						player = nimsys.addplayer(command[1], player);
					} catch (Exception e) {
						System.out.println("Incorrect number of arguments supplied to command.");
					}
					System.out.print("\n$");
				} else if (command[0].equals("addaiplayer")) {
					try {
						player = nimsys.addaiplayer(command[1], player);
					} catch (Exception ArrayIndexOutOfBounds) {
						System.out.println("Incorrect number of arguments supplied to command.");
					}
					System.out.print("\n$");
				} else if (command[0].equals("removeplayer")) {
					player = nimsys.removeplayer(command[1], player);

					System.out.print("\n$");
				} else if (command[0].equals("editplayer")) {
					try {
						player = nimsys.editplayer(command[1], player);
					} catch (Exception ArrayIndexOutOfBounds) {
						System.out.println("Incorrect number of arguments supplied to command.");
					}
					System.out.print("\n$");
				} else if (command[0].equals("resetstats")) {
					player = nimsys.resetstats(command[1], player);
					System.out.print("\n$");
				} else if (command[0].equals("displayplayer")) {
					player = nimsys.displayplayer(command[1], player);
					System.out.print("\n$");
				} else if (command[0].equals("rankings")) {
					player = nimsys.rankings(commandSentence, player);
					System.out.print("\n$");
				} else if (command[0].equals("startgame")) {
					try {
						player = Game.startgame(command[1], player);
					} catch (Exception ArrayIndexOutOfBounds) {
						System.out.println("Incorrect number of arguments supplied to command.");
					}

					System.out.print("\n$");
				} else if (command[0].equals("exit")) {
					playOrNo = "N";
					try {
						out = new FileOutputStream(file);
						ObjectOutputStream ob = new ObjectOutputStream(out);
						ob.writeObject(player);//write the object to the file
						ob.flush();
						ob.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
					System.out.print("\n");
				} else {
					if (!command[0].equals("")) {
						throw new Exception();
					}
				}
			} catch (Exception e) {
				System.out.print("'" + command[0] + "' is not a valid command.\n\n$");
			}
		}
	}

	private Nimplayer[] addplayer(String commandName, Nimplayer[] player) {
		String[] name = commandName.split(",");
		for (int i = 0; i < player.length - 1; i++) {
			if (name[2] != null && player[i].getUsername().equals(name[0])) {
				System.out.println("The player already exists.");
				return player;
			}
		}
		player[player.length - 1] = new NimHumanPlayer();
		player[player.length - 1].setUsername(name[0]);
		player[player.length - 1].setFamily_name(name[1]);
		player[player.length - 1].setGiven_name(name[2]);
		player[player.length - 1].setNumberOfGamePlayed(0);
		player[player.length - 1].setNumberOfGameWon(0);
		player = Arrays.copyOf(player, player.length + 1);
		player[player.length - 1] = new NimHumanPlayer();
		return player;
	}

	private Nimplayer[] addaiplayer(String commandName, Nimplayer[] player) {
		String[] name = commandName.split(",");
		for (int i = 0; i < player.length - 1; i++) {
			if (name[2] != null && player[i].getUsername().equals(name[0])) {
				System.out.println("The player already exists.");
				return player;
			}
		}
		player[player.length - 1] = new NimAIPlayer();
		player[player.length - 1].setUsername(name[0]);
		player[player.length - 1].setFamily_name(name[1]);
		player[player.length - 1].setGiven_name(name[2]);
		player[player.length - 1].setNumberOfGamePlayed(0);
		player[player.length - 1].setNumberOfGameWon(0);
		player = Arrays.copyOf(player, player.length + 1);
		player[player.length - 1] = new NimAIPlayer();
		return player;
	}

	// add player. i>=0, "0"means from the 1st guy in the array.
	private Nimplayer[] removeplayer(String commandUsername, Nimplayer[] player) {
		if (commandUsername == null) {
			System.out.println("Are you sure you want to remove all players? (y/n)");
			String command = Nimsys.keyboard.nextLine();
			if (command.equals("y")) {
				Nimplayer[] player1 = new Nimplayer[1];
				player = player1;
				return player;
			}
		} else {
			String name = commandUsername;
			Nimplayer[] newPlayer = new Nimplayer[player.length - 1];
			int MAXJ = (player.length - 1) - 1;
			for (int j = 0; j < player.length - 1; j++) {
				if (player[j].getUsername().equals(name)) {
					newPlayer = Arrays.copyOf(player, j);
					newPlayer = Arrays.copyOf(newPlayer, player.length - 1);
					for (int k = j; k < player.length - 1; k++) {
						if (player[j] instanceof NimHumanPlayer) {
							newPlayer[k] = new NimHumanPlayer();
						} else {
							newPlayer[k] = new NimAIPlayer();
						}
						newPlayer[k] = player[k + 1];
					}
					return newPlayer;
				} else if (j == MAXJ) {
					System.out.println("The player does not exist.");
				}
			}
		}
		return player;
	}

	// the last element in the player array is null, so the for loop from 0 to
	// player.length-1;so MAXJ(maximum j==player.length-1-1)
	private Nimplayer[] editplayer(String commandName, Nimplayer[] player) {
		String[] name = commandName.split(",");
		if (player[0].getUsername() == null) {
			System.out.println("The player does not exist.");
			return player;
		} else {
			player = Arrays.copyOf(player, player.length - 1);
			for (int j = 0; j < player.length; j++) {
				if (player[j].getUsername().equals(name[0])) {
					player[j].setGiven_name(name[2]);
					player[j].setFamily_name(name[1]);
					player = Arrays.copyOf(player, player.length + 1);
					player[player.length - 1] = new NimHumanPlayer();
					return player;
				} else {
					if (j == player.length - 1) {
						System.out.println("The player does not exist.");
					}
				}
			}
		}
		player = Arrays.copyOf(player, player.length + 1);
		player[player.length - 1] = new NimHumanPlayer();
		return player;
	}
	// edit some players , change their family name or given name

	private Nimplayer[] resetstats(String commandUsername, Nimplayer[] player) {
		if (commandUsername == null) {
			System.out.println("Are you sure you want to reset all player statistics? (y/n)");
			String YorN = Nimsys.keyboard.nextLine();
			if (YorN.equals("y")) {
				for (int j = 0; j < player.length - 1; j++) {
					player[j].setNumberOfGamePlayed(0);
					player[j].setNumberOfGameWon(0);
				}
			}
			return player;
		} else {
			String name = commandUsername;
			int MAXJ = (player.length - 1) - 1;
			for (int j = 0; j < player.length - 1; j++) {
				if (player[j].getUsername().equals(name)) {
					player[j].setNumberOfGamePlayed(0);
					player[j].setNumberOfGameWon(0);
					return player;
				} else {
					if (j == MAXJ) {
						System.out.println("The player does not exist.");

					}
				}
			}
		}
		return player;
	}
	// reset some players

	private Nimplayer[] displayplayer(String commandUsername, Nimplayer[] player) {
		player = Arrays.copyOf(player, player.length - 1);
		Arrays.sort(player, Nimplayer.sortUsername);
		for (int j = 0; j < player.length; j++) {
			if (commandUsername == null) {
				System.out.println(player[j].getUsername() + "," + player[j].getGiven_name() + ","
						+ player[j].getFamily_name() + "," + player[j].getNumberOfGamePlayed() +
						" games,"+ player[j].getNumberOfGameWon() + " wins");
			} else {
				if (player[j].getUsername().equals(commandUsername)) {
					System.out.println(player[j].getUsername() + "," + player[j].getGiven_name() + ","
							+ player[j].getFamily_name() + "," + player[j].getNumberOfGamePlayed() +
							" games,"+ player[j].getNumberOfGameWon() + " wins");
					player = Arrays.copyOf(player, player.length + 1);
					player[player.length - 1] = new NimHumanPlayer();
					return player;
				} else {
					if (j == player.length - 1) {
						System.out.println("The player does not exist.");
					}
				}
			}
		}
		player = Arrays.copyOf(player, player.length + 1);
		player[player.length - 1] = new NimHumanPlayer();
		return player;
	}

	// show player(s)
	private Nimplayer[] rankings(String command, Nimplayer[] player) {
		player = Arrays.copyOf(player, player.length - 1);
		int percent = 100;
		if (command.equals("rankings")) {
			Arrays.sort(player, Nimplayer.sortRateDesc);
		}
		if (command.equals("rankings asc")) {
			Arrays.sort(player, Nimplayer.sortRateAsc);
		}
		for (int j = 0; j < min(player.length, 10); j++) {
			int percentage = Math.round((player[j].getWinRate() * percent));
			System.out.println(String.format("%1$-5s", percentage + "%") + "| "
					+ String.format("%02d", player[j].getNumberOfGamePlayed()) + " games | " 
					+ player[j].getGiven_name()
					+ " " + player[j].getFamily_name());

		}
		player = Arrays.copyOf(player, player.length + 1);
		return player;
	}

	// percent=100, 100 is change the decimal to percentage.
	// rank all players
	public int min(int a, int b) {
		int i = 0;
		if (a <= b)
			i = a;
		if (a > b)
			i = b;
		return i;
	}
	// compare a,b which is smaller

}
