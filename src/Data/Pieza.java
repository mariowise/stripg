package Data;

public class Pieza {

	private int numPieza;
	private int largoPieza;
	private int anchoPieza;
	
	
	
	
	public Pieza(int numPieza, int largoPieza, int anchoPieza) {
		super();
		this.numPieza = numPieza;
		this.largoPieza = largoPieza;
		this.anchoPieza = anchoPieza;
	}
	
	public Pieza() {
		super();
	}

	public int getNumPieza() {
		return numPieza;
	}
	public void setNumPieza(int numPieza) {
		this.numPieza = numPieza;
	}
	public int getLargoPieza() {
		return largoPieza;
	}
	public void setLargoPieza(int largoPieza) {
		this.largoPieza = largoPieza;
	}
	public int getAnchoPieza() {
		return anchoPieza;
	}
	public void setAnchoPieza(int anchoPieza) {
		this.anchoPieza = anchoPieza;
	}
	
	
	
}
