package Terminals;

import java.util.ArrayList;

import Data.ListData;
import Data.Pieza;
import Problem.StripPackingProblem;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Menor extends GPNode{

	public String toString() { return "MENOR"; }

    public int expectedChildren() { return 0; }
	
    @Override
    public void eval(EvolutionState es, int i, GPData gpdata, ADFStack adfs, GPIndividual gpi, Problem prblm) {
        
    	ListData ld = ((ListData)gpdata);
    	ld.posObjetivo = menor(((StripPackingProblem)prblm).listaOrginalcurrent);
        
    }
    
public int menor(ArrayList<Pieza> listaOriginal){
    	
    	int idPiezaMenor = 0;
    	double largoPieza = listaOriginal.get(0).getLargoPieza();
    	
    	for(int i = 0; i < listaOriginal.size(); i++){
    		if(listaOriginal.get(i).getLargoPieza() <= largoPieza){
    			
    			idPiezaMenor = i;
    			largoPieza = listaOriginal.get(i).getLargoPieza();
    			
    		}
    	}
    	
    	return idPiezaMenor;
    	
    } 
	
}
