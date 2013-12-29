package Data;

public class Pieza {

	private int numPieza;
	private double largoPieza;
	private double anchoPieza;
	
	
	
	
	public Pieza(int numPieza, double largoPieza, double anchoPieza) {
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
	public double getLargoPieza() {
		return largoPieza;
	}
	public void setLargoPieza(double largoPieza) {
		this.largoPieza = largoPieza;
	}
	public double getAnchoPieza() {
		return anchoPieza;
	}
	public void setAnchoPieza(double anchoPieza) {
		this.anchoPieza = anchoPieza;
	}
	
	
	
}
