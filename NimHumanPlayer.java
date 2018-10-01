
public class NimHumanPlayer extends Nimplayer {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int removeStone(int numberOfMove, int totalOfStone, int upperOfStone) {
		int atLeastOne = 1;
		int i = new Nimsys().min(totalOfStone, upperOfStone);
		try {
			if (numberOfMove > totalOfStone || numberOfMove > upperOfStone || numberOfMove < atLeastOne) {
				throw new Exception();
			}
		} catch (Exception e) {
			System.out.println("\nInvalid move. You must remove between 1 " + "and " + i + " stones.\n");
			System.out.print(totalOfStone + " stones left:");
			NimGame.printStones(totalOfStone);
			return 0;
		}
		return numberOfMove;
	
	}
	//AIplayer use this method to remove stone
}
