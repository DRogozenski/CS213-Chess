package abcpackage.pieces;


import java.util.ArrayList;

import abcpackage.Board;
import abcpackage.Main;
import abcpackage.Move;
import abcpackage.Piece;
import abcpackage.Position;
/**
 * Queen Class extends Piece class to create the Queen
 * @author Daniel Rogozenski
 */
public class Queen extends Piece {

	
	/** Queen constructor
	 * @param color fetches the color of the piece
	 */
	public Queen(String color) {//True is white, false is black
		super(color);

	}
	
	
	/**
	 * toString to get the piece of color + Q
	 * @return String of color + Q
	 */
	public String toString() {
		
		return this.getColor() + "Q";
	}
	
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
	 * getPossibleMoves stores all possible/valid moves the Queen can execute
	 * @param startx is the where the Queen is moving from
	 * @param starty is the where the Queen is moving from
	 * @return a list of possible moves by the Queen
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
        for(int i =1;i<=numSpacesYUp;i++) {//hopefully starting at 1 works
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
		
		
		return moves;//Return all possible legal moves
	}
	
}
