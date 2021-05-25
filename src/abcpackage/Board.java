package abcpackage;

import java.util.ArrayList;

import abcpackage.pieces.Bishop;
import abcpackage.pieces.King;
import abcpackage.pieces.Knight;
import abcpackage.pieces.Pawn;
import abcpackage.pieces.Queen;
import abcpackage.pieces.Rook;
/**
 * Board class to set and print the Board of the chess game
 * @author Daniel Rogozenski
 */
public class Board {
	
	
	Position[][] board;
	
	public Board() {
		int size = 8;
		board = new Position[size][size];
		for(int i=0;i<8;i++) {
			for(int j = 0;j<8;j++) {
				board[i][j] = new Position(i,j);
				//board[i][j].x = i;
				//board[i][j].y = j;
			}
		}
		
		populateBoard();
		//Board is x any y like this (where it starts counting)
		//0 1 2 3 4
		//1 
		//2
		//3
		
		
	}
	/**
	 * populateBoard method to fill board with appropriate starting pieces
	 * No parameters and no return type
	 */
	public void populateBoard() {
		//Set up the beginning board
		
		for(int i =0;i<8;i++) {
			Piece p = new Pawn("b");
			if(p == null) {
				System.out.print("NULL");
			}
			board[i][1].setState(p);
		}
		
		for(int i =0;i<8;i++) {
			board[i][6].setState(new Pawn("w"));
		}
		
		board[0][0].setState(new Rook("b"));
		board[1][0].setState(new Knight("b"));
		board[2][0].setState(new Bishop("b"));
		board[3][0].setState(new Queen("b"));
		board[4][0].setState(new King("b"));
		board[5][0].setState(new Bishop("b"));
		board[6][0].setState(new Knight("b"));
		board[7][0].setState(new Rook("b"));
		
		board[0][7].setState(new Rook("w"));
		board[1][7].setState(new Knight("w"));
		board[2][7].setState(new Bishop("w"));
		board[3][7].setState(new Queen("w"));
		board[4][7].setState(new King("w"));
		board[5][7].setState(new Bishop("w"));
		board[6][7].setState(new Knight("w"));
		board[7][7].setState(new Rook("w"));
		
		//System.out.println("Done populating board!");
	}
	/**
	 * 
	 * @param x for the x coordinate of a certain square on the board
	 * @param y for the y coordinate of a certain square on the board
	 * @return the position of the piece on the board, respective to the piece
	 */
	public Position getElement(int x, int y) {
		return board[x][y];
	}
	
	//public boolean isValidMove(Move m) {//(String m1, String m2) {
		
	//	return false;
	//}
	
	public int convertX(String s) {//convert the input string to an x value on the board grid
		
		return 0;
	}
	
	public int convertY(String s) {
		
		return 0;
	}
	
	/**
	 * printBoard prints out the chess game board for user to see
	 * no return type and no parameters
	 */
//	Print out the whole board
	public void printBoard() {
		//TO DO : Add aesthetic ##'s
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if(board[j][i].getState() == null) {
					if(i%2 ==0 && j%2 ==1) {
						System.out.print("## ");
					}else if(i%2==1 && j%2==0) {
						System.out.print("## ");
					}else {
						System.out.print("   ");
					}
					
				}else {
					System.out.print(board[j][i].getState().toString() + " ");
				}
			}
			
			
			
