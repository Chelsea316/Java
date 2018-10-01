/*
	NimAIPlayer.java
	
	This class is provided as a skeleton code for the tasks of 
	Sections 2.4, 2.5 and 2.6 in Project C. Add code (do NOT delete any) to it
	to finish the tasks. 
*/

public class NimAIPlayer extends Nimplayer implements Testable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// you may further extend a class or implement an interface
	// to accomplish the tasks.	

	public NimAIPlayer() {
				
	}
	
	public String advancedMove(boolean[] available, String lastMove) {
		// the implementation of the victory
		// guaranteed strategy designed by you
		String move = "";
		
		return move;
	}

	public int removeStone(int numberOfMove, int totalOfStone, int upperOfStone) {
		int k;
		int atLeastOne=1;
		k=(int) (Math.floor(totalOfStone-1)/(upperOfStone+1));
		numberOfMove=totalOfStone-(k*(upperOfStone+1)+1);
		try{if (numberOfMove > totalOfStone || numberOfMove < atLeastOne) {
			throw new Exception();
		}
	} catch (Exception e) {
		if(upperOfStone>totalOfStone) {
			upperOfStone=totalOfStone;
		}
		 numberOfMove=new java.util.Random().nextInt(upperOfStone)+1;
	}
		return numberOfMove;
	}
// AIplayer use this method to remove stone
	// because Random would choose number from 0 to n, including 0 doesn't include n, 
	//so I add "1" after that.
}