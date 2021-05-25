package abcpackage.pieces;

import java.util.ArrayList;

import abcpackage.Board;
import abcpackage.Main;
import abcpackage.Move;
import abcpackage.Piece;
import abcpackage.Position;
/**
 * Knight class extends Piece class
 * @author Daniel Rogozenski
 */
public class Knight extends Piece {

	/**
	 * fetching the color of the knight piece
	 * @param color for the color of the piece (string)
	 * constructor defined for knight
	 */
	public Knight(String color) {//True is white, false is black
		super(color);

	}
	/**
	 * return string for the color + N for knight
	 * no parameters
	 */
	public String toString() {
		
		return this.getColor() + "N";
	}
	
	
	/**
	 * isValid method for checking if the move set by user is valid
	 * @param startingPosition for the starting position of piece
	 * @param destPosition for the destination for piece
	 * @param b for the position of knight on the board
	 * @return boolean true/false for valid and invalid respectively
	 */
	//Checking if knight's move is valid
		public boolean isValid(String startingPosition, String destPosition, Board b){
			//convert inputs into coordinates through letters and numbers
			//get the value from the user from Main class
			
			int prevx = Main.converttoMove(String.valueOf(startingPosition.charAt(0)));
			int prevy = Integer.valueOf(String.valueOf(startingPosition.charAt(1)));
			int tox = Main.converttoMove(String.valueOf(destPosition.charAt(0)));
			int toy = Integer.valueOf(String.valueOf(destPosition.charAt(1)));
			
//			char startingX = input.charAt(0); //a
//			int startingY = input.charAt(1);  //2
//			char destX = input.charAt(3);     //a
//			int destY = input.charAt(4);	  //4
//			int numXstart = Character.getNumericValue(startingX);
//			int numXdest = Character.getNumericValue(destX);
			
			ArrayList<Move> moves = getPossibleMoves(startingPosition, b);
			//System.out.println("knight moves size: " + moves.size());
			for(Move m:moves) {
				if(m.getToX() == tox && m.getToY() == toy) {
					return true;
				}
			}
			return false;
		}
		
