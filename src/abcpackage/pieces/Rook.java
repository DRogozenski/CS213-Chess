package abcpackage.pieces;

import java.util.ArrayList;

import abcpackage.Board;
import abcpackage.Main;
import abcpackage.Move;
import abcpackage.Piece;
import abcpackage.Position;
/**
 * Rook class extends Piece class
 * @author Daniel Rogozenski
 */
public class Rook extends Piece {

	/**
	 * declaring constructor for rook
	 * @param color for color + R
	 */
	public Rook(String color) {//True is white, false is black
		super(color);
		

	}
	
	
	public String toString() {
		
		return this.getColor() + "R";
	}
	
	//Checking if rook's move is valid
	/**
	 * checking if the move of the Rook is valid
	 * @param startPos for starting position of the rook
	 * @param destPos for the destination of the rook
	 * @param b for the position of the rook on the board
	 * @return boolean type true for valid and false for invalid
	 */
	public boolean isValid(String startPos, String destPos, Board b){
		
		int prevx = Main.converttoMove(String.valueOf(startPos.charAt(0)));
		int prevy = Integer.valueOf(String.valueOf(startPos.charAt(1)));
		int tox = Main.converttoMove(String.valueOf(destPos.charAt(0)));
		int toy = Integer.valueOf(String.valueOf(destPos.charAt(1)));
		
		ArrayList<Move> moves = getPossibleMoves(startPos, b);
		for(Move m :moves) {
			if(m.getToX() == tox && m.getToY() == toy) {
				return true;//or we could return the Move/null, which will tell us the populated info of the Move object (piecetaken/promotion of whatever)
			}
			
		}
		
		return false;
	}
		
		
	/**
	 * ArrayList getPossibleMoves for the move
	 * @param startx for starting x position
	 * @param starty for starting y position
	 * @param b for the position of rook on the board
	 * @return list of possible moves of the rook
	 */
	public ArrayList<Move> getPossibleMoves(int startx, int starty, Board b){//String startpos, Board b){
		//startpos is the position of the current piece,
		//so we know which one it is on the board
		
		ArrayList<Move> moves = new ArrayList<Move>();
		
		
		
		//int startx = Main.converttoMove(String.valueOf(startpos.charAt(0)));
		//int starty = Integer.valueOf(String.valueOf(startpos.charAt(1)));
					
		Position startpo = b.getElement(startx, starty);
		String startcol = startpo.getState().getColor();
		boolean color = true;//color: white is true, black if false
		if(startcol.equalsIgnoreCase("b")) {
			color = false;
		}
		
		
		//go up and down
		//Number of spaces to move in X and Y direction
        int numSpacesYUp = starty;//Not sure if this math is correct
        int numSpacesYDown = Math.abs(8-(starty+1));//^
		
        //up, make a move for each possible space it can move in the up direction
        for(int i =1;i<=numSpacesYUp;i++) {
        	int endy = starty-(i);
    		Position endpo = b.getElement(startx, endy);
    		if(endpo.getState() != null) {//theres a piece at the end loc
        		String endcol = endpo.getState().getColor();
        		boolean endcolor = true;//color: white is true, black if false
        		if(endcol.equalsIgnoreCase("b")) {
        			endcolor = false;
        		}
        		Move m = new Move(startx, starty, startx, endy);
            	if(color != endcolor) {
            		m.setPieceTaken(true);
            		if(endpo.getState() instanceof King) {
            			m.setCheck(true);
            		}else {
            			m.setCheck(false);
            		}
            		moves.add(m);
            		//break;//can't get any more in the upper right diag because there would be a piece blocking its way
            	}else if(color == endcolor) {
            		//we don't add the move, because its not possible :/
            		//you cant move to a location where you already have a piece
            		//break;
            	}
            	break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, startx, endy);
    			moves.add(m);
    		}
        }
        
