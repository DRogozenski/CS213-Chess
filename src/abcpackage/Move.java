package abcpackage;
/**
 * Move class for the movement of the pieces
 * @author Daniel Rogozenski
 *
 */
public class Move {

	int prevx, prevy;
	int tox, toy;
	int dprevx, dprevy;
	int dtox, dtoy;
	
	Piece promote;
	
	boolean check;
	boolean piecetaken;
	boolean promotion;
	boolean doublemove;
	
	boolean enpassantdefend;
	boolean enpassantattack;
	int enpassx;//defender spot that they skipped over
	int enpassy;//defender
	
	public Move(int prevx, int prevy, int tox, int toy) {
		this.prevx = prevx;
		this.prevy = prevy;
		this.tox = tox;
		this.toy = toy;
		promote = null;
		check = false;
		piecetaken = false;
		promotion = false;
		doublemove = false;
		enpassantdefend = false;//not such thing as enpassant defend anymore, useless
		enpassantattack = false;
		
		
		//add check for check
		
		
		//add check for piece taken
		
		
		//add check for promotion
		
	}
	
	public Move(int prevx, int prevy, int tox, int toy,int dprevx, int dprevy, int dtox, int dtoy) {
		this.prevx = prevx;
		this.prevy = prevy;
		this.tox = tox;
		this.toy = toy;
		
		this.dprevx = dprevx;
		this.dprevy = dprevy;
		this.dtox = dtox;
		this.dtoy = dtoy;
		
		promote = null;
		check = false;
		piecetaken = false;
		promotion = false;
		doublemove = true;
		
		//add check for check
		
		
		//add check for piece taken
		
		
		//add check for promotion
		
	}
	
	
	/**
	 * constructor Move
	 * @param prevx for alpha half of starting coordinate
	 * @param prevy for number half of starting coordinate
	 * @param tox for alpha half of destination
	 * @param toy for number half of destination
	 * @param type for the type of piece
	 */
	public Move(int prevx, int prevy, int tox, int toy, Piece type) {
		this(prevx, prevy, tox, toy);
		//String type is type of piece to promote pawn to
		promote = type;
		
	}

	public int getPrevX() {
		return this.prevx;
	}
	
	public int getPrevY() {
		return this.prevy;
	}
	
	public int getToX() {
		return this.tox;
	}
	
	public int getToY() {
		return this.toy;
	}
	
	public int getdPrevX() {
		return this.prevx;
	}
	
	public int getdPrevY() {
		return this.prevy;
	}
	
	public int getdToX() {
		return this.tox;
	}
	
	public int getdToY() {
		return this.toy;
	}
	
	public void setDoubleMove(boolean move) {
		this.doublemove = move;
	}
	
	public void setPieceTaken(boolean taken) {
		this.piecetaken = taken;
	}
	
	public void setPromotion(boolean promo) {
		this.promotion = promo;
	}
	
	public void setPromoPiece(Piece p) {
		this.promote = p;
	}
	
	public void setCheck(boolean che) {
		this.check = che;
	}
	
	public boolean getCheck() {
		return this.check;
	}
	
	/**
	 * will set the empassant to what ever the input tells it to
	 * @param enpas the boolean of if its an enpassant attack or not
	 */
	public void setEnpassantAttack(boolean enpas) {
		this.enpassantattack = enpas;
	}
	
	public boolean getEnpassantAttack() {
		return this.enpassantattack;
	}
	
	public int getEnpassX() {
		return this.enpassx;
	}
	
	public int getEnpassY() {
		return this.enpassy;
	}
	
	public void setEnpassX(int x) {
		this.enpassx = x;
	}
	
	public void setEnpassY(int y) {
		this.enpassy = y;
	}
	
	
}
