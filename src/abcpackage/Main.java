package abcpackage;

import java.util.ArrayList;
import java.util.Scanner;

import abcpackage.pieces.Pawn;
import abcpackage.pieces.Queen;
/**
 * Main class to run the actual game
 * @author Daniel Rogozenski
 *
 */
public class Main {

	public static void main(String[] args) {
		Board b = new Board();
		//b.printBoard();
//		System.out.println("Printed Board");
		
		boolean turn = true;//white is true, black if false
		Scanner scanner = new Scanner(System.in);
		boolean gameover = false;
		boolean drawOffered = false;
		boolean winner = false;//default false doesnt mean anthing. white is true, black if false.
		boolean print = true;
		while(gameover == false) {
			if(print == true) {
				b.printBoard();
			}else {
				print = true;
			}
			String sturn = "Enter the move you'd like to make";
			if(turn == true) {
				sturn = sturn + "(white): ";
			}else {
				sturn = sturn + "(black): ";
			}
			System.out.print(sturn);
			String moves = scanner.nextLine();
			
			if(moves.equalsIgnoreCase("draw")) {
				if(drawOffered==true) {
					//gameover=true;
					break;
				}else {
					System.out.println("A draw has not been offered");
					continue;
					
				}
				
			}
			
			if(drawOffered==true && !moves.equalsIgnoreCase("draw")) {
				drawOffered = false;//just reset the draw offer, right?
			}
			
			
			if(moves.equalsIgnoreCase("resign")) {
				gameover = true;
				
				if(turn == true) {//if its whites turn, then black wins
					winner = false;
				}else {//if its blacks turn, then white wins
					winner = true;
				}
				
				break;
			}
			
			
			String move[] = moves.split(" ");
			
			
			//try to perform move
			int prevx = converttoMove(String.valueOf(move[0].charAt(0)));
			int prevy = 8-Integer.valueOf(String.valueOf(move[0].charAt(1)));
			int tox = converttoMove(String.valueOf(move[1].charAt(0)));
			int toy = Integer.valueOf(String.valueOf(move[1].charAt(1)));
			Position from = b.getElement(prevx, prevy);
			Piece p = from.getState();
			//if invalid dont change turn and just-> continue;
			if(p==null) {
				//invalid move, no piece at given location
				System.out.println("Illegal move, try again");
				print = false;
				continue;
			}
			
			if(p.getColor().equalsIgnoreCase("w") && turn == false) {
				//invalid, you dont own that piece
				System.out.println("Illegal move, try again");
				print = false;
				continue;
				
			}
			
			if(p.getColor().equalsIgnoreCase("b")&&turn == true) {
				//invalid, you dont own that piece
				System.out.println("Illegal move, try again");
				print = false;
				continue;
			}
			
			Move m = p.getMove(move[0], move[1], b);
			if(m == null) {
				//System.out.println("Not a possible move");
				//invalid, the move isnt possible, it doesnt exist in the list of possible moves
				System.out.println("Illegal move, try again");
				print = false;
				continue;
			}
			
			
			//check if a move will immediately result in the player who's turn it is, being put in check (they check themselves)
			//if(b.check(m, turn)) {
			if(b.check(m,p.getColor())==true) {
				System.out.println("Illegal move, try again");
				print = false;
				continue;
				
			}
			
			
			//Move m = movefromString(move);
			//boolean val = b.isValidMove(m);
			
			//boolean valid = b.isValidMove(move[0], move[1]);
			
			if(m.promotion == true) {
				if(move.length >= 3) {
					Piece promo = Piece.getPiecefromString2(move[2], p.getColor());
					if(promo == null) {
						m.setPromoPiece(new Queen(p.getColor()));
					}else {
						m.setPromoPiece(promo);
					}
					
				}else {
					m.setPromoPiece(new Queen(p.getColor()));
				}
				
				
			}
			
			//now perform the move
			b.performMove(m);
			
			
			if(move[move.length-1].equalsIgnoreCase("draw?")) {
				if(drawOffered == true) {
					//what then? theres already a draw offered...
				}
				
				drawOffered = true;
				
			}
			
			if(b.checkMate(!turn) == true) {//after you perform a move and put them in checkmate, they lose
				gameover=true;
				System.out.println("Checkmate");
				//check if this is correct
				if(turn == true) {//if its whites turn, then white wins
					winner = true;
				}else {//if its blacks turn, then black wins
					winner = false;
				}
				
			}
			
				//check for check and gameover
			if((b.check()==true || m.getCheck()==true) && b.checkMate(!turn)==false) {
					
					if(m.getCheck() == true) {//can also check that the piece taken is a king
						//SHOULD NEVER GET TO THIS, SHOULD END ON CHECKMATE
						//game is over, they got the king!
						gameover=true;
						if(turn == true) {//if its whites turn, then white wins
							winner = true;
						}else {//if its blacks turn, then black wins
							winner = false;
						}
						
					} else if(b.checkMate(turn)==true) {
						
						gameover=true;
						System.out.println("Checkmate");
						//check if this is correct
						if(turn == true) {//if its whites turn, then black wins
							winner = false;
						}else {//if its blacks turn, then white wins
							winner = true;
						}
						
					}else {
						System.out.println("Check");
					}
					
				}
			
			removeEnpassant(b, p.getColor());
			if(turn == true) {//switch turn
				turn = false;
			}else {
				turn = true;
			}
			
		}
		
		if(winner ==true && gameover == true) {//white won!
			System.out.println("White wins");
		}else if(winner == false && gameover == true) {//black won!
			System.out.println("Black wins");
			
		}else {//draw, gameover is false
			System.out.println("draw");
			
		}


		scanner.close();
		
		
		
	}
	
	
	/**
	 * @param b for the board in order to remove the enpassants after each round
	 * @param color to see the color of which enpassant should be removed
	 */
	public static void removeEnpassant(Board b, String color) {
		//turn - white is true, black if false
		
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Position s = b.getElement(i,j);
                if(s.getState() == null) {
                    //Skip null spot
                } else if(!s.getState().getColor().equals(color) ) {//remove the opposite turns enpassants, because the current turn didnt act on it
                	Piece p = s.getState();
                	if(p instanceof Pawn) {
                		p.enpassant = false;
                	}

                }
            }
        }
		
		
	}
	

	/**
	 * movefromString translates the user input into a readable move , no longer used
	 * @param move string array 
	 * @return the corresponding coordinate
	 */
	public static Move movefromString(String move[]) {
		
		if(move.length ==4) {//includes a draw
			int prevx = converttoMove(String.valueOf(move[0].charAt(0)));
			int prevy = Integer.valueOf(String.valueOf(move[0].charAt(1)));
			int tox = converttoMove(String.valueOf(move[1].charAt(0)));
			int toy = Integer.valueOf(String.valueOf(move[1].charAt(1)));
			//Piece promote = Piece.getPiecefromString(s, color)
			
			
		}if(move.length==3) {//"g7 g8 N", or "g7 g8 draw?"
			
			//m = new Move()
			int prevx = converttoMove(String.valueOf(move[0].charAt(0)));
			int prevy = Integer.valueOf(String.valueOf(move[0].charAt(1)));
			int tox = converttoMove(String.valueOf(move[1].charAt(0)));
			int toy = Integer.valueOf(String.valueOf(move[1].charAt(1)));
			//Piece promote = Piece.getPiecefromString(s, color)
			
		}else if(move.length ==2){//"e2 e4"
			int prevx = converttoMove(String.valueOf(move[0].charAt(0)));
			int prevy = Integer.valueOf(String.valueOf(move[0].charAt(1)));
			int tox = converttoMove(String.valueOf(move[1].charAt(0)));
			int toy = Integer.valueOf(String.valueOf(move[1].charAt(1)));
		
			
		}else {
			//wtf, not possible
		}
		
		return null;
	}
	
	/**
	 * converttoMove translates the user input letter into an int for use
	 * @param letter string of the move input
	 * @return the corresponding coordinate
	 */
	public static int converttoMove(String letter) {
		if(letter.equalsIgnoreCase("a")) {
			return 0;
		}else if(letter.equalsIgnoreCase("b")) {
			return 1;
		}else if(letter.equalsIgnoreCase("c")) {
			return 2;
		}else if(letter.equalsIgnoreCase("d")) {
			return 3;
		}else if(letter.equalsIgnoreCase("e")) {
			return 4;
		}else if(letter.equalsIgnoreCase("f")) {
			return 5;
		}else if(letter.equalsIgnoreCase("g")) {
			return 6;
		}//else if(letter.equalsIgnoreCase("h")) {
		//	return 7;
		//}
		return 7;
		
		
	}
	

}
