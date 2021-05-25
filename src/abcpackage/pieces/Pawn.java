package abcpackage.pieces;

import abcpackage.Piece;
import java.util.ArrayList;

import abcpackage.Board;
import abcpackage.Main;
import abcpackage.Move;
import abcpackage.Piece;
import abcpackage.Position;
/**
 * Pawn piece extends Piece class
 * @author Daniel Rogozenski
 */
public class Pawn extends Piece {

	
	/**
	 * declare constructor of Pawn
	 * @param color for the color of the piece
	 */
	public Pawn(String color) {//True is white, false is black
		super(color);

		
	}
	public String toString() {
		return this.getColor() + "p";
	}
	
	/**
	 * checking the validity of the pawn's move
	 * @param startingPosition position for where the pawn begins
	 * @param destPosition for the pawn
	 * @param b board for the pawn's position on the board
	 * @return boolean true if valid and false if invalid
	 */
	//Checking if Pawn move is valid
	public boolean isValid(String startingPosition, String destPosition, Board b){
		//convert inputs into coordinates through letters and numbers
		//get the value from the user from Main class
		
		int prevx = Main.converttoMove(String.valueOf(startingPosition.charAt(0)));
		int prevy = Integer.valueOf(String.valueOf(startingPosition.charAt(1)));
		int tox = Main.converttoMove(String.valueOf(destPosition.charAt(0)));
		int toy = Integer.valueOf(String.valueOf(destPosition.charAt(1)));
		
//		char startingX = input.charAt(0); //a
//		int startingY = input.charAt(1);  //2
//		char destX = input.charAt(3);     //a
//		int destY = input.charAt(4);	  //4
//		int numXstart = Character.getNumericValue(startingX);
//		int numXdest = Character.getNumericValue(destX);
		
		ArrayList<Move> moves = getPossibleMoves(startingPosition, b);
		for(Move m:moves) {
			if(m.getToX() == tox && m.getToY() == toy) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * ArrayList getPossibleMoves for testing storing all possible moves into array
	 * @param startx for starting x int
	 * @param starty for starting y int
	 * @param b for the board - position of the pawn on the board
	 * @return the list of possible moves for the pawn to check validity
	 */
	public ArrayList<Move> getPossibleMoves(int startx, int starty, Board b){//String startpos, Board b){
		//startpos is the position of the current piece,
		//so we know which one it is on the board
		
		ArrayList<Move> moves = new ArrayList<Move>();
		
		
		
		//int startx = Main.converttoMove(String.valueOf(startpos.charAt(0)));
		//int starty = Integer.valueOf(String.valueOf(startpos.charAt(1)));
				
		Position startpo = b.getElement(startx, starty);
		String startcol = startpo.getState().getColor();
		boolean color = true; //color: white is true, black if false
		
		int numSpacesYUp = Math.abs(2-starty);
		int numSpacesYDown = Math.abs(2-starty);
		
		if(startcol.equalsIgnoreCase("b")) {
			color = false;
		}
		if(color == true) {
			//move 'up' for white
			if(startpo.getState().isFirstMove()==true) {
				numSpacesYUp = 2;
			}else {
				numSpacesYUp = 1;
			}
			
			for(int i = 1; i <= numSpacesYUp; i++) {
				int endy = starty - (i);
				if(endy == 0) {
					Position endpo = b.getElement(startx, endy);
					if(endpo.getState() != null) {
						//do nothing, because it cant move forward if something is there

						//Move m = new Move(startx, starty, startx, endy);
						//moves.add(m);
						break;//cant go forward and move there, so break from the loop
						//breaking from the loop wont allow it to get to 2
						
					} else {//the space is empty, it can move there
						Move m = new Move(startx, starty, startx, endy);
						m.setPromotion(true);
						moves.add(m);
					}
				}
				
				if(!(endy <0)) {//check for bounds
					Position endpo = b.getElement(startx, endy);
					if(endpo.getState() != null) {
						//do nothing, because it cant move forward if something is there

						//Move m = new Move(startx, starty, startx, endy);
						//moves.add(m);
						break;//cant go forward and move there, so break from the loop
						//breaking from the loop wont allow it to get to 2
						
					} else {//the space is empty, it can move there
						Move m = new Move(startx, starty, startx, endy);
						if(startpo.getState().isFirstMove()==true && i ==2) {
							//m.setEnpassantDefend(true);
							m.setEnpassX(startx);
							m.setEnpassY(starty-1);
						}
						moves.add(m);
					}
				}
			}
		}
		
		if (color == false) {
			//move 'down' for black
			if(startpo.getState().isFirstMove()==true) {
				numSpacesYDown = 2;
			}else {
				numSpacesYDown = 1;
			}
			for(int i = 1; i <= numSpacesYDown; i++) {
				int endy = starty + (i);
				
				if(endy == 7) {
					Position endpo = b.getElement(startx, endy);
					if(endpo.getState() != null) {
						//do nothing, because it cant move forward if something is there

						//Move m = new Move(startx, starty, startx, endy);
						//moves.add(m);
						break;//cant go forward and move there, so break from the loop
						//breaking from the loop wont allow it to get to 2
						
					} else {//the space is empty, it can move there
						Move m = new Move(startx, starty, startx, endy);
						m.setPromotion(true);
						moves.add(m);
					}
				}
				
				if(!(endy >7)) {//check for bounds
					Position endpo = b.getElement(startx, endy);
					if(endpo.getState() != null) {
						//do nothing, because it cant move forward if something is there

						//Move m = new Move(startx, starty, startx, endy);
						//moves.add(m);
						break;//cant go forward and move there, so break from the loop
						//breaking from the loop wont allow it to get to 2
						
					} else {//the space is empty, it can move there
						Move m = new Move(startx, starty, startx, endy);
						if(startpo.getState().isFirstMove()==true && i ==2) {
							//m.setEnpassantDefend(true);
							m.setEnpassX(startx);
							m.setEnpassY(starty+1);
						}
						moves.add(m);
					}
				}
			}
		}
		
		//up right diag kill
		if(color == true) {
			for(int i = 1; i <= 1; i ++) {
				int endx = startx+i;
				int endy = starty-(i);
				if(endx <8 && endy >=0) {
					Position endpo = b.getElement(endx, endy);
					if (endpo.getState() != null) {
						String endcol = endpo.getState().getColor();
						boolean endcolor = true;
						if (endcol.equalsIgnoreCase("b")) {
							endcolor = false;
						}
						Move m = new Move(startx, starty, endx, endy);
						if(color != endcolor) {
							m.setPieceTaken(true);
							if(endy == 0) {
								m.setPromotion(true);
							}
							if(endpo.getState() instanceof King) {
								m.setCheck(true);
							}else {
		            			m.setCheck(false);
		            		}
							moves.add(m);
							//break;
						}
						else if(color == endcolor) {
							break;
						}
					} else {
						//null, no piece there, so do nothing
					}
				}
			}
		}
		//up left diag kill
		if(color == true) {
			for(int i = 1; i <= 1; i ++) {
				int endx = startx-i;
				int endy = starty-(i);
				if(endx >= 0 && endy>=0) {
					Position endpo = b.getElement(endx, endy);
					if (endpo.getState() != null) {
						String endcol = endpo.getState().getColor();
						boolean endcolor = true;
						if (endcol.equalsIgnoreCase("b")) {
							endcolor = false;
						}
						Move m = new Move(startx, starty, endx, endy);
						if(color != endcolor) {
							m.setPieceTaken(true);
							if(endy==0) {
								m.setPromotion(true);
							}
							if(endpo.getState() instanceof King) {
								m.setCheck(true);
							}else {
		            			m.setCheck(false);
		            		}
							moves.add(m);
							//break;
						}
						else if(color == endcolor) {
							break;
						}
					} else {
						//null, no piece there, so do nothing
					}
				}
			}
		}
		
		//down left diag kill (black)
		if(color == false) {
			for(int i = 1; i <= 1; i ++) {
				int endx = startx-i;
				int endy = starty+(i);
				if(endx > 0 && endy<8) {
					Position endpo = b.getElement(endx, endy);
					if (endpo.getState() != null) {
						String endcol = endpo.getState().getColor();
						boolean endcolor = true;
						if (endcol.equalsIgnoreCase("b")) {
							endcolor = false;
						}
						Move m = new Move(startx, starty, endx, endy);
						if(color != endcolor) {
							m.setPieceTaken(true);
							if(endy==7) {
								m.setPromotion(true);
							}
							if(endpo.getState() instanceof King) {
								m.setCheck(true);
							}else {
		            			m.setCheck(false);
		            		}
							moves.add(m);
							//break;
						}
						else if(color == endcolor) {
							break;
						}
					} else {
						//null, no piece there, so do nothing
					}
				}
			}
		}
		
		
		//down right diag kill (black)
		if(color == false) {
			for(int i = 1; i <= 1; i ++) {
				int endx = startx+i;
				int endy = starty+(i);
				if(endx < 8 && endy<8) {
					Position endpo = b.getElement(endx, endy);
					if (endpo.getState() != null) {
						String endcol = endpo.getState().getColor();
						boolean endcolor = true;
						if (endcol.equalsIgnoreCase("b")) {
							endcolor = false;
						}
						Move m = new Move(startx, starty, endx, endy);
						if(color != endcolor) {
							m.setPieceTaken(true);
							if(endy ==7)
							{
								m.setPromotion(true);
							}
							if(endpo.getState() instanceof King) {
								m.setCheck(true);
							}else {
		            			m.setCheck(false);
		            		}
							moves.add(m);
							//break;
						}
						else if(color == endcolor) {
							break;
						}
					} else {
						//null, no piece there, so do nothing
					}
				}
			}
		}
		
		//up right enpassant kill (white)
		//check the 2nd row to see 3rd row to see if there are any opposing pawns
		//of the 3rd row pawns see if any are currently enpassant
		//if they are enpassant, add killing it as a valid move
		if(color == true) {
			for(int i = 1; i <= 1; i ++) {
				if(starty != 3) {
					break;
				}
				int endx = startx+i;//i is always 1 here
				int endy = starty-(i);
				
				if(endy >7) {
					break;
				}
				if(endy<0) {
					break;
				}
				
				//check to the right of y for a pawn
				
				int px = startx + 1;
				int py = starty;
				Position po = b.getElement(px, py);
				//boolean pocol =false;
				if(po.getState()!= null) {
					String col = po.getState().getColor();
					boolean pocolor = true;
					if (col.equalsIgnoreCase("b")) {
						pocolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != pocolor) {
						if(po.getState().getEnpassant() == true) {
							m.setEnpassantAttack(true);
							m.setEnpassX(px);
							m.setEnpassY(py);
							m.setPieceTaken(true);
							
							moves.add(m);
						}

						//break;
					}
					
				}else {
					//nothing, null
				}
				
			}
		}
		
		
		//up left enpassant kill (white)
		if(color == true) {
			for(int i = 1; i <= 1; i ++) {
				if(starty != 3) {
					break;
				}
				int endx = startx-i;//i is always 1 here
				int endy = starty-(i);
				
				if(endx<0) {
					break;
				}
				if(endy<0) {
					break;
				}
				
				//check to the immediate left of y for a pawn
				
				int px = startx - 1;
				int py = starty;
				Position po = b.getElement(px, py);
				//boolean pocol =false;
				if(po.getState()!= null) {
					String col = po.getState().getColor();
					boolean pocolor = true;
					if (col.equalsIgnoreCase("b")) {
						pocolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != pocolor) {
						if(po.getState().getEnpassant() == true) {
							m.setEnpassantAttack(true);
							m.setEnpassX(px);
							m.setEnpassY(py);
							m.setPieceTaken(true);
							
							moves.add(m);
						}

						//break;
					}
					
				}else {
					//nothing, null
				}
				
			}
		}
		
		
		//down left enpassant kill (black)
		if(color == false) {
			for(int i = 1; i <= 1; i ++) {
				if(starty != 4) {
					break;
				}
				int endx = startx-i;//i is always 1 here
				int endy = starty+(i);
				
				if(endx<0) {
					break;
				}
				if(endy>7) {
					break;
				}
				
				//check to the immediate left of y for a pawn
				
				int px = startx - 1;
				int py = starty;
				Position po = b.getElement(px, py);
				//boolean pocol =false;
				if(po.getState()!= null) {
					String col = po.getState().getColor();
					boolean pocolor = true;
					if (col.equalsIgnoreCase("b")) {
						pocolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != pocolor) {
						if(po.getState().getEnpassant() == true) {
							m.setEnpassantAttack(true);
							m.setEnpassX(px);
							m.setEnpassY(py);
							m.setPieceTaken(true);
							
							moves.add(m);
						}

						//break;
					}
					
				}else {
					//nothing, null
				}
				
			}
		}
		
		
		
		//down right enpassant kill (black)
		if(color == false) {
			for(int i = 1; i <= 1; i ++) {
				if(starty != 4) {
					break;
				}
				int endx = startx+i;//i is always 1 here
				int endy = starty+(i);
				
				if(endx>7) {
					break;
				}
				if(endy>7) {
					break;
				}
				
				//check to the immediate right of y for a pawn
				
				int px = startx + 1;
				int py = starty;
				Position po = b.getElement(px, py);
				//boolean pocol =false;
				if(po.getState()!= null) {
					String col = po.getState().getColor();
					boolean pocolor = true;
					if (col.equalsIgnoreCase("b")) {
						pocolor = false;
					}
					Move m = new Move(startx, starty, endx, endy);
					if(color != pocolor) {
						if(po.getState().getEnpassant() == true) {
							m.setEnpassantAttack(true);
							m.setEnpassX(px);
							m.setEnpassY(py);
							m.setPieceTaken(true);
							
							moves.add(m);
						}

						//break;
					}
					
				}else {
					//nothing, null
				}
				
			}
		}

		
		//movement of pawn
		 //returns
		
		return moves;
	}
}
	
		//for WHITE pieces
//		if (startingY == 50){ //moving two squares from the starting point for pawns 50 is ascii for 2
//			if (destY == 52 || destY == 51){ // for example, a2 can go to either a3 or a4 51 is 3, 52 is 4
//			// BUT THERE MUST BE NOTHING ON SQUARE 4 OR 3
//				return true;
//			}
//		}
//		
//		if (destY <= startingY){ //pawns cannot move backwards in any direction
//			return false;
//		}
//		
//		if (startingX != destX) { //like a2 b3, must be a kill, so nums must differ by 1
//			if (numXdest - numXstart != 1 || destY - startingY != 1 || or something white/nothing is there)
//				return false;
//			}
//			else {
//				return true;
//			}
//			
//		else if (destY - startingY == 1){
//			return true;
//		}
//	}

