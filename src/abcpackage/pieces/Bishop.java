package abcpackage.pieces;

import java.util.ArrayList;

import abcpackage.Board;
import abcpackage.Main;
import abcpackage.Move;
import abcpackage.Piece;
import abcpackage.Position;
/**
 * Bishop class extends Piece class
 * @author Daniel Rogozenski
 */
public class Bishop extends Piece {

	/**
	 * constructor definition for bishop
	 * @param color for the color of the piece (string)
	 */
	public Bishop(String color) {//True is white, false is black
		super(color);

	}
	
	
	/**
	 * toString takes no parameters
	 * @return string for the color + B for bishop
	 */
	public String toString() {
		
		return this.getColor() + "B";
	}

	/**
	 * isValid method for checking if the move set by user is valid
	 * @param startPos the starting position startPos
	 * @param destPos destination position destPos
	 * @param b for the position on the current board
	 * @return boolean true/false for valid and invalid respectively
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
	 * assessing the move of the piece and checking it/validating with the list of possible moves
	 * @param startx int of starting x position
	 * @param starty  int of starting y position
	 * @param b board to get the position of the piece on the board
	 * @return the list of possible moves of the bishop
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
        //int numSpacesYUp = starty+1;//Not sure if this math is correct
        
		int numSpacesYUp = Math.abs(starty);
        int numSpacesYDown = Math.abs(8-(starty+1));//^
		
        
        //go left and right
		int numSpacesXLeft=Math.abs(startx);//TO DO: Add Math
		//int numSpacesXRight =Math.abs(8-(startx+1)); //old
		int numSpacesXRight =Math.abs(8-(startx+1));//new
        
        
        
		//go diagonal upper right
        
		int numtoTopRightCorner = Math.min(numSpacesXRight, numSpacesYUp);//the min of the distance from the current spot to the right edge and to the top edge
        for(int i = 1;i<=numtoTopRightCorner;i++) {
        	int endx = startx+i;
        	int endy = starty-(i);
        	
    		Position endpo = b.getElement(endx, endy);
    		if(endpo.getState() != null) {//theres a piece at the end loc
        		String endcol = endpo.getState().getColor();
        		boolean endcolor = true;//color: white is true, black if false
        		if(endcol.equalsIgnoreCase("b")) {
        			endcolor = false;
        		}
        		Move m = new Move(startx, starty, endx, endy);
            	if(color != endcolor) {
            		//System.out.println("");
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
    			Move m = new Move(startx, starty, endx, endy);
    			moves.add(m);
    		}
        }
		
		
		
		//go diagonal upper left
		int numtoTopLeftCorner = Math.min(numSpacesXLeft, numSpacesYUp);//the min of the distance from the current spot to the right edge and to the top edge
        for(int i = 1;i<=numtoTopLeftCorner;i++) {
        	int endx = startx-(i);
        	int endy = starty-(i);
        	
    		Position endpo = b.getElement(endx, endy);
    		if(endpo.getState() != null) {//theres a piece at the end loc
        		String endcol = endpo.getState().getColor();
        		boolean endcolor = true;//color: white is true, black if false
        		if(endcol.equalsIgnoreCase("b")) {
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
            		//break;//can't get any more in the upper right diag because there would be a piece blocking its way
            	}else if(color == endcolor) {
            		//we don't add the move, because its not possible :/
            		//you cant move to a location where you already have a piece
            		//break;
            	}
            	break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, endy);
    			moves.add(m);
    		}
        	
        	
        }
        
        
        
		//go diagonal lewer left
		int numtoLowerLeftCorner = Math.min(numSpacesXLeft, numSpacesYDown);//the min of the distance from the current spot to the right edge and to the top edge
        for(int i = 1;i<=numtoLowerLeftCorner;i++) {
        	int endx = startx-(i);
        	int endy = starty+(i);
        	
    		Position endpo = b.getElement(endx, endy);
    		if(endpo.getState() != null) {//theres a piece at the end loc
        		String endcol = endpo.getState().getColor();
        		boolean endcolor = true;//color: white is true, black if false
        		if(endcol.equalsIgnoreCase("b")) {
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
            		//break;//can't get any more in the upper right diag because there would be a piece blocking its way
            	}else if(color == endcolor) {
            		//we don't add the move, because its not possible :/
            		//you cant move to a location where you already have a piece
            		//break;
            	}
            	break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, endy);
    			moves.add(m);
    		}
        }
        
		//go diagonal lower right
		int numtoLowerRightCorner = Math.min(numSpacesXRight, numSpacesYDown);//the min of the distance from the current spot to the right edge and to the top edge
        for(int i = 1;i<=numtoLowerRightCorner;i++) {
        	int endx = startx+(i);
        	int endy = starty+(i);
        	
        	//System.out.println("num spaces x right:" + numSpacesXRight);
        	//System.out.println("num spaces y down:" + numSpacesYDown);
        	
    		Position endpo = b.getElement(endx, endy);
    		if(endpo.getState() != null) {//theres a piece at the end loc
        		String endcol = endpo.getState().getColor();
        		boolean endcolor = true;//color: white is true, black if false
        		if(endcol.equalsIgnoreCase("b")) {
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
            		//break;//can't get any more in the upper right diag because there would be a piece blocking its way
            	}else if(color == endcolor) {
            		//we don't add the move, because its not possible :/
            		//you cant move to a location where you already have a piece
            		//break;//should break cause you cant go more in that direction
            	}
            	break;//can't get any more in the lower right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, endy);
    			moves.add(m);
    		}
        	
        	
        }
		
		
		return moves;//Return all possible legal moves
	}
	
}