		/**
		 * ArrayList for getPossibleMoves to assess all possible moves for the knight to execute
		 * @param startx for the starting x position
		 * @param starty for the starting y position
		 * @param b for the position of knight on the board
		 */
		public ArrayList<Move> getPossibleMoves(int startx, int starty, Board b){//String startpos, Board b){
			//System.out.println("Knight x:" + startx + " y:" + starty);
			
			//startpos is the position of the current piece,
			//so we know which one it is on the board
			
			ArrayList<Move> moves = new ArrayList<Move>();
			
			
			
			//int startx = Main.converttoMove(String.valueOf(startpos.charAt(0)));
			//int starty = Integer.valueOf(String.valueOf(startpos.charAt(1)));
					
			Position startpo = b.getElement(startx, starty);
			String startcol = startpo.getState().getColor();
			boolean color = true; //color: white is true, black if false
			if(startcol.equalsIgnoreCase("b")) {
				color = false;
			}
			
			//movement of knight
			//up right?
			for(int i = 0; i < 1; i++) {
				int endx = startx+1;
				int endy = starty-2;
				//check bounds
				if(endy<0) {
					break;
				}
				if(endx>7) {
					break;
				}
				
				//System.out.println("ke x:" + (endx) + "y: " + (endy));
				Position endpo = b.getElement(endx,  endy);
				if (endpo.getState() != null) {
					//System.out.println("knight get state 1");
					String endcol = endpo.getState().getColor();
					boolean endcolor = true;
					if (endcol.equalsIgnoreCase("b")) {
						endcolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != endcolor) {
						m.setPieceTaken(true);
						if(endpo.getState() instanceof King) {
							m.setCheck(true);
						}else {
	            			m.setCheck(false);
	            		}
						moves.add(m);
						//System.out.println("knight move added get state");
					}
					else if(color == endcolor) {
						break;
					}
				}
				else {//the space is empty, it can move there
					Move m = new Move(startx, starty, endx, endy);
					moves.add(m);
					//System.out.println("knight move added 2");
				}
				
			}
			//up left
			for(int i = 0; i < 1; i++) {
				int endx = startx-1;
				int endy = starty-2;
				//check bounds
				if(endy<0) {
					break;
				}
				if(endx<0) {
					break;
				}
				//System.out.println("ke x:" + (endx) + "y: " + (endy));
				Position endpo = b.getElement(endx,  endy);
				if (endpo.getState() != null) {
					String endcol = endpo.getState().getColor();
					boolean endcolor = true;
					if (endcol.equalsIgnoreCase("b")) {
						endcolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != endcolor) {
						m.setPieceTaken(true);
						if(endpo.getState() instanceof King) {
							m.setCheck(true);
						}else {
	            			m.setCheck(false);
	            		}
						moves.add(m);
					}
					else if(color == endcolor) {
						break;
					}
				}
				else {
					Move m = new Move(startx, starty, endx, endy);
					moves.add(m);
				}
				
			}
			//down left
			for(int i = 0; i < 1; i++) {
				int endx = startx-1;
				int endy = starty+2;
				//check bounds
				if(endy>7) {
					break;
				}
				if(endx<0) {
					break;
				}
				//System.out.println("ke x:" + (endx) + "y: " + (endy));
				Position endpo = b.getElement(endx,  endy);
				if (endpo.getState() != null) {
					String endcol = endpo.getState().getColor();
					boolean endcolor = true;
					if (endcol.equalsIgnoreCase("b")) {
						endcolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != endcolor) {
						m.setPieceTaken(true);
						if(endpo.getState() instanceof King) {
							m.setCheck(true);
						}else {
	            			m.setCheck(false);
	            		}
						moves.add(m);
					}
					else if(color == endcolor) {
						break;
					}
				}
				else {
					Move m = new Move(startx, starty, endx, endy);
					moves.add(m);
				}
				
			}
			//down right
			for(int i = 0; i < 1; i++) {
				int endx = startx+1;
				int endy = starty+2;
				//check bounds
				if(endy>7) {
					break;
				}
				if(endx>7) {
					break;
				}
				//System.out.println("ke x:" + (endx) + "y: " + (endy));
				Position endpo = b.getElement(endx,  endy);
				if (endpo.getState() != null) {
					String endcol = endpo.getState().getColor();
					boolean endcolor = true;
					if (endcol.equalsIgnoreCase("b")) {
						endcolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != endcolor) {
						m.setPieceTaken(true);
						if(endpo.getState() instanceof King) {
							m.setCheck(true);
						}else {
	            			m.setCheck(false);
	            		}
						moves.add(m);
					}
					else if(color == endcolor) {
						break;
					}
				}
				else {
					Move m = new Move(startx, starty, endx, endy);
					moves.add(m);
				}
				
			}
			
			//right up
			for(int i = 0; i < 1; i++) {
				int endx = startx+2;
				int endy = starty-1;
				//check bounds
				if(endy<0) {
					break;
				}
				if(endx>7) {
					break;
				}
				//System.out.println("ke x:" + (endx) + "y: " + (endy));
				Position endpo = b.getElement(endx,  endy);
				if (endpo.getState() != null) {
					String endcol = endpo.getState().getColor();
					boolean endcolor = true;
					if (endcol.equalsIgnoreCase("b")) {
						endcolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != endcolor) {
						m.setPieceTaken(true);
						if(endpo.getState() instanceof King) {
							m.setCheck(true);
						}else {
	            			m.setCheck(false);
	            		}
						moves.add(m);
					}
					else if(color == endcolor) {
						break;
					}
				}
				else {
					Move m = new Move(startx, starty, endx, endy);
					moves.add(m);
				}
				
			}
			
			//right down
			for(int i = 0; i < 1; i++) {
				int endx = startx+2;
				int endy = starty+1;
				//check bounds
				if(endy>7) {
					break;
				}
				if(endx>7) {
					break;
				}
				//System.out.println("ke x:" + (endx) + "y: " + (endy));
				Position endpo = b.getElement(endx,  endy);
				if (endpo.getState() != null) {
					String endcol = endpo.getState().getColor();
					boolean endcolor = true;
					if (endcol.equalsIgnoreCase("b")) {
						endcolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != endcolor) {
						m.setPieceTaken(true);
						if(endpo.getState() instanceof King) {
							m.setCheck(true);
						}else {
	            			m.setCheck(false);
	            		}
						moves.add(m);
					}
					else if(color == endcolor) {
						break;
					}
				}
				else {
					Move m = new Move(startx, starty, endx, endy);
					moves.add(m);
				}
				
			}
			
			//left down
			for(int i = 0; i < 1; i++) {
				int endx = startx-2;
				int endy = starty+1;
				//check bounds
				if(endy>7) {
					break;
				}
				if(endx<0) {
					break;
				}
				//System.out.println("ke x:" + (endx) + "y: " + (endy));
				Position endpo = b.getElement(endx,  endy);
				if (endpo.getState() != null) {
					String endcol = endpo.getState().getColor();
					boolean endcolor = true;
					if (endcol.equalsIgnoreCase("b")) {
						endcolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != endcolor) {
						m.setPieceTaken(true);
						if(endpo.getState() instanceof King) {
							m.setCheck(true);
						}else {
	            			m.setCheck(false);
	            		}
						moves.add(m);
					}
					else if(color == endcolor) {
						break;
					}
				}
				else {
					Move m = new Move(startx, starty, endx, endy);
					moves.add(m);
				}
				
			}
			
			//left up
			for(int i = 0; i < 1; i++) {
				int endx = startx-2;
				int endy = starty-1;
				//check bounds
				if(endy<0) {
					break;
				}
				if(endx<0) {
					break;
				}
				//System.out.println("ke x:" + (endx) + "y: " + (endy));
				Position endpo = b.getElement(endx,  endy);
				if (endpo.getState() != null) {
					String endcol = endpo.getState().getColor();
					boolean endcolor = true;
					if (endcol.equalsIgnoreCase("b")) {
						endcolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != endcolor) {
						m.setPieceTaken(true);
						if(endpo.getState() instanceof King) {
							m.setCheck(true);
						}else {
	            			m.setCheck(false);
	            		}
						moves.add(m);
					}
					else if(color == endcolor) {
						break;
					}
				}
				else {
					Move m = new Move(startx, starty, endx, endy);
					moves.add(m);
				}
				
			}
			
			
			return moves;
		}
	//checking if knight move is valid
//	public boolean isValid(String startingPosition, String destPosition){
//		//convert inputs into coordinates through letters and numbers
//		//get the value from the user from Main class
//		char startingX = input.charAt(0);
//		int startingY = input.charAt(1);
//		char destX = input.charAt(3);
//		int destY = input.charAt(4);
//		int numXstart = Character.getNumericValue(startingX); //convert letters to numeric values
//		int numXdest = Character.getNumericValue(destX);
//		
//		//for WHITE pieces
//		if (Math.abs(numXdest - numXstart) == 1 && Math.abs(startingY - destY) == 2 && NO WHITE PIECE) {
//			return true;
//		}
//		else if (Math.abs(numXdest - numXstart) == 2 && Math.abs(startingY - destY) == 1 && NO WHITE PIECE) {
//			return true;
//		}
//		else {
//			return false;
//		}
	

	
}
