package abcpackage;
/**
 * Position class to fetch the position of pieces
 * @author Daniel Rogozenski
 *
 */
public class Position {
	
	int x,y;
	Piece state;
	
	/**
	 * Constructor for Position
	 */
	public Position() {
		this.state = null;
	}
	/**
	 * constructor with parameters 
	 * @param x takes in the x coordinate of piece (on board)
	 * @param y takes in the y coordinate of piece (on board)
	 */
	public Position(int x, int y) {
		this.x = x;
		this.y = y;
		this.state = null;
	}
	
	/**
	 * checking if the piece has been set/moved
	 * @param p the piece to be set/moved
	 */
	public void setState(Piece p) {
		this.state = p;
		//check if it removed seomthing?
		
	}
	/**
	 * getState method to fetch the state of the piece
	 * @return Piece
	 */
	public Piece getState() {
		
		return this.state;
		
	}
	
}
