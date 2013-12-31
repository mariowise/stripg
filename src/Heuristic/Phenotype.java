package Heuristic;

import java.util.ArrayList;
import Data.Pieza;

public class Phenotype {
	public double widthTotal;
	public double height;
	public ArrayList<Pieza> stripes;
	public ArrayList<int[]> genoma;
	
	Phenotype(int width) {
		stripes.add(new Pieza(-1, width, 0));
		widthTotal = width;
		lowest = 0;
	}
	
	public void push(Pieza newPiece) {
		// Siendo que lowest contiene el stripe mas bajo
		while(expand(lowest, newPiece) != 0); // Se repite siempre que sea distinto de 0
		refreshHeights();
	}

	private double fitness() {
		// widthTotal debe ser el ancho del tablero (esta en la primera linea del archivo de entrada)
		int i, area = 0;
		double fullArea = widthTotal * height;
		for(i = 0; i < stripes.size(); i++)
			area += stripes.get(i).getAnchoPieza() * stripes.get(i).getLargoPieza();
		return (double) ((double) height + ( 1.0f - (double) area / (double) fullArea ));
	}
	
	private int lowest;
	
	private int expand(int pos, Pieza ns) {
		// pos es la posición del vector de stripes a partir de la cual
		// ns es el nuevo stripe
		Pieza s = stripes.get(pos);

		if(ns.getAnchoPieza() == s.getAnchoPieza()) { // Si la pieza cabe justo
			// Entonces el stripe se mantiene y cambia su altura
			s.setLargoPieza(ns.getLargoPieza());
			combine();
			refreshHeights();
			return 0;
		}
		else if(ns.getAnchoPieza() < s.getAnchoPieza()) { // Si la pieza es menor al stripe
			// Entonces se deben generar 2 stripes
			// Al stripe que había, se le descuenta el ancho y mantiene su altura
			s.setAnchoPieza(ns.getAnchoPieza());

			// El nuevo stripe se inserta en la posición pos según el iterador .begin()
			stripes.add(pos, new Pieza(-1, ns.getAnchoPieza(), s.getLargoPieza() + ns.getLargoPieza()));
			combine();
			refreshHeights();
			return 0;
		}
		else if(ns.getAnchoPieza() > s.getAnchoPieza()) { // En este caso como el stripe candidato tiene muy poco ancho, entonces deberá inflarse
			swell(pos);
			refreshHeights();
			return -1; // No se ha podido expandir, fue necesario inflar
		}
		else { 
			System.out.println("* Error fatal: Something went really wrong at 'expand' function");
			return -2;
		}
	}
	
	private int swell(int pos) {
		// Pos es el índice del elemento chico que hay que inflar
		// Es necesario elegir a cual de los stripe vecinos se hinchará
		int minNeighbor = 0;
		
		if(pos == 0) // Si se trata del primer elemento
			minNeighbor = 1; // El vecino candidato esta a la derecha
		else if(pos == stripes.size()-1) // Si se trata del último elemento
			minNeighbor = pos-1; // El vecino candidato esta a la izquierda
		else if(stripes.get(pos-1).getLargoPieza() <= stripes.get(pos+1).getLargoPieza()) // Si el de la izquierda es menor que el de la derecha
			minNeighbor = pos-1;
		else if(stripes.get(pos-1).getLargoPieza() > stripes.get(pos+1).getLargoPieza()) // Si el de la derecha es menor que el de la izquierda
			minNeighbor = pos+1;
		else { System.out.println("* Error fatal: swell gone hell"); }
		
		// Se combina este stripe con el strip del vecino
		stripes.get(minNeighbor).setAnchoPieza(stripes.get(pos).getAnchoPieza());

		// Se elimina el stripe que se acaba de combinar
		stripes.remove(pos);

		// Devuelve la posición del nuevo stripe cuestión
		return ((minNeighbor < pos) ? minNeighbor : pos);
	}
	
	private void combine() {
		// Genera una pérdida de contexto respecto de los índices
		int i, aux = 0;
		Pieza x, xn;
		for(i = 0; i < stripes.size()-1; i++) {
			x  = stripes.get(i);
			xn = stripes.get(i+1);
			if(x.getLargoPieza() == xn.getLargoPieza()) { // Si ambos tienen el mismo alto
				// Entonces hay que combinarlos, sumando sus anchos y dejando uno solo
				x.setAnchoPieza(xn.getAnchoPieza());
				stripes.remove(i+1);
				i--; // Accion de redo para el for
			}
		}
		refreshHeights(); // Actualiza el puntero al mínimo
	}
	
	private void refreshHeights() {
		refreshMinHeight();
		refreshMaxHeight();
	}

	private void refreshMaxHeight() {
		int i;
		double max = 0.0;
		for(i = 0; i < stripes.size(); i++) {
			if(stripes.get(i).getLargoPieza() > max)
				max = stripes.get(i).getLargoPieza();
		}
		height = max;
	}
	
	private void refreshMinHeight() {
		int i, pmin = 0;
		double min = stripes.get(0).getLargoPieza();
		for(i = 1; i < stripes.size(); i++) {
			if(stripes.get(i).getLargoPieza() <= min) {
				pmin = i;
				min = stripes.get(i).getLargoPieza();
			}
		}
		lowest = pmin;
	}
	
}