        //down
        for(int i =1;i<=numSpacesYDown;i++) {
        	int endy=starty+(i);
        	
    		Position endpo = b.getElement(startx, endy);
    		if(endpo.getState() != null) {//theres a piece at the end loc
        		String endcol = endpo.getState().getColor();
        		boolean endcolor = true;//color: white is true, black if false
        		if(endcol.equalsIgnoreCase("b")) {
        			endcolor = false;
        		}
        		Move m = new Move(startx, starty, startx, endy);
            	if(color != endcolor) {
            		m.setPieceTaken(true);
            		if(endpo.getState() instanceof King) {
            			m.setCheck(true);
            		}else {
            			m.setCheck(false);
            		}
            		moves.add(m);
            		//break;//can't get any more in the upper right diag because there would be a piece blocking its way
            	}else if(color == endcolor) {
            		//we don't add the move, because its not possible :/
            		//you cant move to a location where you already have a piece
            		//break;
            	}
            	break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, startx, endy);
    			moves.add(m);
    		}
        }
        
        //go left and right
		int numSpacesXLeft=Math.abs(startx);//TO DO: Add Math
		int numSpacesXRight =Math.abs(8-(startx+1));
        
		
        //left
        for(int i =1;i<=numSpacesXLeft;i++) {
        	int endx=startx-(i);
        	
    		Position endpo = b.getElement(endx, starty);
    		if(endpo.getState() != null) {//theres a piece at the end loc
        		String endcol = endpo.getState().getColor();
        		boolean endcolor = true;//color: white is true, black if false
        		if(endcol.equalsIgnoreCase("b")) {
        			endcolor = false;
        		}
        		Move m = new Move(startx, starty, endx, starty);
            	if(color != endcolor) {
            		m.setPieceTaken(true);
            		if(endpo.getState() instanceof King) {
            			m.setCheck(true);
            		}else {
            			m.setCheck(false);
            		}
            		moves.add(m);
            		//break;//can't get any more in the upper right diag because there would be a piece blocking its way
            	}else if(color == endcolor) {
            		//we don't add the move, because its not possible :/
            		//you cant move to a location where you already have a piece
            		//break;
            	}
            	break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, starty);
    			moves.add(m);
    		}
        }
        
        //right
        for(int i =1;i<=numSpacesXRight;i++) {
        	
        	int endx=startx+i;
        	
    		Position endpo = b.getElement(endx, starty);
    		if(endpo.getState() != null) {//theres a piece at the end loc
        		String endcol = endpo.getState().getColor();
        		boolean endcolor = true;//color: white is true, black if false
        		if(endcol.equalsIgnoreCase("b")) {
        			endcolor = false;
        		}
        		Move m = new Move(startx, starty, endx, starty);
            	if(color != endcolor) {
            		m.setPieceTaken(true);
            		if(endpo.getState() instanceof King) {
            			m.setCheck(true);
            		}else {
            			m.setCheck(false);
            		}
            		moves.add(m);
            		//break;//can't get any more in the upper right diag because there would be a piece blocking its way
            	}else if(color == endcolor) {
            		//we don't add the move, because its not possible :/
            		//you cant move to a location where you already have a piece
            		//break;
            	}
            	break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, starty);
    			moves.add(m);
    		}
        	
        }
		

        
		
		
		return moves;//Return all possible legal moves
	}
//	
//	public boolean isValid(String startingPosition, String destPosition){
//		//convert inputs into coordinates through letters and numbers
//		//get the value from the user from Main class
//		char startingX = input.charAt(0); 
//		int startingY = input.charAt(1);  
//		char destX = input.charAt(3);     
//		int destY = input.charAt(4);	  
//		int numXstart = Character.getNumericValue(startingX); //a=10
//		int numXdest = Character.getNumericValue(destX);	  //a=10
//		
//		//for WHITE pieces 
//		if (numXstart == numXdest && Math.abs(startingY - destY) <= 7){
//			return true;
//		}
//		else if (Math.abs(numXstart - numXdest) <= 7 && startingY == destY) {
//			return true;
//		}
//		else {
//			return false;
//		}
	

	
}
