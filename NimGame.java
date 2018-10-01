public class NimGame {
	Nimsys nimsys = new Nimsys();
	NimHumanPlayer nimhuman = new NimHumanPlayer();
	NimAIPlayer nimai = new NimAIPlayer();

	/*
	 * NimHumanPlayer nimhuman=new NimHumanPlayer();
	 */ public Nimplayer[] startgame(String information, Nimplayer[] player) {
		String[] playerInformation = information.split(",");
		String winner = null;
		int totalOfStone = Integer.parseInt(playerInformation[0]);
		int upperOfStone = Integer.parseInt(playerInformation[1]);
		int noStoneLeft = 0;
		String player1 = playerInformation[2];
		String player2 = playerInformation[3];
		NimGame Game = new NimGame();
		int j = Game.find(player, player1);
		int k = Game.find(player, player2);
		if (j == -1 || k == -1) {
			System.out.println("One of the players does not exist.");
		} else {
			System.out.println("\nInitial stone count: " + totalOfStone + "\n" + "Maximum stone removal: "
					+ upperOfStone + "\n" + "Player 1: " + player[j].getGiven_name() + " " + player[j].getFamily_name()
					+ "\n" + "Player 2: " + player[k].getGiven_name() + " " + player[k].getFamily_name());
			System.out.print("\n" + totalOfStone + " stones left:");
			printStones(totalOfStone);
			while (totalOfStone > noStoneLeft) {
				int numberOfMove1 = Game.playGame(totalOfStone, upperOfStone, player, j);
				totalOfStone = NimGame.leftStones(numberOfMove1, totalOfStone);
				if (totalOfStone <= noStoneLeft) {
					winner = player[k].getUsername();
					break;
				}
				int numberOfMove2 = Game.playGame(totalOfStone, upperOfStone, player, k);
				totalOfStone = NimGame.leftStones(numberOfMove2, totalOfStone);
				if (totalOfStone <= noStoneLeft) {
					winner = player[j].getUsername();
					break;
				}
			}
			player[j].setNumberOfGamePlayed(player[j].getNumberOfGamePlayed() + 1);
			player[k].setNumberOfGamePlayed(player[k].getNumberOfGamePlayed() + 1);
			if (player[j].getUsername().equals(winner)) {
				NimGame.printWinners(player, j, k, winner);
			}
			if (player[k].getUsername().equals(winner)) {
				NimGame.printWinners(player, k, j, winner);
			}
		}
		return player;
	}

	// upperOfStone means upper bound, totalOfStone means the how many stone left //
	// j==-1;k==-1 just a signal return back from find method.
	private int playGame(int totalOfStone, int upperOfStone, Nimplayer[] player, int j) {
		int numberOfMove = 0;
		while (numberOfMove == 0) {
			System.out.println(player[j].getGiven_name() + "'s turn - remove how many?");
			
			if (player[j] instanceof NimAIPlayer) {
				numberOfMove = nimai.removeStone(numberOfMove, totalOfStone, upperOfStone);
			}else {
				numberOfMove = Nimsys.keyboard.nextInt();
				numberOfMove = nimhuman.removeStone(numberOfMove, totalOfStone, upperOfStone);
			}
		}
		return numberOfMove;
	}

	// numberOfMove means the number of Stone move
	private static int leftStones(int numberOfMove, int totalOfStone) {
		totalOfStone = totalOfStone - numberOfMove;
		if (totalOfStone >= 1) {
			System.out.print("\n" + totalOfStone + " stones left:");
			printStones(totalOfStone);
		} else {
			System.out.println();
		}
		return totalOfStone;
	}

	// return how many stones left after move
	private int find(Nimplayer[] player, String name) {

		for (int i = 0; i < player.length - 1; i++) {
			if (player[i].getUsername().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	// find the index of player in the array .
	// return -1, -1 as a signal to realize the name whether in this array
	static void printStones(int stone) {
		for (int i = 0; i < stone; i++)
			System.out.print(" *");
		System.out.println();
	}

	// print stones
	private static void printWinners(Nimplayer[] player, int a, int b, String winner) {
		player[a].setNumberOfGameWon(player[a].getNumberOfGameWon() + 1);
		winner = player[a].getGiven_name() + " " + player[a].getFamily_name();
		player[a].setWinRate(player[a].getNumberOfGameWon(), player[a].getNumberOfGamePlayed());
		player[b].setWinRate(player[b].getNumberOfGameWon(), player[b].getNumberOfGamePlayed());
		System.out.println("Game Over");
		System.out.println(winner + " " + "wins!");
	}

	// print winners

}
