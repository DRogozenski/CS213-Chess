package abcpackage.pieces;

import java.util.ArrayList;

import abcpackage.Board;
import abcpackage.Main;
import abcpackage.Move;
import abcpackage.Piece;
import abcpackage.Position;
/**
 * King class extends Piece class
 * @author Daniel Rogozenski
 */
public class King extends Piece {

	
	/**
	 * 
	 * @param color fetching for the King piece
	 * declaring constructor for the King
	 */
	public King(String color) {//True is white, false is black
		super(color);
		
		
		
	}
	/**
	 * @return string for the color + K for King piece
	 * no parameters taken
	 */
	public String toString() {
		
		return this.getColor() + "K";
	}
	/**
	 * isValid method for the checking of King piece
	 * @param startPos for the starting position of the King
	 * @param destPos for the destination of the King
	 * @param b for the position of the King piece from the board
	 * @return boolean true if valid and false if invalid
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
	 * getPossibleMoves stores all possible/valid moves the Queen can execute
	 * @param startx is the x where the King is originally moving from
	 * @param starty is the y where the King is moving from
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
		
		//add castling move
		if(startpo.getState().isFirstMove()) {
			if(startcol.equalsIgnoreCase("w")) {
				//current pos is e1
				//lower right castling
				Position rookpo = b.getElement(7, 7);
				if(rookpo.getState()!= null) {
					if(rookpo.getState() instanceof Rook) {
						if(rookpo.getState().isFirstMove()) {
							Position empty1 = b.getElement(5, 7);//f1 - rook
							Position empty2 = b.getElement(6, 7);//g1 - king
							
							if(empty1.getState() == null && empty2.getState() == null) {
								//we can swap!
								//Move m = new Move(startx, starty, 5, 7,7,7,6,7);
								Move m = new Move(startx, starty, 6, 7,7,7,5,7);
								m.setDoubleMove(true);
								moves.add(m);
								
								
							}
						}
						
						
					}
				}
				
				//lower left castling
				Position rookpo2 = b.getElement(0, 7);
				if(rookpo2.getState()!= null) {
					if(rookpo2.getState() instanceof Rook) {
						if(rookpo2.getState().isFirstMove()) {
							Position empty1 = b.getElement(2, 7);//c1 - king
							Position empty2 = b.getElement(3, 7);//d1 - rook
							
							if(empty1.getState() == null && empty2.getState() == null) {
								//we can swap!
								Move m = new Move(startx, starty,2,7,0,7,3,7 );
								m.setDoubleMove(true);
								moves.add(m);
								
								
							}
						}
						
						
					}
				}
				
			}else if(startcol.equalsIgnoreCase("b")) {
				//upper right castling
				Position rookpo = b.getElement(7, 0);
				if(rookpo.getState()!= null) {
					if(rookpo.getState() instanceof Rook) {
						if(rookpo.getState().isFirstMove()) {
							Position empty1 = b.getElement(5, 0);//f8 - rook
							Position empty2 = b.getElement(6, 0);//g8 - king
							
							if(empty1.getState() == null && empty2.getState() == null) {
								//we can swap!
								//Move m = new Move(startx, starty, 5, 0,7,0,6,0);
								Move m = new Move(startx, starty, 6, 0,7,0,5,0);
								m.setDoubleMove(true);
								moves.add(m);
								
								
							}
						}
						
						
					}
				}
				
				//upper left castling
				Position rookpo2 = b.getElement(0, 0);
				if(rookpo2.getState()!= null) {
					if(rookpo2.getState() instanceof Rook) {
						if(rookpo2.getState().isFirstMove()) {
							Position empty1 = b.getElement(2, 0);//c1 - king
							Position empty2 = b.getElement(3, 0);//d1 - rook
							
							if(empty1.getState() == null && empty2.getState() == null) {
								//we can swap!
								Move m = new Move(startx, starty,2,0,0,0,3,0 );
								m.setDoubleMove(true);
								moves.add(m);
								
								
							}
						}
						
						
					}
				}
				
			}
			
		}
		
		
		//up 1
        int endy = starty-(1);
        if(!(endy<0)) {//check bounds
        	
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
            	//break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, startx, endy);
    			moves.add(m);
    		}
        }
        
        //down 1
        endy=starty+1;
        if(!(endy >7)) {	//check bounds
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
            	//break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, startx, endy);
    			moves.add(m);
    		}
        }
		
        //left
        int endx=startx-(1);
        	if(!(endx<0)) {
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
            	//break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, starty);
    			moves.add(m);
    		}
        }
        
        //right
        	
        endx=startx+1;
        if(!(endx>7)) {//check bounds
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
            	//break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, starty);
    			moves.add(m);
    		}
        	
        }
        
        
		//go diagonal upper right
        
        endx = startx+1;
        endy = starty-(1);
        if(!(endx>7 || endy<0)) {
        	
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
            	//break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, endy);
    			moves.add(m);
    		}
        }
		
		
		
		//go diagonal upper left

        	endx = startx-(1);
        	endy = starty-(1);
        	
        	if(!(endx<0 || endy<0)) {
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
            	//break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, endy);
    			moves.add(m);
    		}
        	
        	
        }
        
        
        
		//go diagonal lewer left
        	endx = startx-(1);
        	endy = starty+(1);
        	if(!(endx<0 || endy>7)) {
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
            	//break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, endy);
    			moves.add(m);
    		}
        }
        
		//go diagonal lower right
        	endx = startx+(1);
        	endy = starty+(1);
        	if(!(endx>7 || endy>7)) {
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
            	//break;//can't get any more in the upper right diag because there would be a piece blocking its way
    		}else {
    			Move m = new Move(startx, starty, endx, endy);
    			moves.add(m);
    		}
        	
        	
        }
		
		
		return moves;//Return all possible legal moves
	}
	
}