			System.out.print("" + (8-i));
			System.out.println();
		}
		System.out.print(" a  b  c  d  e  f  g  h ");
		System.out.println();
		
	}	
	
	/**
	 * grabbing the position of the kings for both sides
	 * @return true if in check and false if otherwise
	 */
	public boolean check() {

		//unnecessary anymore
//        //Holds the location of each player's king, and the piece to compare
//        Position whiteKing = null, blackKing = null;
//        
//        
//        //Search for the kings
//        for(int i = 0; i < 8; i++) {
//            for(int j = 0; j < 8; j++) {
//                Position s = getElement(i,j);
//                if(s.getState() == null) {
//                    //Skip null spot
//                }
//                else if((s.getState() instanceof King) && s.getState().getColor().equals("w"))
//                    whiteKing = s;
//                else if((s.getState() instanceof  King) && s.getState().getColor().equals("b"))
//                    blackKing = s;
//                
//            }
//           
//        }

        //Compare each piece to see if it can attack opponents king
        //Search whole board for pieces, and check all moves to see if any return a check
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                Position s = getElement(i,j);
                if(s.getState() == null) {
                    //Skip null spot
                } else if(s.getState().getColor().equals("w")) {
                	ArrayList<Move> moves = s.getState().getPossibleMoves(i,j, this);
                	if(moves != null) {
                    	for(Move m:moves) {
                            if(m.check==true) {
                                //System.out.println("Check - White can attack black's king");
                                return true;    //Black in check

                            }
                    	}
                	}

                } else if(s.getState().getColor().equals("b")) {
                	ArrayList<Move> moves = s.getState().getPossibleMoves(i,j, this);
                	if(moves!=null) {
                    	for(Move m:moves) {
                            if(m.check==true) {
                                //System.out.println("Check - Black can attack white's king");
                                return true;    //White in check

                            }
                    	}
                	}

                }
            }
        }
		
		
		return false;
	}
	
	
    /**
     * Checks if a player is in checkmate
     *
     * @param turn The color to check for checkmate (w or b): white = true, black = false
     * @return True if player is in checkmate, false otherwise
     */
	public boolean checkMate(boolean turn) {//white is true, black is false; turn is whos turn it is (who's in checkmate)
		//checks all possible moves of the defendant to see if any move results in loss of check
		
		boolean mate = true;
		
		if(check()==false) {
			return false;
		}
		
		//go through the board and find all the pieces of "turn"s color
        for(int x = 0; x < 8; x++) {
            for(int y = 0; y < 8; y++) {
            	Piece p = getElement(x, y).getState();
                if(p != null) {//theres a piece there
                	if((p.getColor().equalsIgnoreCase("w") && turn == true) || (p.getColor().equalsIgnoreCase("b") && turn == false)) {
                		//it's what we're looking for
                		//for(Move m: p.getPossibleMoves(startpos, b))
                		
                		//We found a piece, now try every possible move of that piece and see if there is still a check
                		for(Move m : p.getPossibleMoves(x, y, this)) {
                			
                			//Make a new board an implement the changes and see if there's still a check, if theres not a check anymore, we return false
                			//this is because theres still a move that can undo the check, hence there's not a checkmate
                			Board b = new Board();
                			for(int i=0;i<8;i++) {
                				for(int j=0;j<8;j++) {
                					b.board[i][j]= new Position();
                					Piece ps = board[i][j].getState();
                					if(ps!=null) {
                    					b.board[i][j].state = Piece.getPiecefromString(ps.toString());
                    					if(b.board[i][j].state == null) {
                    						System.out.println("its still null");
                    					}
                    					//b.board[i][j].state.firstmove = ps.firstmove;
                    					b.board[i][j].state.setFirstMove(ps.firstmove);
                					}	
                					
                					
                					//= board[i][j].;
                					
                				}
                			}
                			
        					b.performMove(m);
        					if(b.check() == false) {
        						return false;
        					}
                			
                			
                		}
                		
                		
                	}
                	
                }else {
                	//no piece there, just carry on
                	continue;
                }
            	
            	
            }
        }
		
		return mate;
	}
	
	
	/**
	 * checks if a move will result in a player putting themselves in check, inorder to see if its invalid
	 * @param move move to check the result
	 * @param turn the string color of whos turn it is
	 * @return true if in check and false if otherwise
	 */
	public boolean check(Move move, String turn) {
		//turn - white is true, black if false


		//performs the move on a new board to see if it results in check
		
		//Make a new board an implement the changes and see if there's still a check, if theres not a check we return false
		Board b = new Board();
		for(int i=0;i<8;i++) {
			for(int j=0;j<8;j++) {
				b.board[i][j]= new Position();
				Piece ps = board[i][j].getState();
				if(ps!=null) {
					b.board[i][j].state = Piece.getPiecefromString(ps.toString());
					if(b.board[i][j].state == null) {
						System.out.println("its still null");
					}
					//b.board[i][j].state.firstmove = ps.firstmove;
					b.board[i][j].state.setFirstMove(ps.firstmove);
				}	
				
				
				
				
			}
		}
		
		b.performMove(move);
		
		if(turn.equals("w")) {
			//System.out.println("white turn in check method");
	        //Compare each piece to see if it can attack opponents king
	        //Search whole board for pieces, and check all moves to see if any return a check
	        for(int i = 0; i < 8; i++) {
	            for(int j = 0; j < 8; j++) {
	                Position s = b.getElement(i,j);
	                if(s.getState() == null) {
	                    //Skip null spot
	                } else if(s.getState().getColor().equals("b")) {
	                	ArrayList<Move> moves = s.getState().getPossibleMoves(i,j, b);
	                	if(moves!=null) {
	                    	for(Move m:moves) {
	                            if(m.check==true) {
	                            	//System.out.println("returning true for white in check method");
	                                //System.out.println("Check - Black can attack white's king");
	                                return true;    //White in check

	                            }
	                    	}
	                	}

	                }
	            }
	        }
			
		}else {//blacks turn
			//System.out.println("black turn in check");
	        //Compare each piece to see if it can attack opponents king
	        //Search whole board for pieces, and check all moves to see if any return a check
	        for(int i = 0; i < 8; i++) {
	            for(int j = 0; j < 8; j++) {
	                Position s = b.getElement(i,j);
	                if(s.getState() == null) {
	                    //Skip null spot
	                } else if(s.getState().getColor().equals("w")) {
	                	ArrayList<Move> moves = s.getState().getPossibleMoves(i,j, b);
	                	if(moves != null) {
	                    	for(Move m:moves) {
	                            if(m.check==true) {
	                                //System.out.println("Check - White can attack black's king");
	                                return true;    //Black in check

	                            }
	                    	}
	                	}

	                }
	            }
	        }
		}
		
		
		
		return false;
	}
	
	
	//this method will actually make the changes of the move onto the board
	
	/**
	 * performMove to move the actual pieces to their respective places
	 * @param m the move to perform
	 * no return type and parameter m of Move
	 */
	public void performMove(Move m) {
		Position to = getElement(m.tox, m.toy);
		
		
		if(m.doublemove== false) {
			if(to.getState()==null) {
				//perfect, just move the piece
				//or handle enpassant
				if(board[m.prevx][m.prevy].state instanceof Pawn) {
					if(m.enpassantattack == true) {
						board[m.tox][m.toy].state = board[m.prevx][m.prevy].state;
						board[m.enpassx][m.enpassy].state = null;
						board[m.prevx][m.prevy].state = null;
					}else {
						board[m.tox][m.toy].state = board[m.prevx][m.prevy].state;
						board[m.prevx][m.prevy].state = null;
					}
				}else {
					board[m.tox][m.toy].state = board[m.prevx][m.prevy].state;
					board[m.prevx][m.prevy].state = null;
				}
				

				//System.out.println("piece moved!");
				
				
				if(board[m.tox][m.toy].state instanceof King) {
					board[m.tox][m.toy].state.firstmove = false;
				}else if(board[m.tox][m.toy].state instanceof Rook) {
					board[m.tox][m.toy].state.firstmove = false;
				}else if(board[m.tox][m.toy].state instanceof Pawn) {
					
					if(board[m.tox][m.toy].state.enpassant == true) {
						board[m.tox][m.toy].state.enpassant = false;
					}
					if(board[m.tox][m.toy].state.firstmove == true) {
						board[m.tox][m.toy].state.enpassant = true;
					}
					
					board[m.tox][m.toy].state.firstmove = false;
					
					//check for promotions
					if(m.promotion == true) {
						board[m.tox][m.toy].state = m.promote;
					}
					
				}//I could just set it equal to false for all of them...
				
			}else {
				//replace the piece..
				//the case where a move to location has a same color shouldnt be possible
				board[m.tox][m.toy].state = board[m.prevx][m.prevy].state;
				board[m.prevx][m.prevy].state = null;
				//System.out.println("Piece taken!");
				if(board[m.tox][m.toy].state instanceof King) {
					board[m.tox][m.toy].state.firstmove = false;
				}else if(board[m.tox][m.toy].state instanceof Rook) {
					board[m.tox][m.toy].state.firstmove = false;
				}else if(board[m.tox][m.toy].state instanceof Pawn) {
					board[m.tox][m.toy].state.firstmove = false;
					//dont have to worry about enpassant here
				}
			}
		}else {
			//castling
			Position dto = getElement(m.dtox, m.dtoy);
			System.out.println("Castling, made it this far 1");
			if(dto.getState() == null) {
				System.out.println("Casting dto state null");
			}else {
				System.out.println(dto.getState().toString());
			}
			if(to.getState() == null) {
				System.out.println("Casting to state null");
			}
			if(to.getState()==null && dto.getState() ==null) {
				System.out.println("Castling, moving the piece");
				//perfect, just move the piece
				board[m.tox][m.toy].state = board[m.prevx][m.prevy].state;
				board[m.prevx][m.prevy].state = null;
				//System.out.println("king piece moved!");
				board[m.tox][m.toy].state.firstmove = false;
				
				board[m.dtox][m.dtoy].state = board[m.dprevx][m.dprevy].state;
				board[m.dprevx][m.dprevy].state = null;
				//System.out.println("rook piece moved!");
				board[m.dtox][m.dtoy].state.firstmove = false;
				
				
			}else {
				//this shouldnt be possible, you cant do this
				System.out.println("Impossible state");
				
			}
			
			
			
		}
		
		
		
	}
	/**
	 * over() to check the game status
	 * @return false if else, true if game over
	 */
	public boolean over() {//check if the game is over and there's a winner
		//this isnt used
		return false;
	}
	
	
	public boolean winner() {//returns the winner, to be called only if over is true
		//this isnt used
		return false;
	}
	
}
