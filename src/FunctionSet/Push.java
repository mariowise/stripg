package FunctionSet;

import Data.ListData;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Push extends GPNode{

	public String toString() { return "PUSH"; }
    public int expectedChildren() { return 1; }
	
    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        
        int posicion;
        ListData rd = ((ListData)(gpdata));
        children[0].eval(es, i, gpdata, adfs, gpi, prblm);
        
        
        posicion = rd.posObjetivo;
        if(rd.vacio == false){
        	if(posicion < rd.listaOrginal.size()){
        		rd.listaFinal.add(rd.listaOrginal.get(posicion));
        		rd.listaOrginal.remove(posicion);
        	}
        	else{
        		rd.listaFinal.add(rd.listaOrginal.get(rd.listaOrginal.size()-1));
        		rd.listaOrginal.remove(rd.listaOrginal.size()-1);
        	}
        	if(rd.listaOrginal.isEmpty()){
        		rd.vacio = true;
        	}
        	else{
        		rd.vacio = false;
        	}
        }
        else{
        	rd.vacio = true;
        }
    }
}
