package abcpackage;

import java.util.ArrayList;

import abcpackage.pieces.Bishop;
import abcpackage.pieces.King;
import abcpackage.pieces.Knight;
import abcpackage.pieces.Pawn;
import abcpackage.pieces.Queen;
import abcpackage.pieces.Rook;
/**
 * Piece class where it specifies the piece object and its functions
 * @author Daniel Rogozenski
 */
public abstract class Piece {
	
	private String color;
	boolean firstmove = true;//for keeping track of a pawns first move for the 2 spaces
	boolean enpassant = false;
	int enpassx;
	int enpassy;
	
	public Piece(String color) {
		this.color = color;
		this.firstmove = true;//set it false after you make a first move; only use this in pawn
		this.enpassant = false;
		enpassx = 0;
		enpassy=0;//unset
		
	}
	/**
	 * getting the color of the piece
	 * @return String of the color 
	 */
	public String getColor() {
		
		return this.color;
	}
	
	/**
	 * getting the color of the piece
	 * @param en the boolean of whether its enpassant or not
	 */
	public void setEnpassant(boolean en) {
		this.enpassant = en;
	}
	
	/**
	 * getting the enpassant value of the piece
	 * @return boolean of whether the piece is enpassant or not
	 */
	public boolean getEnpassant() {
		return this.enpassant;
	}
	
	/**
	 * getting a new piece from a string for use by Main
	 * @param s the pieces string
	 * @return Piece of a certain type depending on the string
	 */
	public static Piece getPiecefromString(String s) {
		String color = s.substring(0,1);
		String p = s.substring(1);
		//System.out.println("color: " + color);
		//System.out.println("p:" + p);
		//Incomplete, not sure if I still need this
		if(p.equalsIgnoreCase("N")) {
			return new Knight(color);
		}
		if(p.equalsIgnoreCase("B")) {
			return new Bishop(color);
		}
		if(p.equalsIgnoreCase("Q")) {
			return new Queen(color);
		}
		if(p.equalsIgnoreCase("R")) {
			return new Rook(color);
		}
		if(p.equalsIgnoreCase("K")) {
			return new King(color);
		}
		if(p.equalsIgnoreCase("P")) {
			return new Pawn(color);
		}
		
		return null;
		
	}
	
	public static Piece getPiecefromString2(String s, String color) {
		String p = s.substring(0,1);
		//System.out.println("color: " + color);
		//System.out.println("p:" + p);
		//Incomplete, not sure if I still need this
		if(p.equalsIgnoreCase("N")) {
			return new Knight(color);
		}
		if(p.equalsIgnoreCase("B")) {
			return new Bishop(color);
		}
		if(p.equalsIgnoreCase("Q")) {
			return new Queen(color);
		}
		if(p.equalsIgnoreCase("R")) {
			return new Rook(color);
		}
		if(p.equalsIgnoreCase("K")) {
			return new King(color);
		}
		if(p.equalsIgnoreCase("P")) {
			return new Pawn(color);
		}
		
		return null;
		
	}
	
	/**
	 * isValid to declare if a move is possible
	 * @param startPos of the piece by user
	 * @param destPos of the piece by user
	 * @param b of Board, for the respective pieces
	 * @return a boolean true if valid and false if invalid
	 */
	public abstract boolean isValid(String startPos, String destPos, Board b);
	
	/**
	 * ArrayList for getPossibleMoves to map all possible  
	 * @param startx for the y int the piece in moving from
	 * @param starty for the x int the piece is moving from
	 * @param b for the position of piece on board
	 * @return the list of possible moves so that they can be validated 
	 */
	public abstract ArrayList<Move> getPossibleMoves(int startx, int starty, Board b);
	
	public ArrayList<Move> getPossibleMoves(String startpos, Board b){
		
		int startx = Main.converttoMove(String.valueOf(startpos.charAt(0)));
		int starty = Integer.valueOf(String.valueOf(startpos.charAt(1)));
				
		//System.out.println("x:" + startx + " y:" + (8-starty));
		return getPossibleMoves(startx, (8-starty),b);
	}
	/**
	 * getMove method to fetch the move of the user's input
	 * @param startPos for the starting coordinate
	 * @param destPos for the destination coordinate
	 * @param b for the board positions of the pieces
	 * @return m, for moves that are possible, added to the list
	 */
	public Move getMove(String startPos, String destPos, Board b) {
		
		int prevx = Main.converttoMove(String.valueOf(startPos.charAt(0)));
		int prevy = 8-Integer.valueOf(String.valueOf(startPos.charAt(1)));
		int tox = Main.converttoMove(String.valueOf(destPos.charAt(0)));
		int toy = 8-Integer.valueOf(String.valueOf(destPos.charAt(1)));
		
		ArrayList<Move> moves = getPossibleMoves(startPos, b);
		//System.out.println("piece move size: " +moves.size());
		for(Move m :moves) {
			//System.out.println("piece fromx:" + m.getPrevX() + " fromy: " + m.getPrevY());
			//System.out.println("move tox:" + m.getToX() + " fromy: " + m.getToY());
			if(m.getToX() == tox && m.getToY() == toy) {
				return m;//or we could return the Move/null, which will tell us the populated info of the Move object (piecetaken/promotion of whatever)
			}
			
		}
		
		return null;
	}
	/**
	 * for the pawn 
	 * @param set boolean type for true if pawn is in starting position
	 */
	public void setFirstMove(boolean set){
		this.firstmove = set;
	}
	
	public boolean isFirstMove() {
		return this.firstmove;
	}
	
}
