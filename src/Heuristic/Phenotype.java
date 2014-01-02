package Heuristic;

import java.util.ArrayList;

import Data.Pieza;

public class Phenotype {
	public int height;
	private int lowest;
	private int widthTotal;
	public ArrayList<Pieza> stripes;
	
	public Phenotype(int width) {
		widthTotal = width;
		lowest = 0;
		stripes = new ArrayList<Pieza>();
		stripes.add(new Pieza(-1, 0, width));
	}
	
	public void push(Pieza newPiece) {
		// Siendo que lowest contiene el stripe mas bajo
		while(expand(lowest, newPiece) != 0); // Se repite siempre que sea distinto de 0
		
		refreshHeights();
		// newPiece->write(cout); cout << " => \t";
		// write(cout); cout << " lower=" << lowest << " altura=" << height << endl << endl; // cout << " (stripe.size=" << stripes.size() << ", lowest=" << lowest << ")" << endl;
	}

	public double fitness() {
		// Es obvio que el fitness será la variable 'height', 
		// pero para añadirle algo mas interesante, se la sumará 
		// el factor de área que queda disponible para nuevas 
		// piezas por debajo de height (Que oscila entre (float) [0, 1])
		// [Se supone que este porcentaje debe ser lo mas bajo posible]
		int i, area = 0, fullArea = widthTotal * height;
		for(i = 0; i < stripes.size(); i++)
			area += stripes.get(i).getAnchoPieza() * stripes.get(i).getLargoPieza();
		return (float) ((float) height + ( 1.0f - (float) area / (float) fullArea ));
	}
	
	private int expand(int pos, Pieza ns) {
		// pos es la posición del vector de stripes a partir de la cual
		// ns es el nuevo stripe
		Pieza s = stripes.get(pos);

	//	System.out.println("Ancho stripe actual: " + s.getAnchoPieza() + " altura = " + height);
	//	System.out.println("Ancho stripe entrante: " + ns.getAnchoPieza());
		
		if(ns.getAnchoPieza() == s.getAnchoPieza()) { // Si la pieza cabe justo
			// Entonces el stripe se mantiene y cambia su altura
			s.setLargoPieza(s.getLargoPieza() + ns.getLargoPieza());
			combine();
			refreshHeights();
			return 0;
		}
		else if(ns.getAnchoPieza() < s.getAnchoPieza()) { // Si la pieza es menor al stripe
	//		System.out.println("  pieza menor");
			// Entonces se deben generar 2 stripes
			// Al stripe que había, se le descuenta el ancho y mantiene su altura
			s.setAnchoPieza(s.getAnchoPieza() - ns.getAnchoPieza());

			// El nuevo stripe se inserta en la posición pos según el iterador .begin()
			stripes.add(pos, new Pieza(-1, s.getLargoPieza() + ns.getLargoPieza(), ns.getAnchoPieza()));
			//stripes.insert(stripes.begin() + pos, piece(ns.width, s->height + ns.height));
			combine();
			refreshHeights();
			return 0;
		}
		else if(ns.getAnchoPieza() > s.getAnchoPieza()) { // En este caso como el stripe candidato tiene muy poco ancho, entonces deberá inflarse
			swell(pos);
			refreshHeights();
			return -1; // No se ha podido expandir, fue necesario inflar
		}
		else { System.out.println("* Error fatal: Something went really wrong"); System.exit(1); } 

		stripes.set(pos, s);
		return 0;
	}

	private int swell(int pos) {
//		System.out.println("  swell(" + pos + ")");
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
		else { 
			System.out.println("* Error fatal: swell gone hell");
			System.exit(1); 
		} 
		
		// Se combina este stripe con el strip del vecino
		// stripes.at(minNeighbor).width += stripes.at(pos).width;
		stripes.get(minNeighbor).setAnchoPieza(
			stripes.get(minNeighbor).getAnchoPieza() +
			stripes.get(pos).getAnchoPieza());
		
		// Se elimina el stripe que se acaba de combinar
		stripes.remove(pos);
		// stripes.erase(stripes.begin() + pos);

		// Devuelve la posición del nuevo stripe cuestión
		return ((minNeighbor < pos) ? minNeighbor : pos);
	}

	private void combine() {
		// Genera una pérdida de contexto respecto de los índices
		int i, aux = 0;
		Pieza x, xn;
		for(i = 0; i < stripes.size()-1; i++) {
			x = stripes.get(i);
			xn= stripes.get(i+1);
			if(x.getLargoPieza() == xn.getLargoPieza()) { // Si ambos tienen el mismo alto
				// Entonces hay que combinarlos, sumando sus anchos y dejando uno solo
				x.setAnchoPieza(x.getAnchoPieza() + xn.getAnchoPieza());
				stripes.remove(i+1);
				i--; // Accion de redo para el for
			}
			// stripes.set(i, x);
			// stripes.set(i+1, xn);
		}
		refreshHeights(); // Actualiza el puntero al mínimo
	}

	private void refreshHeights() {
		refreshMinHeight();
		refreshMaxHeight();
	}

	private void refreshMaxHeight() {
		int i, max = 0;
		for(i = 0; i < stripes.size(); i++) {
			if(stripes.get(i).getLargoPieza() > max)
				max = stripes.get(i).getLargoPieza();
		}
		height = max;
	}

	private void refreshMinHeight() {
		// cout << "\tBuscando menor de "; write(cout);
		int i, pmin = 0, min = stripes.get(0).getLargoPieza();
		for(i = 1; i < stripes.size(); i++) {
			if(stripes.get(i).getLargoPieza() <= min) {
				pmin = i;
				min = stripes.get(i).getLargoPieza();
			}
		}
		lowest = pmin;
		// cout << " resultando ser " << lowest << endl;
	}
	
}